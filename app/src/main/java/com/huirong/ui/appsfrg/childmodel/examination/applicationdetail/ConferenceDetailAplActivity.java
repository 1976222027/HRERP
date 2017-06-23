package com.huirong.ui.appsfrg.childmodel.examination.applicationdetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huirong.R;
import com.huirong.base.BaseActivity;
import com.huirong.common.MyException;
import com.huirong.dialog.Loading;
import com.huirong.helper.UserHelper;
import com.huirong.inject.ViewInject;
import com.huirong.model.MyApplicationModel;
import com.huirong.model.applicationdetailmodel.ConferenceModel;
import com.huirong.utils.PageUtil;

import java.util.ArrayList;
import java.util.List;

import static com.huirong.R.id.tv_contains;

/**
 * 申请 会议详情
 * Created by sjy on 2017/1/16.
 */

public class ConferenceDetailAplActivity extends BaseActivity {
    //back
    @ViewInject(id = R.id.layout_back, click = "forBack")
    RelativeLayout layout_back;

    //
    @ViewInject(id = R.id.tv_title)
    TextView tv_title;

    //
    @ViewInject(id = R.id.tv_right)
    TextView tv_right;

    //审批人
    @ViewInject(id = R.id.tv_Requester)
    TextView tv_Requester;

    //审批状况
    @ViewInject(id = R.id.tv_state_result)
    TextView tv_state_result;
    @ViewInject(id = R.id.layout_state, click = "forState")
    LinearLayout layout_state;

    //会议标题
    @ViewInject(id = R.id.tv_conference_name)
    TextView tv_conference_name;

    //会议主题
    @ViewInject(id = R.id.tv_conference_title)
    TextView tv_conference_title;

    //准备
    @ViewInject(id = R.id.tv_conference_Device, click = "DeviceExpended")
    TextView tv_conference_Device;

    //简介
    @ViewInject(id = R.id.tv_conference_Abstract, click = "AbstractExpended")
    TextView tv_conference_Abstract;

    //备注
    @ViewInject(id = R.id.tv_remark, click = "RemarkExpended")
    TextView tv_remark;

    //开始
    @ViewInject(id = R.id.tv_conference_start)
    TextView tv_conference_start;

    //结束
    @ViewInject(id = R.id.tv_conference_end)
    TextView tv_conference_end;


    //获取子控件个数的父控件
    @ViewInject(id = R.id.layout_ll)
    LinearLayout layout_ll;

