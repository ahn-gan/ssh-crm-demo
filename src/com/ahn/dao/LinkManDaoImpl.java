package com.ahn.dao;

import com.ahn.entity.LinkMan;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.ArrayList;
import java.util.List;

public class LinkManDaoImpl extends HibernateDaoSupport implements LinkManDao {

    //�����ϵ�˲���
    public void insert(LinkMan linkMan) {
        this.getHibernateTemplate().save(linkMan);
    }

    //�õ�������ϵ�˼���
    @SuppressWarnings("all")
    public List<LinkMan> list() {
        return (List<LinkMan>) this.getHibernateTemplate().find("from LinkMan");
    }

    //����id��ѯ��ϵ��
    public LinkMan fineOneById(int lid) {
        //���linkMan����
        LinkMan linkMan=this.getHibernateTemplate().get(LinkMan.class,lid);
        return linkMan;
    }

    //���·���
    public void update(LinkMan linkMan) {
        //�����id����update
        this.getHibernateTemplate().update(linkMan);
    }

/*    //������ѯ��hql��ʽ
    @SuppressWarnings("all")
    public List<LinkMan> query(LinkMan linkMan) {

        String hql="from LinkMan where 1=1";
        //Ϊ��ѯ��䴫�����
        List<Object> param=new ArrayList<Object>();
        //�жϲ�ѯ�����Ƿ�Ϊ��
        if(linkMan.getLkmName()!=null&&linkMan.getLkmName()!=""){
            hql+=" and lkmName like ?";
            param.add("%"+linkMan.getLkmName()+"%");
        }
        if(linkMan.getCustomer().getCid()!=null&&linkMan.getCustomer().getCid()>0){
            hql+=" and customer.cid=?";
            param.add(linkMan.getCustomer().getCid());
        }
        return (List<LinkMan>) this.getHibernateTemplate().find(hql,param.toArray());
    }*/
    //������ѯ�����߶���ʽ
    @SuppressWarnings("all")
    public List<LinkMan> query(LinkMan linkMan) {
        //�������߶���
        DetachedCriteria criteria=DetachedCriteria.forClass(LinkMan.class);
        //�жϲ�ѯ�����Ƿ�Ϊ��
        if (linkMan.getLkmName() != null && linkMan.getLkmName() != "") {
            criteria.add(Restrictions.like("lkmName","%"+linkMan.getLkmName()+"%"));
        }
        if (linkMan.getCustomer().getCid() != null && linkMan.getCustomer().getCid() > 0) {
            criteria.add(Restrictions.eq("customer.cid",linkMan.getCustomer().getCid()));
        }
        return (List<LinkMan>) this.getHibernateTemplate().findByCriteria(criteria);
    }
}
