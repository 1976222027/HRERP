package com.huirong.ui.appsfrg.childmodel.finance;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.huirong.R;
import com.huirong.base.BaseActivity;
import com.huirong.inject.ViewInject;
import com.huirong.model.applicationdetailmodel.FinancialAllModel;

import java.util.List;

/**
 * 应用-财务-借款详情
 * Created by sjy on 2017/2/25.
 */

public class FinancialRemburseDetailActivity extends BaseActivity {

    //back
    @ViewInject(id = R.id.layout_back, click = "forBack")
    RelativeLayout layout_back;

    //
    @ViewInject(id = R.id.tv_title)
    TextView tv_title;

    //
    @ViewInject(id = R.id.tv_right)
    TextView tv_right;

    //获取子控件个数的父控件
    @ViewInject(id = R.id.layout_ll)
    LinearLayout layout_ll;


    //1
    @ViewInject(id = R.id.tv_feeOne)
    TextView tv_feeOne;
    //1
    @ViewInject(id = R.id.tv_useageOne)
    TextView tv_useageOne;
    //2
    @ViewInject(id = R.id.tv_feeTwo)
    TextView tv_feeTwo;
    //2
    @ViewInject(id = R.id.tv_useageTwo)
    TextView tv_useageTwo;
    //3
    @ViewInject(id = R.id.tv_feeThree)
    TextView tv_feeThree;
    //3
    @ViewInject(id = R.id.tv_useageThree)
    TextView tv_useageThree;
    //
    @ViewInject(id = R.id.tv_totle)
    TextView tv_totle;

    //备注
    @ViewInject(id = R.id.tv_remark, click = "RemarkExpended")
    TextView tv_remark;

    //申请人
    @ViewInject(id = R.id.tv_ApprovalPerson)
    TextView tv_ApprovalPerson;

    //部门
    @ViewInject(id = R.id.tv_approvaldept)
    TextView tv_approvaldept;

    //公司
    @ViewInject(id = R.id.tv_approvalCo)
    TextView tv_approvalCo;

    //申请时间
    @ViewInject(id = R.id.tv_approvalTime)
    TextView tv_approvalTime;

    //变量
    private FinancialAllModel model;
    private List<FinancialAllModel.ApprovalInfoLists> modelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_apps_financial_reimburse_detail);

        initMyView();
        setShow();
    }

    private void initMyView() {
        tv_title.setText(getResources().getString(R.string.financial_reimburse));
        tv_right.setText("");

        Bundle bundle = this.getIntent().getExtras();
        model = (FinancialAllModel) bundle.getSerializable("FinancialAllModel");
    }

    private void setShow() {
        //
        tv_ApprovalPerson.setText(model.getEmployeeName());
        tv_approvaldept.setText(model.getDepartmentName());
        tv_approvalCo.setText(model.getStoreName());
        tv_approvalTime.setText(model.getCreateTime());

        //
        tv_feeOne.setText(TextUtils.isEmpty(model.getJine1()) ? "无" : model.getJine1());
        tv_feeTwo.setText(TextUtils.isEmpty(model.getJine2()) ? "无" : model.getJine2());
        tv_feeThree.setText(TextUtils.isEmpty(model.getJine3()) ? "无" : model.getJine3());

        tv_useageOne.setText(TextUtils.isEmpty(model.getZhaiyao1()) ? "无" : model.getZhaiyao1());
        tv_useageTwo.setText(TextUtils.isEmpty(model.getZhaiyao2()) ? "无" : model.getZhaiyao2());
        tv_useageThree.setText(TextUtils.isEmpty(model.getZhaiyao3()) ? "无" : model.getZhaiyao3());

        tv_totle.setText(model.getHeji());
        tv_remark.setText(model.getRemark());

    }

    /**
     * back
     *
     * @param view
     */

    public void forBack(View view) {
        this.finish();
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
