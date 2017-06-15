package com.huirong.model.approvaldetailmodel;

import java.io.Serializable;
import java.util.List;

/**
 * Created by sjy on 2016/12/26.
 */

public class TakeDaysOffApvlModel implements Serializable {
    private static final long serialVersionUID = 1L;

    public String StartOffDate;
    public String EndOffDate;
    public String EndTakeDate;
    public String StartTakeDate;
    public String Reason;
    public String Remark;

    public String ApprovalStatus;
    public String ApplicationTitle;
    public String EmployeeName;
    public String StoreName;
    public String DepartmentName;
    public String ApplicationCreateTime;

    public List<TakeDaysOffApvlModel.ApprovalInfoLists> ApprovalInfoLists;

    public String getApplicationTitle() {
        return ApplicationTitle;
    }

    public void setApplicationTitle(String applicationTitle) {
        ApplicationTitle = applicationTitle;
    }

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

    public List<TakeDaysOffApvlModel.ApprovalInfoLists> getApprovalInfoLists() {
        return ApprovalInfoLists;
    }

    public void setApprovalInfoLists(List<TakeDaysOffApvlModel.ApprovalInfoLists> ApprovalInfoLists) {
        this.ApprovalInfoLists = ApprovalInfoLists;
    }

    public String getStartTakeDate() {
        return StartTakeDate;
    }

    public void setStartTakeDate(String startTakeDate) {
        StartTakeDate = startTakeDate;
    }

    public String getEndTakeDate() {
        return EndTakeDate;
    }

    public void setEndTakeDate(String endTakeDate) {
        EndTakeDate = endTakeDate;
    }

    public String getStartOffDate() {
        return StartOffDate;
    }

    public void setStartOffDate(String startOffDate) {
        StartOffDate = startOffDate;
    }

    public String getEndOffDate() {
        return EndOffDate;
    }

    public void setEndOffDate(String endOffDate) {
        EndOffDate = endOffDate;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    public String getApprovalStatus() {
        return ApprovalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        ApprovalStatus = approvalStatus;
    }

    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String employeeName) {
        EmployeeName = employeeName;
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

    public String getApplicationCreateTime() {
        return ApplicationCreateTime;
    }

    public void setApplicationCreateTime(String applicationCreateTime) {
        ApplicationCreateTime = applicationCreateTime;
    }
}
