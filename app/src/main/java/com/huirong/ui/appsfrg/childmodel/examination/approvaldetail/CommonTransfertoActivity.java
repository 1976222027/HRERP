package com.huirong.ui.appsfrg.childmodel.examination.approvaldetail;

import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huirong.R;
import com.huirong.adapter.ContactsSelectAdapter;
import com.huirong.application.MyApplication;
import com.huirong.base.BaseActivity;
import com.huirong.common.CharacterParser;
import com.huirong.common.MyException;
import com.huirong.common.PinyinComparator;
import com.huirong.dialog.Loading;
import com.huirong.helper.UserHelper;
import com.huirong.inject.ViewInject;
import com.huirong.model.ApprovalSModel;
import com.huirong.model.ContactsEmployeeModel;
import com.huirong.model.MyApprovalModel;
import com.huirong.utils.ConfigUtil;
import com.huirong.utils.PageUtil;
import com.huirong.widget.SideBar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 审批-转交(转交通讯录和审批-申请添加审批人的通讯录一样)
 * <p>
 * listView绑定checkBox
 * <p>
 * Created by sjy on 2017/1/17.
 */

public class CommonTransfertoActivity extends BaseActivity {
    //back
    @ViewInject(id = R.id.layout_back, click = "forBack")
    RelativeLayout layout_back;
    //
    @ViewInject(id = R.id.tv_title)
    TextView tv_title;

    //
    @ViewInject(id = R.id.tv_right, click = "forCopyto")
    TextView tv_right;

    //listView
    @ViewInject(id = R.id.country_lvcountry)
    ListView contactsListView;

    //变量
    private MyApprovalModel myApprovalModel;//跳转对象
    private ApprovalSModel approvalSModel;//传送对象
    private String sApprovalemployeeinfos;//转发人ApprovalEmployeeID

    private SideBar sideBar;
    private CharacterParser characterParser;// 汉字转换成拼音的类
    private PinyinComparator pinyinComparator;// 根据拼音来排列ListView里面的数据类

    private static List<ContactsEmployeeModel> listContactApprover;//审批人通讯录 集合
    public static List<ContactsEmployeeModel> selectlist;//checkBox选中数据集合

    private ContactsSelectAdapter adapter;//转交通讯录适配

