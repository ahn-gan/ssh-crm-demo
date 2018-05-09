package com.ahn.dao;

import com.ahn.entity.LinkMan;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.ArrayList;
import java.util.List;

public class LinkManDaoImpl extends HibernateDaoSupport implements LinkManDao {

    //添加联系人操作
    public void insert(LinkMan linkMan) {
        this.getHibernateTemplate().save(linkMan);
    }

    //得到所有联系人集合
    @SuppressWarnings("all")
    public List<LinkMan> list() {
        return (List<LinkMan>) this.getHibernateTemplate().find("from LinkMan");
    }

    //根据id查询联系人
    public LinkMan fineOneById(int lid) {
        //获得linkMan对象
        LinkMan linkMan=this.getHibernateTemplate().get(LinkMan.class,lid);
        return linkMan;
    }

    //更新方法
    public void update(LinkMan linkMan) {
        //会根据id进行update
        this.getHibernateTemplate().update(linkMan);
    }

/*    //条件查询，hql方式
    @SuppressWarnings("all")
    public List<LinkMan> query(LinkMan linkMan) {

        String hql="from LinkMan where 1=1";
        //为查询语句传入参数
        List<Object> param=new ArrayList<Object>();
        //判断查询条件是否为空
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
    //条件查询，离线对象方式
    @SuppressWarnings("all")
    public List<LinkMan> query(LinkMan linkMan) {
        //创建离线对象
        DetachedCriteria criteria=DetachedCriteria.forClass(LinkMan.class);
        //判断查询条件是否为空
        if (linkMan.getLkmName() != null && linkMan.getLkmName() != "") {
            criteria.add(Restrictions.like("lkmName","%"+linkMan.getLkmName()+"%"));
        }
        if (linkMan.getCustomer().getCid() != null && linkMan.getCustomer().getCid() > 0) {
            criteria.add(Restrictions.eq("customer.cid",linkMan.getCustomer().getCid()));
        }
        return (List<LinkMan>) this.getHibernateTemplate().findByCriteria(criteria);
    }
}
