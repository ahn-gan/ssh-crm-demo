package com.ahn.dao;

import com.ahn.entity.User;
import org.springframework.orm.hibernate5.HibernateTemplate;

import java.util.List;

public class UserDaoImpl implements UserDao {

    private HibernateTemplate hibernateTemplate;

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @SuppressWarnings("all")
    public User login(User user) {
        //利用HibernateTemplate对象进行数据库的操作
        List<User> list=(List<User>)this.hibernateTemplate.find("from User where username=? and password=?",user.getUsername(),user.getPassword());
        if(list!=null && list.size()!=0){
            User u=list.get(0);
            return u;
        } else
            return null;
    }
}
