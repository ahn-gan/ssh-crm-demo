package com.ahn.entity;

import java.util.HashSet;
import java.util.Set;

public class Customer {

    //默认自增长的id
    private Integer cid;
    private String custName;//客户姓名
/*    private String custLevel;//客户级别*/
    private String custSource;//来源
    private String custLinkman;//对应的联系人
    private String custPhone;//固定电话
    private String custMobile;//移动电话
    private String custAddress;//地址
    private String custZip;//邮政编码
    private String custFax;//传真
    private String custWebsite;//客户网站

    //客户级别通过外键连接数据字典表
    private Dict dictLevel;

    public Dict getDictLevel() {
        return dictLevel;
    }

    public void setDictLevel(Dict dictLevel) {
        this.dictLevel = dictLevel;
    }

    //一个客户对应有多条访问记录
    private Set<Visit> cvisitSet=new HashSet<Visit>();

    public Set<Visit> getCvisitSet() {
        return cvisitSet;
    }

    public void setCvisitSet(Set<Visit> cvisitSet) {
        this.cvisitSet = cvisitSet;
    }

    //（客户对联系人是多对一关系，客户作为“一”，联系人作为多的一方）
    private Set<LinkMan> linkManSet=new HashSet<LinkMan>();

    public Set<LinkMan> getLinkManSet() {
        return linkManSet;
    }

    public void setLinkManSet(Set<LinkMan> linkManSet) {
        this.linkManSet = linkManSet;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

/*    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel;
    }*/

    public String getCustSource() {
        return custSource;
    }

    public void setCustSource(String custSource) {
        this.custSource = custSource;
    }

    public String getCustLinkman() {
        return custLinkman;
    }

    public void setCustLinkman(String custLinkman) {
        this.custLinkman = custLinkman;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    public String getCustMobile() {
        return custMobile;
    }

    public void setCustMobile(String custMobile) {
        this.custMobile = custMobile;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    public String getCustZip() {
        return custZip;
    }

    public void setCustZip(String custZip) {
        this.custZip = custZip;
    }

    public String getCustFax() {
        return custFax;
    }

    public void setCustFax(String custFax) {
        this.custFax = custFax;
    }

    public String getCustWebsite() {
        return custWebsite;
    }

    public void setCustWebsite(String custWebsite) {
        this.custWebsite = custWebsite;
    }
}
