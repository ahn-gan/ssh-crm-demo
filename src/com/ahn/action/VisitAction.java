package com.ahn.action;

import com.ahn.entity.Customer;
import com.ahn.entity.User;
import com.ahn.entity.Visit;
import com.ahn.service.CustomerService;
import com.ahn.service.UserService;
import com.ahn.service.VisitService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;

import java.util.List;

public class VisitAction extends ActionSupport implements ModelDriven<Visit> {

    //ģ��������װ
    private Visit visit=new Visit();
    public Visit getModel() {
        return visit;
    }
    private VisitService visitService;

    public void setVisitService(VisitService visitService) {
        this.visitService = visitService;
    }

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    private CustomerService customerService;

    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    //����ӷ��ʼ�¼ҳ��
    public String toAddPage(){

        //��ȡ���е��û�����ϵ�˼���
        List<User> userList= userService.findAll();

        List<Customer> customerList=customerService.findall();
        //�����ݱ������������
        ServletActionContext.getRequest().setAttribute("userList",userList);
        ServletActionContext.getRequest().setAttribute("customerList",customerList);
        return "toadd";
    }

    //��Ӳ���
    public String insert(){
        //�������ݶ���
        visitService.insert(visit);
        return "insert";
    }
    //��¼�б���ʾ
    public String list(){
        List<Visit> list=visitService.findAll();
        //�����ݱ��浽������У�no session ����ͨ���ӳٹر�session�����õ��Խ����
        ServletActionContext.getRequest().setAttribute("list",list);
        return "list";
    }
    //�޸�ҳ��
    public String showedit(){
        //����ȡ����vidֵ
        int vid=visit.getVid();
        //��ø÷��ʼ�¼
        Visit v=visitService.findOneById(vid);
        //�����¼����
        ServletActionContext.getRequest().setAttribute("visit",v);
        //��ȡ���е��û��Ϳͻ�����
        List<User> userList= userService.findAll();
        List<Customer> customerList=customerService.findall();
        //���������ݱ������������
        ServletActionContext.getRequest().setAttribute("userList",userList);
        ServletActionContext.getRequest().setAttribute("customerList",customerList);
        return "showedit";
    }

    //ִ���޸Ĳ���
    public String edit(){
        visitService.update(visit);
        return "edit";
    }

}
