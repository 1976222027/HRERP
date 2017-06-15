package com.huirong.ui.appsfrg;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huirong.R;
import com.huirong.adapter.VehicleReturnLoadMoreListAdapter;
import com.huirong.base.BaseActivity;
import com.huirong.base.BaseLoadMoreListAdapter;
import com.huirong.common.MyException;
import com.huirong.dialog.Loading;
import com.huirong.helper.UserHelper;
import com.huirong.inject.ViewInject;
import com.huirong.model.VehicleReturnModel;
import com.huirong.ui.appsfrg.childmodel.vehiclereturn.VehicleReturnMaintenanceCompleteActivity;
import com.huirong.ui.appsfrg.childmodel.vehiclereturn.VehicleReturnMaintenanceUncompleteActivity;
import com.huirong.ui.appsfrg.childmodel.vehiclereturn.VehicleReturnUseCompleteActivity;
import com.huirong.ui.appsfrg.childmodel.vehiclereturn.VehicleReturnUseUncompleteActivity;
import com.huirong.widget.RefreshListView;

import java.util.ArrayList;
import java.util.List;

/**
 * 交车详细界面
 * <p>
 * 多次跳转完成交车后，需要再次跳转到该界面，未交车 显示 变成已交车
 * Created by sjy on 2017/2/14.
 */

public class VehicleReturnListActivity extends BaseActivity implements RefreshListView.IReflashListener {
    //back
    @ViewInject(id = R.id.layout_back, click = "forBack")
    RelativeLayout layout_back;

    //
    @ViewInject(id = R.id.tv_title)
    TextView tv_title;

    //
    @ViewInject(id = R.id.tv_right)
    TextView tv_right;

    //list
    @ViewInject(id = R.id.listview_vehicleReturn)
    RefreshListView myListView;

    private VehicleReturnLoadMoreListAdapter vAdapter;//记录适配
    private boolean ifLoading = false;//标记
    private int pageSize = 20;
    private ArrayList<VehicleReturnModel> listData = null;//改集合只存储20条记录，拼接的所有数据在adapter的entryList中获取
    private String IMaxtime = null;
    private String IMinTime = null;
    private boolean isNeedRefresh = false;

