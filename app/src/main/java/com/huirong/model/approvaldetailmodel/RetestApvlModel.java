package com.huirong.model.approvaldetailmodel;

import java.io.Serializable;
import java.util.List;

/**
 * 外出
 * Created by sjy on 2016/12/26.
 */

public class RetestApvlModel implements Serializable {
    private static final long serialVersionUID = 1L;

    public String Reexpeople;
    public String Remark;

    public String ApprovalStatus;
    public String EmployeeName;
    public String StoreName;
    public String DepartmentName;
    public String ApplicationCreateTime;
    public List<RetestApvlModel.ApprovalInfoLists> ApprovalInfoLists;

    //未用
    public String EmployeeID;
    public String CreateTime;
    public String StoreID;

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

    public List<RetestApvlModel.ApprovalInfoLists> getApprovalInfoLists() {
        return ApprovalInfoLists;
    }

    public void setApprovalInfoLists(List<RetestApvlModel.ApprovalInfoLists> approvalInfoLists) {
        this.ApprovalInfoLists = approvalInfoLists;
    }



    public String getEmployeeName() {
        return EmployeeName;
    }

    public void setEmployeeName(String employeeName) {
        EmployeeName = employeeName;
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

    public String getApplicationCreateTime() {
        return ApplicationCreateTime;
    }

    public void setApplicationCreateTime(String applicationCreateTime) {
        ApplicationCreateTime = applicationCreateTime;
    }


    public String getRemark() {
        return Remark;
    }

    public void setRemark(String remark) {
        Remark = remark;
    }

    public String getEmployeeID() {
        return EmployeeID;
    }

    public void setEmployeeID(String employeeID) {
        EmployeeID = employeeID;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getStoreID() {
        return StoreID;
    }

    public void setStoreID(String storeID) {
        StoreID = storeID;
    }

    public String getReexpeople() {
        return Reexpeople;
    }

    public void setReexpeople(String reexpeople) {
        Reexpeople = reexpeople;
    }
}
