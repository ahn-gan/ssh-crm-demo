package com.ahn.action;

import com.ahn.entity.User;
import com.ahn.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

//����action������action�Ļ���
public class UserAction extends ActionSupport {

    private UserService userService;
    //���Է�װ
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

    //��¼��֤
    public String login(){
        //���û���Ϣ��װ������
        User user=new User(null,username,password);
        //����service�ķ���
        User currentUser=userService.login(user);
        //�ж��û�
        if(currentUser!=null){//��ʾ�û����ڣ���¼�ɹ�
            //�����û��ĵ�¼״̬
            HttpServletRequest request=ServletActionContext.getRequest();
            request.getSession().setAttribute("user",currentUser);
            return "loginsuccess";
        }else//��¼ʧ��
            return "login";
    }
}
