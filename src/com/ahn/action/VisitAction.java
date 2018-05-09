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

    //模型驱动封装
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

    //到添加访问记录页面
    public String toAddPage(){

        //获取所有的用户机联系人集合
        List<User> userList= userService.findAll();

        List<Customer> customerList=customerService.findall();
        //将数据保存仔域对象中
        ServletActionContext.getRequest().setAttribute("userList",userList);
        ServletActionContext.getRequest().setAttribute("customerList",customerList);
        return "toadd";
    }

    //添加操作
    public String insert(){
        //保存数据对象
        visitService.insert(visit);
        return "insert";
    }
    //记录列表显示
    public String list(){
        List<Visit> list=visitService.findAll();
        //将数据保存到域对象中（no session 问题通过延迟关闭session的配置得以解决）
        ServletActionContext.getRequest().setAttribute("list",list);
        return "list";
    }
    //修改页面
    public String showedit(){
        //将获取到的vid值
        int vid=visit.getVid();
        //获得该访问记录
        Visit v=visitService.findOneById(vid);
        //保存记录数据
        ServletActionContext.getRequest().setAttribute("visit",v);
        //获取所有的用户和客户集合
        List<User> userList= userService.findAll();
        List<Customer> customerList=customerService.findall();
        //将集合数据保存仔域对象中
        ServletActionContext.getRequest().setAttribute("userList",userList);
        ServletActionContext.getRequest().setAttribute("customerList",customerList);
        return "showedit";
    }

    //执行修改操作
    public String edit(){
        visitService.update(visit);
        return "edit";
    }

}
