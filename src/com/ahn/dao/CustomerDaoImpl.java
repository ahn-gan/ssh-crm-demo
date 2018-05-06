package com.ahn.dao;

import com.ahn.entity.Customer;
import com.ahn.entity.PageBean;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.List;


//需要HibernateTemplate模板对象，可以直接继承HibernateDaoSupport类得到
public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao {

    //添加客户功能
    public void add(Customer customer) {
        //获得HibernateTemplate模板对象
        //将对象保存到数据库中
        this.getHibernateTemplate().save(customer);
    }

    //客户列表功能
    @SuppressWarnings("all")
    public List<Customer> findall() {
        List<Customer> list= (List<Customer>) this.getHibernateTemplate().find("from Customer");
        return list;
    }

    @Override
    public Customer findOne(int cid) {
        return this.getHibernateTemplate().get(Customer.class,cid);
    }

    @Override
    public void delete(Customer c) {
        //删除对象
        this.getHibernateTemplate().delete(c);
    }

    @Override
    public void update(Customer customer) {
        //根据id进行修改S
        this.getHibernateTemplate().update(customer);
    }

    //获取总记录数
    @SuppressWarnings("all")
    public int getTotalCount() {
        List<Object> list = (List<Object>) this.getHibernateTemplate().find("select count(*) from Customer");
        //将object对象转换成int类型
        if(list!=null&&list.size()!=0){
            Object obj=list.get(0);
            Long lobj=(Long)obj;
            int count=lobj.intValue();
            return count;
        }
        return 0;
    }

    //获取分页的数据集合
    @Override
    public List<Customer> getPageList(int beginIndex, int pageCount) {
        //使用离线对象和hibernate的模板方法得到分页查询的数据集合
        /*1、创建离线对象，设置对哪个实体类进行操作*/
        DetachedCriteria criteria=DetachedCriteria.forClass(Customer.class);
        /*调用hibernate的模板方法进行查询
        * 第一个参数是离线对象，第二个参数是每页开始的位置。第三个参数是每页显示的记录数*/

        //得到总记录数
        //criteria.setProjection(Projections.rowCount());

        List<Customer> list = (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria, beginIndex, pageCount);
        return list;
    }

    //执行条件查询
    public List<Customer> findByCondition(Customer customer) {
        //使用离线对象进行条件查询操作
        DetachedCriteria criteria=DetachedCriteria.forClass(Customer.class);
        //设置条件是实体类的哪个属性
        criteria.add(Restrictions.like("custName","%"+customer.getCustName()+"%"));
        criteria.add(Restrictions.like("custLevel","%"+customer.getCustLevel()+"%"));
        criteria.add(Restrictions.like("custSource","%"+customer.getCustSource()+"%"));
        //调用hibernateTemplate的方法得到查询的结果集
        List<Customer> list = (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria);
        this.getHibernateTemplate().getMaxResults();
        return list;
    }

    @Override
    public PageBean findByConditions(Customer customer, Integer currentPage) {
        PageBean pageBean=new PageBean();
        DetachedCriteria criteria=DetachedCriteria.forClass(Customer.class);
        //设置条件是实体类的哪个属性
        criteria.add(Restrictions.like("custName","%"+customer.getCustName()+"%"));
        criteria.add(Restrictions.like("custLevel","%"+customer.getCustLevel()+"%"));
        criteria.add(Restrictions.like("custSource","%"+customer.getCustSource()+"%"));
        //调用hibernateTemplate的方法得到查询的结果集
        List<Customer> list = (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria);
        int resultCount=list.size();
        System.out.println("查询结果有："+resultCount);
        pageBean.setList(list);
        pageBean.setTotalCount(resultCount);
        return pageBean;
    }
}