    //常量
    public static final int POST_SUCCESEE = 15;
    public static final int POST_FAILED = 16;
    public static final int CHASE_DATA = 17;
    public static final int POSTDATA_SUCCESS = 18;//数据转交

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_apps_examination_myapproval_common_contacts2);

        tv_title.setText(getResources().getString(R.string.examination_transfer));
        tv_right.setText(getResources().getString(R.string.examination_requester_sure));

        //获取跳转对象
        myApprovalModel = (MyApprovalModel) getIntent().getSerializableExtra("MyApprovalModel");

        initViews();
        initListener();
        getContactApprover();

        MyApplication.getInstance().addACT(this);
    }

    /**
     *
     */
    private void initViews() {
        //实例化汉字转拼音类
        characterParser = CharacterParser.getInstance();
        pinyinComparator = new PinyinComparator();
        sideBar = (SideBar) findViewById(R.id.sidrbar);
    }

    /**
     * 控件监听
     */

    private void initListener() {

        //设置右侧触摸监听
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {

            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    contactsListView.setSelection(position);
                }

            }
        });

        //checkbox绑定列表监听
        contactsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //判断view是否相等
                if (view.getTag() instanceof ContactsSelectAdapter.MyViewHolder) {
                    //如果是的话，重用
                    ContactsSelectAdapter.MyViewHolder holder = (ContactsSelectAdapter.MyViewHolder) view.getTag();
                    //自动触发
                    holder.selectCheck.toggle();
                }
            }
        });

    }

    public void getContactApprover() {
        //先判断sp中是否有值
        ConfigUtil config = new ConfigUtil(MyApplication.getInstance());
        List<ContactsEmployeeModel> list = config.getContactApproverData();
        if (list.size() > 0 && list != null) {
            Log.d("SJY", "走sp缓存");
            sendMessage(CHASE_DATA, list);

        } else if (list == null || list.size() <= 0) {
            Log.d("SJY", "走服务端数据");
            //获取服务端数据
            getDataFromURL();

        }
    }

    public void getDataFromURL() {
        //
        Loading.run(this, new Runnable() {
            @Override
            public void run() {
                try {
                    List<ContactsEmployeeModel> list = UserHelper.getContactsSelectCo(MyApplication.getInstance());
                    sendMessage(POST_SUCCESEE, list);
                } catch (MyException e) {
                    sendMessage(POST_FAILED, e.getMessage());
                }
            }
        });
    }

    @Override
    protected void handleMessage(Message msg) {
        super.handleMessage(msg);
        switch (msg.what) {
            case POST_SUCCESEE://
                List<ContactsEmployeeModel> listHandler = (List<ContactsEmployeeModel>) msg.obj;
                listContactApprover = filledData(listHandler);//为数据添加首字母
                //数据保存
                ConfigUtil config = new ConfigUtil(MyApplication.getInstance());
                config.setContactApproverData(listContactApprover);
                // 根据a-z进行排序源数据
                Collections.sort(listContactApprover, pinyinComparator);

                //界面展示
                adapter = new ContactsSelectAdapter(CommonTransfertoActivity.this, listContactApprover);
                contactsListView.setAdapter(adapter);
                break;
            case POST_FAILED://
                PageUtil.DisplayToast((String) msg.obj);
                break;
            case CHASE_DATA:
                List<ContactsEmployeeModel> listData = (List<ContactsEmployeeModel>) msg.obj;
                listContactApprover = filledData(listData);//为数据添加首字母
                // 根据a-z进行排序源数据
                Collections.sort(listContactApprover, pinyinComparator);

                //界面展示
                adapter = new ContactsSelectAdapter(CommonTransfertoActivity.this, listContactApprover);
                contactsListView.setAdapter(adapter);
                break;

            case POSTDATA_SUCCESS:
                PageUtil.DisplayToast((String) msg.obj);
                MyApplication.getInstance().closeACT();
                break;
            default:
                break;
        }
    }


    /**
     * 重新修改model,为ListView填充首字母数据
     *
     * @return
     */
    private List<ContactsEmployeeModel> filledData(List<ContactsEmployeeModel> listdata) {
        List<ContactsEmployeeModel> mSortList = new ArrayList<ContactsEmployeeModel>();

        for (int i = 0; i < listdata.size(); i++) {

            //汉字转换成拼音
            String pinyin = characterParser.getSelling(listdata.get(i).getsEmployeeName());
            String sortString = pinyin.substring(0, 1).toUpperCase();

            // 正则表达式，判断首字母是否是英文字母
            if (sortString.matches("[A-Z]")) {
                listdata.get(i).setFirstLetter(sortString.toUpperCase());
            } else {
                listdata.get(i).setFirstLetter("#");
            }

            mSortList.add(listdata.get(i));
        }
        return mSortList;

    }

    /**
     * 根据搜索框中的值来过滤数据并更新ListView
     *
     * @param filterStr
     */
    private void filterData(String filterStr) {
        List<ContactsEmployeeModel> filterDateList = new ArrayList<ContactsEmployeeModel>();

        if (TextUtils.isEmpty(filterStr)) {
            filterDateList = listContactApprover;
        } else {
            filterDateList.clear();
            for (ContactsEmployeeModel sortModel : listContactApprover) {
                String name = sortModel.getsEmployeeName();

                if (name.indexOf(filterStr.toString()) != -1 || characterParser.getSelling(name).startsWith(filterStr.toString())) {
                    filterDateList.add(sortModel);
                }
            }
        }

        // 根据a-z进行排序
        Collections.sort(filterDateList, pinyinComparator);
        adapter.updateListView(filterDateList);
    }

    /**
     * 转交
     *
     * @param view
     */
    public void forCopyto(View view) {
        selectlist = getSelectList(listContactApprover);//获取转交人
        sApprovalemployeeinfos = getList2String(selectlist);//获取
        Log.d("SJY", "转发-确定sApprovalemployeeinfos=" + sApprovalemployeeinfos);

        if (TextUtils.isEmpty(sApprovalemployeeinfos)) {
            PageUtil.DisplayToast("转发人不能为空");

        }

        //对象赋值处理
        approvalSModel = new ApprovalSModel();
        approvalSModel.setsApprovalid(myApprovalModel.getApprovalID());
        approvalSModel.setsComment(myApprovalModel.getComment());
        approvalSModel.setsApplicationid(myApprovalModel.getApplicationID());
        approvalSModel.setsApplicationtype(myApprovalModel.getApplicationType());
        approvalSModel.setsEmployeeid(myApprovalModel.getEmployeeID());
        approvalSModel.setsStoreid(myApprovalModel.getStoreID());
        approvalSModel.setsApplicationtitle(myApprovalModel.getApplicationTitle());
        approvalSModel.setsApprovalemployeeinfos(sApprovalemployeeinfos);

        Loading.run(this, new Runnable() {
            @Override
            public void run() {

                try {
                    String message = UserHelper.transfortoMyApproval(CommonTransfertoActivity.this, approvalSModel);
                    sendMessage(POSTDATA_SUCCESS, message);
                } catch (MyException e) {
                    sendMessage(POST_FAILED, e.getMessage());
                }
            }
        });
    }


    /**
     * 获取选择的转交人
     *
     * @param list
     * @return
     */
    private List<ContactsEmployeeModel> getSelectList(List<ContactsEmployeeModel> list) {
        List<ContactsEmployeeModel> checkBoxList = new ArrayList<>();
        //遍历
        for (int i = 0; i < list.size(); i++) {
            if (ContactsSelectAdapter.getIsSelectedMap().get(i) == true) {
                checkBoxList.add(list.get(i));
                Log.d("SJY", "选中的checkbox位置=" + i + "checkbox选中数据长度=" + checkBoxList.size());
            }
        }
        return checkBoxList;
    }

    /**
     * 对参数处理
     *
     * @param list
     * @return
     */
    private String getList2String(List<ContactsEmployeeModel> list) {
        StringBuilder orgString = new StringBuilder();
        //遍历
        for (int i = 0; i < list.size(); i++) {
            orgString.append(list.get(i).getsEmployeeID() + ",");
        }
        //去除末尾逗号
        return orgString.substring(0, orgString.length() - 1);
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
