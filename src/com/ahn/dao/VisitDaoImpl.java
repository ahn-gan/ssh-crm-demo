package com.ahn.dao;

import com.ahn.entity.Visit;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

//需要HibernateTemplate模板对象，可以直接继承HibernateDaoSupport类得到
public class VisitDaoImpl  extends HibernateDaoSupport implements VisitDao {

    @SuppressWarnings("all")
    public List<Visit> findAll() {
        List<Visit> list= (List<Visit>) this.getHibernateTemplate().find("from Visit");
        return list;
    }

    //保存数据
    public void insert(Visit visit) {
        this.getHibernateTemplate().save(visit);
    }

    //查询某一记录
    public Visit findOneById(int vid) {
        Visit visit=this.getHibernateTemplate().get(Visit.class,vid);
        return visit;
    }

    //修改记录
    public void update(Visit visit) {
        this.getHibernateTemplate().update(visit);
    }
}
