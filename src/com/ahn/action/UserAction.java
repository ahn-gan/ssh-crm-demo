package com.ahn.action;

import com.ahn.entity.User;
import com.ahn.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

//创建action，配置action的环境
public class UserAction extends ActionSupport {

    private UserService userService;
    //属性封装
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    //登录验证
    public String login(){
        //将用户信息封装到对象
        User user=new User(null,username,password);
        //调用service的方法
        User currentUser=userService.login(user);
        //判断用户
        if(currentUser!=null){//表示用户存在，登录成功
            //保存用户的登录状态
            HttpServletRequest request=ServletActionContext.getRequest();
            request.getSession().setAttribute("user",currentUser);
            return "loginsuccess";
        }else//登录失败
            return "login";
    }
}
