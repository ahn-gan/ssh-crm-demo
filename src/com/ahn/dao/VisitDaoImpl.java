package com.ahn.dao;

import com.ahn.entity.Visit;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;

//��ҪHibernateTemplateģ����󣬿���ֱ�Ӽ̳�HibernateDaoSupport��õ�
public class VisitDaoImpl  extends HibernateDaoSupport implements VisitDao {

    @SuppressWarnings("all")
    public List<Visit> findAll() {
        List<Visit> list= (List<Visit>) this.getHibernateTemplate().find("from Visit");
        return list;
    }

    //��������
    public void insert(Visit visit) {
        this.getHibernateTemplate().save(visit);
    }

    //��ѯĳһ��¼
    public Visit findOneById(int vid) {
        Visit visit=this.getHibernateTemplate().get(Visit.class,vid);
        return visit;
    }

    //�޸ļ�¼
    public void update(Visit visit) {
        this.getHibernateTemplate().update(visit);
    }
}