    //常量
    private static final int GET_MORE_DATA = -38;//上拉加载
    private static final int GET_NEW_DATA = -37;//
    private static final int GET_REFRESH_DATA = -36;//
    private static final int GET_NONE_NEWDATA = -35;//没有新数据

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_vehicle_return);
        tv_title.setText(getResources().getString(R.string.vehicleRe));
        tv_right.setText("");

        initMyView();
        initListener();

        getData();
    }

    private void initMyView() {

        myListView.setInterFace(VehicleReturnListActivity.this);//下拉刷新监听
        vAdapter = new VehicleReturnLoadMoreListAdapter(this, adapterCallBack);// 上拉加载
        myListView.setAdapter(vAdapter);

    }

    private void initListener() {

        //		 点击一条记录后，跳转到登记时详细的信息
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int headerViewsCount = myListView.getHeaderViewsCount();//得到header的总数量
                int newPosition = position - headerViewsCount;//得到新的修正后的position

                //所需参数
                VehicleReturnModel model = (VehicleReturnModel) vAdapter.getItem(newPosition);//
                //页面跳转
                transferTo(model);
            }
        });
    }

    private void getData() {
        Loading.run(VehicleReturnListActivity.this, new Runnable() {
            @Override
            public void run() {
                ifLoading = true;//
                String storeID = UserHelper.getCurrentUser().getStoreID();
                try {
                    List<VehicleReturnModel> visitorModelList = UserHelper.GetVehicleReturnResults(
                            VehicleReturnListActivity.this,
                            "",//iMaxTime
                            "");

                    if (visitorModelList == null) {
                        vAdapter.IsEnd = true;
                    } else if (visitorModelList.size() < pageSize) {
                        vAdapter.IsEnd = true;
                    }


                    sendMessage(GET_NEW_DATA, visitorModelList);
                } catch (MyException e) {
                    sendMessage(GET_NONE_NEWDATA, e.getMessage());
                }
            }
        });

    }

    //RefreshListView.IReflashListener接口 下拉刷新
    @Override
    public void onRefresh() {
        Loading.noDialogRun(VehicleReturnListActivity.this, new Runnable() {

            @Override
            public void run() {
                ifLoading = true;//
                try {

                    List<VehicleReturnModel> visitorModelList = UserHelper.GetVehicleReturnResults(
                            VehicleReturnListActivity.this,
                            IMaxtime,//iMaxTime
                            "");

                    Log.d("SJY", "loadMore--min=" + IMaxtime);
                    if (visitorModelList == null) {
                        vAdapter.IsEnd = true;
                    } else if (visitorModelList.size() < pageSize) {
                        vAdapter.IsEnd = true;
                    }

                    sendMessage(GET_REFRESH_DATA, visitorModelList);

                } catch (MyException e) {
                    sendMessage(GET_NONE_NEWDATA, e.getMessage());
                }
            }

        });
    }

    // 上拉加载
    BaseLoadMoreListAdapter.AdapterCallBack adapterCallBack = new BaseLoadMoreListAdapter.AdapterCallBack() {
        @Override
        public void loadMore() {

            if (ifLoading) {
                return;
            }

            Loading.run(VehicleReturnListActivity.this, new Runnable() {

                @Override
                public void run() {

                    ifLoading = true;//
                    try {
                        List<VehicleReturnModel> visitorModelList = UserHelper.GetVehicleReturnResults(
                                VehicleReturnListActivity.this,
                                "",//iMaxTime
                                IMinTime);

                        Log.d("SJY", "loadMore--min=" + IMaxtime);
                        if (visitorModelList == null) {
                            vAdapter.IsEnd = true;
                        } else if (visitorModelList.size() < pageSize) {
                            vAdapter.IsEnd = true;
                        }
                        sendMessage(GET_MORE_DATA, visitorModelList);

                    } catch (MyException e) {
                        sendMessage(GET_NONE_NEWDATA, e.getMessage());
                    }
                }
            });

        }
    };

    @Override
    protected void handleMessage(Message msg) {
        switch (msg.what) {
            case GET_NEW_DATA://进入页面加载最新
                // 数据显示
                listData = (ArrayList<VehicleReturnModel>) msg.obj;
                vAdapter.setEntityList(listData);
                //数据处理，获取iLastUpdateTime参数方便后续上拉/下拉使用
                setIMinTime(listData);
                setIMaxTime(listData);
                Log.d("SJY", "MineApplicationActivity--GET_NEW_DATA--> myListView.reflashComplete");
                myListView.reflashComplete();
                ifLoading = false;
                break;
            case GET_REFRESH_DATA://刷新
                listData = (ArrayList<VehicleReturnModel>) msg.obj;
                vAdapter.insertEntityList(listData);
                //数据处理/只存最大值,做刷新新数据使用
                setIMaxTime(listData);
                ifLoading = false;
                break;

            case GET_MORE_DATA://加载
                listData = (ArrayList<VehicleReturnModel>) msg.obj;
                vAdapter.addEntityList(listData);
                //数据处理，只存最小值
                setIMinTime(listData);
                ifLoading = false;
                break;

            case GET_NONE_NEWDATA://没有获取新数据
                //                vAdapter.insertEntityList(null);
                sendToastMessage((String) msg.obj);
                Log.d("SJY", "MineApplicationActivity--GET_NONE_NEWDATA--> myListView.reflashComplete");
                myListView.reflashComplete();
                ifLoading = false;
                break;

            default:
                break;
        }
        super.handleMessage(msg);
    }

    public void setIMaxTime(ArrayList<VehicleReturnModel> list) {
        IMaxtime = list.get(0).getCopyTime();
    }

    public void setIMinTime(ArrayList<VehicleReturnModel> list) {
        IMinTime = list.get(list.size() - 1).getCopyTime();
    }

    private void transferTo(VehicleReturnModel model) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("VehicleReturnModel", model);
        //用车
        if (model.getApplicationType().contains("用车申请") || model.getApplicationType().contains("用车")) {

            if (model.getIsBack().equals("1")) {
                isNeedRefresh = false;
                startActivity(VehicleReturnUseCompleteActivity.class, bundle);//用车-已交车

            } else {
                Log.d("SJY", "用车-未交车");
                isNeedRefresh = true;
                startActivity(VehicleReturnUseUncompleteActivity.class, bundle);//用车-未交车
            }
        }
        //维保
        if (model.getApplicationType().contains("车辆维保") || model.getApplicationType().contains("维保")) {
            if (model.getIsBack().equals("1")) {
                Log.d("SJY", "维保-已交车");
                isNeedRefresh = false;
                startActivity(VehicleReturnMaintenanceCompleteActivity.class, bundle);//维保-已交车

            } else {
                Log.d("SJY", "维保-未交车");
                isNeedRefresh = true;
                startActivity(VehicleReturnMaintenanceUncompleteActivity.class, bundle);//维保-未交车
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isNeedRefresh) {
            Log.d("SJY", "交车数据刷新处理");
            vAdapter = new VehicleReturnLoadMoreListAdapter(this, adapterCallBack);// 上拉加载
            myListView.setAdapter(vAdapter);
            getData();
        }
    }

    /**
     * back
     *
     * @param view
     */
    public void forBack(View view) {
        this.finish();
    }


}
