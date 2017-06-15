package com.huirong.model;

import java.io.Serializable;
import java.util.List;

/**
 * 获取整个公司所有分公司员工信息
 * Created by sjy on 2017/1/6.
 */

public class ContactsSonCOModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private String sStoreID;//分公司ID
    private String sStoreName;//分公司名称
    private List<ContactsEmployeeModel> obj;//员工信息
    private String FirstLetter;//首字母设置

    public String getFirstLetter() {
        return FirstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        FirstLetter = firstLetter;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getsStoreID() {
        return sStoreID;
    }

    public void setsStoreID(String sStoreID) {
        this.sStoreID = sStoreID;
    }

    public String getsStoreName() {
        return sStoreName;
    }

    public void setsStoreName(String sStoreName) {
        this.sStoreName = sStoreName;
    }

    public List<ContactsEmployeeModel> getObj() {
        return obj;
    }

    public void setObj(List<ContactsEmployeeModel> obj) {
        this.obj = obj;
    }
}