    //变量
    private ConferenceModel conferenceModel;
    //动态添加view
    private List<View> ls_childView;//用于保存动态添加进来的View
    private List<ViewHolder> listViewHolder = new ArrayList<>();
    //    private int mark = 5;//0显示在顶部
    //常量
    public static final int POST_SUCCESS = 11;
    public static final int POST_FAILED = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_apps_examination_conference_d);
        tv_title.setText(getResources().getString(R.string.conference));
        tv_right.setText("");

        Intent intent = getIntent();
        MyApplicationModel model = (MyApplicationModel) intent.getSerializableExtra("MyApplicationModel");
        getDetailModel(model);
    }


    private void setShow(ConferenceModel model) {
        //
        tv_conference_name.setText(model.getConferenceName());
        tv_conference_title.setText(model.getTitle());
        tv_conference_Device.setText(model.getDeviceName());
        tv_conference_Abstract.setText(model.getAbstract());
        tv_conference_start.setText(model.getStartTime());
        tv_conference_end.setText(model.getFinishTime());
        tv_remark.setText(model.getRemark());

        // 审批人
        List<ConferenceModel.ApprovalInfoLists> modelList = model.getApprovalInfoLists();
        StringBuilder nameBuilder = new StringBuilder();
        for (int i = 0; i < modelList.size(); i++) {
            nameBuilder.append(modelList.get(i).getApprovalEmployeeName() + " ");
        }
        tv_Requester.setText(nameBuilder);

        //审批状态
        if (conferenceModel.getApprovalStatus().contains("0")) {
            tv_state_result.setText("未审批");
            tv_state_result.setTextColor(getResources().getColor(R.color.red));
        } else if (conferenceModel.getApprovalStatus().contains("1")) {
            tv_state_result.setText("已审批");
            tv_state_result.setTextColor(getResources().getColor(R.color.green));
        } else if (conferenceModel.getApprovalStatus().contains("2")) {
            tv_state_result.setText("审批中...");
            tv_state_result.setTextColor(getResources().getColor(R.color.black));
        } else {
            tv_state_result.setText("你猜猜！");
        }


        if (conferenceModel.getApprovalStatus().contains("1") || conferenceModel.getApprovalStatus().contains("2")) {
            //插入意见
            for (int i = 0, mark = layout_ll.getChildCount(); i < modelList.size(); i++, mark++) {//mark是布局插入位置，放在mark位置的后边（从1开始计数）
                ViewHolder vh = AddView(this, mark);//添加布局
                vh.tv_name.setText(modelList.get(i).getApprovalEmployeeName());
                vh.tv_time.setText(modelList.get(i).getApprovalDate());
                vh.tv_contains.setText(modelList.get(i).getComment());
                if (modelList.get(i).getYesOrNo().contains("0")) {
                    vh.tv_yesOrNo.setText("不同意");
                    vh.tv_yesOrNo.setTextColor(getResources().getColor(R.color.red));
                } else if (TextUtils.isEmpty(modelList.get(i).getYesOrNo())) {
                    vh.tv_yesOrNo.setText("未审批");
                    vh.tv_yesOrNo.setTextColor(getResources().getColor(R.color.red));
                } else if ((modelList.get(i).getYesOrNo().contains("1"))) {
                    vh.tv_yesOrNo.setText("同意");
                    vh.tv_yesOrNo.setTextColor(getResources().getColor(R.color.green));
                } else {
                    vh.tv_yesOrNo.setText("");
                }
            }
        }

    }

    /**
     * 获取详情数据
     */
    public void getDetailModel(final MyApplicationModel model) {

        Loading.run(this, new Runnable() {
            @Override
            public void run() {

                //泛型
                try {
                    ConferenceModel model1 = new UserHelper<ConferenceModel>(ConferenceModel.class)
                            .applicationDetailPost(ConferenceDetailAplActivity.this,
                                    model.getApplicationID(),
                                    model.getApplicationType());
                    sendMessage(POST_SUCCESS, model1);
                } catch (MyException e) {
                    e.printStackTrace();
                    sendMessage(POST_FAILED, e.getMessage());
                }
            }
        });
    }

    @Override
    protected void handleMessage(Message msg) {
        super.handleMessage(msg);
        switch (msg.what) {
            case POST_SUCCESS:
                conferenceModel = (ConferenceModel) msg.obj;
                setShow(conferenceModel);
                break;
            case POST_FAILED: // 1001
                PageUtil.DisplayToast((String) msg.obj);
                break;
            default:
                break;
        }
    }

    public class ViewHolder {
        private int id = -1;
        private TextView tv_name;
        private TextView tv_yesOrNo;
        private TextView tv_time;
        private TextView tv_contains;
    }

    private ViewHolder AddView(Context context, int marks) {
        ls_childView = new ArrayList<View>();
        LayoutInflater inflater = LayoutInflater.from(context);
        View childView = inflater.inflate(R.layout.item_examination_status, new LinearLayout(context), false);
        childView.setId(marks);
        layout_ll.addView(childView, marks);
        return getViewInstance(childView);

    }

    private ViewHolder getViewInstance(View childView) {
        ViewHolder vh = new ViewHolder();
        vh.id = childView.getId();
        vh.tv_name = (TextView) childView.findViewById(R.id.tv_name);
        vh.tv_yesOrNo = (TextView) childView.findViewById(R.id.tv_yesOrNo);
        vh.tv_time = (TextView) childView.findViewById(R.id.tv_time);
        vh.tv_contains = (TextView) childView.findViewById(tv_contains);
        listViewHolder.add(vh);
        ls_childView.add(childView);
        return vh;
    }

    /**
     * back
     *
     * @param view
     */
    public void forBack(View view) {
        this.finish();
    }

    private boolean isDeviceExpend = false;

    public void DeviceExpended(View view) {
        if (!isDeviceExpend) {
            tv_conference_Device.setMinLines(0);
            tv_conference_Device.setMaxLines(Integer.MAX_VALUE);
            isDeviceExpend = true;
        } else {
            tv_conference_Device.setLines(3);
            isDeviceExpend = false;
        }

    }

    private boolean isAbstractExpended = false;

    public void AbstractExpended(View view) {
        if (!isAbstractExpended) {
            tv_conference_Abstract.setMinLines(0);
            tv_conference_Abstract.setMaxLines(Integer.MAX_VALUE);
            isAbstractExpended = true;
        } else {
            tv_conference_Abstract.setLines(3);
            isAbstractExpended = false;
        }

    }

    private boolean isRemarkExpend = false;

    public void RemarkExpended(View view) {
        if (!isRemarkExpend) {
            tv_remark.setMinLines(0);
            tv_remark.setMaxLines(Integer.MAX_VALUE);
            isRemarkExpend = true;
        } else {
            tv_remark.setLines(3);
            isRemarkExpend = false;
        }

    }
}
