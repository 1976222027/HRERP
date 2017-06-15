package com.huirong.model.applicationdetailmodel;

import java.io.Serializable;
import java.util.List;

/**
 * 用车
 * Created by sjy on 2016/12/26.
 */

public class VehicleModel implements Serializable {
    private static final long serialVersionUID = 1L;


    public String PlanBorrowTime= "";//计划用车时间
    public String PlanReturnTime= "";//计划交车时间
    public String ActualBorrowTime= "";//实际用车时间
    public String ActualReturnTime= "";//实际交车时间
    public String Purpose= "";//用车原因
    public String Remark;//申请备注
    public String Destination= "";//目的地

    public String Driver;//驾驶人
    public String CreateTime;//创建时间
    public String StoreID;
    public String Number;//车牌号
    public String StartMileage;//出车里程
    public String FinishMileage;//交车里程
    public String Passenger;//乘车人
    public String BackRemark;//乘车备注

    public String ApprovalStatus;
    public String ApplicationID;
    public String ApplicantDepartmentID;
    public String StoreName;
    public String DepartmentName;
    public String EmployeeName;
    public String ApplicationCreateTime;


    public List<VehicleModel.ApprovalInfoLists> ApprovalInfoLists;


    public String getApplicationID() {
        return ApplicationID;
    }

    public void setApplicationID(String applicationID) {
        ApplicationID = applicationID;
    }

    public String getApplicantDepartmentID() {
        return ApplicantDepartmentID;
    }

    public void setApplicantDepartmentID(String applicantDepartmentID) {
        ApplicantDepartmentID = applicantDepartmentID;
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

    public List<VehicleModel.ApprovalInfoLists> getApprovalInfoLists() {
        return ApprovalInfoLists;
    }

    public void setApprovalInfoLists(List<VehicleModel.ApprovalInfoLists> ApprovalInfoLists) {
        this.ApprovalInfoLists = ApprovalInfoLists;
    }

    public String getPlanBorrowTime() {
        return PlanBorrowTime;
    }

    public void setPlanBorrowTime(String planBorrowTime) {
        PlanBorrowTime = planBorrowTime;
    }

    public String getPlanReturnTime() {
        return PlanReturnTime;
    }

    public void setPlanReturnTime(String planReturnTime) {
        PlanReturnTime = planReturnTime;
    }

    public String getDestination() {
        return Destination;
    }

    public void setDestination(String destination) {
        Destination = destination;
    }

    public String getPurpose() {
        return Purpose;
    }

    public void setPurpose(String purpose) {
        Purpose = purpose;
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

    public String getActualBorrowTime() {
        return ActualBorrowTime;
    }

    public void setActualBorrowTime(String actualBorrowTime) {
        ActualBorrowTime = actualBorrowTime;
    }

    public String getActualReturnTime() {
        return ActualReturnTime;
    }

    public void setActualReturnTime(String actualReturnTime) {
        ActualReturnTime = actualReturnTime;
    }

    public String getDriver() {
        return Driver;
    }

    public void setDriver(String driver) {
        Driver = driver;
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

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getStartMileage() {
        return StartMileage;
    }

    public void setStartMileage(String startMileage) {
        StartMileage = startMileage;
    }

    public String getFinishMileage() {
        return FinishMileage;
    }

    public void setFinishMileage(String finishMileage) {
        FinishMileage = finishMileage;
    }

    public String getPassenger() {
        return Passenger;
    }

    public void setPassenger(String passenger) {
        Passenger = passenger;
    }

    public String getBackRemark() {
        return BackRemark;
    }

    public void setBackRemark(String backRemark) {
        BackRemark = backRemark;
    }
}
