package com.huirong.model;

import java.io.Serializable;
import java.util.List;

/**
 * 获取分公司所有部门员工信息
 * Created by sjy on 2017/1/6.
 */

public class ContactsDeptModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private String sDeptID;//部门ID
    private String sDeptName;//部门名称
    private List<ContactsEmployeeModel> obj;//员工信息
    private String FirstLetter;

    public String getFirstLetter() {
        return FirstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        FirstLetter = firstLetter;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getsDeptID() {
        return sDeptID;
    }

    public void setsDeptID(String sDeptID) {
        this.sDeptID = sDeptID;
    }

    public String getsDeptName() {
        return sDeptName;
    }

    public void setsDeptName(String sDeptName) {
        this.sDeptName = sDeptName;
    }

    public List<com.huirong.model.ContactsEmployeeModel> getObj() {
        return obj;
    }

    public void setObj(List<com.huirong.model.ContactsEmployeeModel> obj) {
        this.obj = obj;
    }
}
