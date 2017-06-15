package com.huirong.ui.appsfrg.childmodel.examination.create;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huirong.R;
import com.huirong.base.BaseActivity;
import com.huirong.common.MyException;
import com.huirong.dialog.DateChooseWheelViewDialog;
import com.huirong.dialog.Loading;
import com.huirong.helper.UserHelper;
import com.huirong.inject.ViewInject;
import com.huirong.model.ContactsEmployeeModel;
import com.huirong.ui.appsfrg.childmodel.examination.ZOAplicationListActivity;
import com.huirong.utils.PageUtil;
import com.huirong.ui.contractsfrg.ContactsSelectActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * 加班申请
 */

public class WorkOverTimeActivity extends BaseActivity {

    //back
    @ViewInject(id = R.id.layout_back, click = "forBack")
    RelativeLayout layout_back;

    //
    @ViewInject(id = R.id.tv_title)
    TextView tv_title;

    //
    @ViewInject(id = R.id.tv_right, click = "forCommit")
    TextView tv_right;

    //开始时间
    @ViewInject(id = R.id.layout_startTime, click = "startTime")
    LinearLayout layout_startTime;
    @ViewInject(id = R.id.tv_timeStart)
    TextView tv_timeStart;


    //结束时间
    @ViewInject(id = R.id.layout_end, click = "endTime")
    LinearLayout layout_end;
    @ViewInject(id = R.id.tv_timeEnd)
    TextView tv_timeEnd;

    //说明
    @ViewInject(id = R.id.et_reason)
    EditText et_reason;

    //备注
    @ViewInject(id = R.id.et_remark)
    EditText et_remark;

    //人员
    @ViewInject(id = R.id.et_OverEmployee)
    EditText et_OverEmployee;

    //添加审批人
    @ViewInject(id = R.id.AddApprover, click = "forAddApprover")
    RelativeLayout AddApprover;

    //审批人
    @ViewInject(id = R.id.tv_Requester)
    TextView tv_Requester;


    //变量
    private String startDate;
    private String endDates;
    private String reason;
    private String overEmployee;
    private String remark = "";
    private String approvalID = "";


    //常量
    public static final int POST_SUCCESS = 17;
    public static final int POST_FAILED = 18;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_apps_examination_workovertime);
        tv_title.setText(getResources().getString(R.string.workOverTime));
    }

    /**
     * 提交
     */
    public void forCommit(View view) {
        reason = et_reason.getText().toString();
        remark = et_remark.getText().toString();
        overEmployee = et_OverEmployee.getText().toString().trim();
        if (TextUtils.isEmpty(startDate) || TextUtils.isEmpty(endDates)) {
            PageUtil.DisplayToast("请假时间不能为空");
            return;
        }
        if (TextUtils.isEmpty(reason)) {
            PageUtil.DisplayToast("加班原因不能为空");
            return;
        }
        if (TextUtils.isEmpty(overEmployee)) {
            PageUtil.DisplayToast("加班人员不能为空");
            return;
        }
        if (TextUtils.isEmpty(approvalID)) {
            PageUtil.DisplayToast("审批人不能为空");
            return;
        }
        Loading.run(WorkOverTimeActivity.this, new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject js = new JSONObject();
                    js.put("OverEmployee", overEmployee);
                    js.put("OverCause", reason);
                    js.put("StratOverTime", startDate);
                    js.put("EndOverTime", endDates);
                    js.put("ApprovalIDList", approvalID);

                    UserHelper.overApprovalPost(WorkOverTimeActivity.this, js);
                    sendMessage(POST_SUCCESS);
                } catch (MyException e) {
                    sendMessage(POST_FAILED, e.getMessage());

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void handleMessage(Message msg) {
        super.handleMessage(msg);
        switch (msg.what) {
            case POST_SUCCESS:
                PageUtil.DisplayToast(getResources().getString(R.string.approval_success));
                //                clear();
                startActivity(ZOAplicationListActivity.class);
                this.finish();
                break;
            case POST_FAILED:
                PageUtil.DisplayToast((String) msg.obj);
                break;
        }
    }

    private void clear() {
        tv_timeStart.setText("");
        tv_timeEnd.setText("");
        et_OverEmployee.setText("");
        et_reason.setText("");
        tv_Requester.setText("");
        et_remark.setText("");
        approvalID = null;
        startDate = null;
        endDates = null;
    }

    /**
     * 开始时间
     *
     * @param view
     */
    public void startTime(View view) {
        DateChooseWheelViewDialog endDateChooseDialog = new DateChooseWheelViewDialog(WorkOverTimeActivity.this,
                new DateChooseWheelViewDialog.DateChooseInterface() {
                    @Override
                    public void getDateTime(String time, boolean longTimeChecked) {
                        startDate = time;
                        tv_timeStart.setText(time);
                    }
                });
        //        endDateChooseDialog.setTimePickerGone(true);
        endDateChooseDialog.setDateDialogTitle("开始时间");
        endDateChooseDialog.showDateChooseDialog();
    }

    /**
     * 结束时间
     *
     * @param view
     */
    public void endTime(View view) {
        DateChooseWheelViewDialog endDateChooseDialog = new DateChooseWheelViewDialog(WorkOverTimeActivity.this,
                new DateChooseWheelViewDialog.DateChooseInterface() {
                    @Override
                    public void getDateTime(String time, boolean longTimeChecked) {
                        endDates = time;
                        tv_timeEnd.setText(time);
                    }
                });
        //        endDateChooseDialog.setTimePickerGone(true);
        endDateChooseDialog.setDateDialogTitle("结束时间");
        endDateChooseDialog.showDateChooseDialog();
    }

    /**
     * 添加审批人
     *
     * @param view
     */
    public void forAddApprover(View view) {
        myStartForResult(ContactsSelectActivity.class, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == 0)//通过请求码(去SActivity)和回传码（回传数据到第一个页面）判断回传的页面
        {
            //判断返回值是否为空
            List<ContactsEmployeeModel> list = new ArrayList<>();
            if (data != null && (List<ContactsEmployeeModel>) data.getSerializableExtra("data") != null) {
                list = (List<ContactsEmployeeModel>) data.getSerializableExtra("data");
            } else {

            }
            StringBuilder name = new StringBuilder();
            StringBuilder employeeId = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                name.append(list.get(i).getsEmployeeName() + "  ");
                employeeId.append(list.get(i).getsEmployeeID() + ",");
            }
            //            approvalID = "0280c9c5-870c-46cf-aa95-cdededc7d86c,88dd7959-cb2f-40c6-947a-4d6801fc4765";
            approvalID = getApprovalID(employeeId.toString());
            Log.d("SJY", "approvalID=" + approvalID);
            tv_Requester.setText(name);
        }

    }

    /*
     *处理字符串，去除末尾逗号
     */
    private String getApprovalID(String str) {
        if (str.length() > 1) {
            return str.substring(0, str.length() - 1);
        } else {
            return "";
        }
    }


    /**
     * back
     */
    public void forBack(View view) {
        this.finish();
    }
}
