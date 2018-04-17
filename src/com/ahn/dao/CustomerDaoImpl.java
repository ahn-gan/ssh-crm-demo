package com.ahn.dao;

import com.ahn.entity.Customer;
import org.hibernate.criterion.DetachedCriteria;
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

        List<Customer> list = (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria, beginIndex, pageCount);
        return list;
    }
}
