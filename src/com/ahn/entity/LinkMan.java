package com.ahn.entity;

public class LinkMan {
    private Integer lid;//����������id
    private String lkmName;
    private String lkmGender;//�Ա�
    private String lkmPhone;//��ϵ�˰칫�绰
    private String lkmMobile;//��ϵ���ֻ�
    //�����ͻ����ͻ�����ϵ���Ƕ��һ��ϵ���ͻ���Ϊ��һ����
    private Customer customer;

    public Integer getLid() {
        return lid;
    }

    public void setLid(Integer lid) {
        this.lid = lid;
    }

    public String getLkmName() {
        return lkmName;
    }

    public void setLkmName(String lkmName) {
        this.lkmName = lkmName;
    }

    public String getLkmGender() {
        return lkmGender;
    }

    public void setLkmGender(String lkmGender) {
        this.lkmGender = lkmGender;
    }

    public String getLkmPhone() {
        return lkmPhone;
    }

    public void setLkmPhone(String lkmPhone) {
        this.lkmPhone = lkmPhone;
    }

    public String getLkmMobile() {
        return lkmMobile;
    }

    public void setLkmMobile(String lkmMobile) {
        this.lkmMobile = lkmMobile;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
