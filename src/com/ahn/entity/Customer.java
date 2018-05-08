package com.ahn.entity;

import java.util.HashSet;
import java.util.Set;

public class Customer {

    //Ĭ����������id
    private Integer cid;
    private String custName;//�ͻ�����
/*    private String custLevel;//�ͻ�����*/
    private String custSource;//��Դ
    private String custLinkman;//��Ӧ����ϵ��
    private String custPhone;//�̶��绰
    private String custMobile;//�ƶ��绰
    private String custAddress;//��ַ
    private String custZip;//��������
    private String custFax;//����
    private String custWebsite;//�ͻ���վ

    //�ͻ�����ͨ��������������ֵ��
    private Dict dictLevel;

    public Dict getDictLevel() {
        return dictLevel;
    }

    public void setDictLevel(Dict dictLevel) {
        this.dictLevel = dictLevel;
    }

    //һ���ͻ���Ӧ�ж������ʼ�¼
    private Set<Visit> cvisitSet=new HashSet<Visit>();

    public Set<Visit> getCvisitSet() {
        return cvisitSet;
    }

    public void setCvisitSet(Set<Visit> cvisitSet) {
        this.cvisitSet = cvisitSet;
    }

    //���ͻ�����ϵ���Ƕ��һ��ϵ���ͻ���Ϊ��һ������ϵ����Ϊ���һ����
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
