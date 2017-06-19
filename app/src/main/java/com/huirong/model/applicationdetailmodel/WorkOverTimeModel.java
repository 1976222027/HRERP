package com.huirong.model.applicationdetailmodel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sjy on 2016/12/26.
 */

public class WorkOverTimeModel implements Serializable {
    private static final long serialVersionUID = 1L;

    public String ApprovalStatus;
    public String StoreName;//
    public String Remark;//
    public String DepartmentName;//
    public String EmployeeName;//
    public String ApplicationCreateTime;//


    public String OverEmployee;
    public String OverCause;
    public String StratOverTime;
    public String EndOverTime;


    public List<WorkOverTimeModel.ApprovalInfoLists> ApprovalInfoLists;


    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public static class ApprovalInfoLists implements Serializable{
        public String Comment;
        public String ApprovalDate;
        public String YesOrNo;
        public String ApprovalEmployeeName;

        public String getComment() {
            return Comment;
        }

        public void setComment(String comment) {
            Comment = comment;
        }

        public String getApprovalDate() {
            return ApprovalDate;
        }

        public void setApprovalDate(String approvalDate) {
            ApprovalDate = approvalDate;
        }

        public String getYesOrNo() {
            return YesOrNo;
        }

        public void setYesOrNo(String yesOrNo) {
            YesOrNo = yesOrNo;
        }

        public String getApprovalEmployeeName() {
            return ApprovalEmployeeName;
        }

        public void setApprovalEmployeeName(String approvalEmployeeName) {
            ApprovalEmployeeName = approvalEmployeeName;
        }
    }

    public List<WorkOverTimeModel.ApprovalInfoLists> getApprovalInfoLists() {
        return ApprovalInfoLists;
    }

    public void setApprovalInfoLists(List<WorkOverTimeModel.ApprovalInfoLists> ApprovalInfoLists) {
        this.ApprovalInfoLists = ApprovalInfoLists;
    }

    public String getOverEmployee() {
        return OverEmployee;
    }

    public void setOverEmployee(String overEmployee) {
        OverEmployee = overEmployee;
    }

    public String getOverCause() {
        return OverCause;
    }

    public void setOverCause(String overCause) {
        OverCause = overCause;
    }

    public String getStratOverTime() {
        return StratOverTime;
    }

    public void setStratOverTime(String stratOverTime) {
        StratOverTime = stratOverTime;
    }

    public String getEndOverTime() {
        return EndOverTime;
    }

    public void setEndOverTime(String endOverTime) {
        EndOverTime = endOverTime;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getApprovalStatus() {
        return ApprovalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        ApprovalStatus = approvalStatus;
    }

    public String getStoreName() {
        return StoreName;
    }

    public void setStoreName(String storeName) {
        StoreName = storeName;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public void setDepartmentName(String departmentName) {
        DepartmentName = departmentName;
    }

    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String employeeName) {
        EmployeeName = employeeName;
    }

    public String getApplicationCreateTime() {
        return ApplicationCreateTime;
    }

    public void setApplicationCreateTime(String applicationCreateTime) {
        ApplicationCreateTime = applicationCreateTime;
    }
}
