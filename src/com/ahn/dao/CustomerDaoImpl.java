package com.ahn.dao;

import com.ahn.entity.Customer;
import com.ahn.entity.Dict;
import com.ahn.entity.PageBean;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import java.util.HashMap;
import java.util.List;


//��ҪHibernateTemplateģ����󣬿���ֱ�Ӽ̳�HibernateDaoSupport��õ�
public class CustomerDaoImpl extends HibernateDaoSupport implements CustomerDao {

    //��ӿͻ�����
    public void add(Customer customer) {
        //���HibernateTemplateģ�����
        //�����󱣴浽���ݿ���
        this.getHibernateTemplate().save(customer);
    }

    //�ͻ��б���
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
        //ɾ������
        this.getHibernateTemplate().delete(c);
    }

    @Override
    public void update(Customer customer) {
        //����id�����޸�S
        this.getHibernateTemplate().update(customer);
    }

    //��ȡ�ܼ�¼��
    @SuppressWarnings("all")
    public int getTotalCount() {
        List<Object> list = (List<Object>) this.getHibernateTemplate().find("select count(*) from Customer");
        //��object����ת����int����
        if(list!=null&&list.size()!=0){
            Object obj=list.get(0);
            Long lobj=(Long)obj;
            int count=lobj.intValue();
            return count;
        }
        return 0;
    }

    //��ȡ��ҳ�����ݼ���
    @Override
    public List<Customer> getPageList(int beginIndex, int pageCount) {
        //ʹ�����߶����hibernate��ģ�巽���õ���ҳ��ѯ�����ݼ���
        /*1���������߶������ö��ĸ�ʵ������в���*/
        DetachedCriteria criteria=DetachedCriteria.forClass(Customer.class);
        /*����hibernate��ģ�巽�����в�ѯ
        * ��һ�����������߶��󣬵ڶ���������ÿҳ��ʼ��λ�á�������������ÿҳ��ʾ�ļ�¼��*/

        //�õ��ܼ�¼��
        //criteria.setProjection(Projections.rowCount());

        List<Customer> list = (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria, beginIndex, pageCount);
        return list;
    }

    @SuppressWarnings("all")
    public List<Dict> findLevel() {
        return (List<Dict>) this.getHibernateTemplate().find("from Dict");
    }

    //ִ��������ѯ
    public List<Customer> findByCondition(Customer customer) {
        //ʹ�����߶������������ѯ����
        DetachedCriteria criteria=DetachedCriteria.forClass(Customer.class);
        //����������ʵ������ĸ�����
        criteria.add(Restrictions.like("custName","%"+customer.getCustName()+"%"));
        criteria.add(Restrictions.eq("dictLevel.did",customer.getDictLevel().getDid()));
        criteria.add(Restrictions.like("custSource","%"+customer.getCustSource()+"%"));
        //����hibernateTemplate�ķ����õ���ѯ�Ľ����
        List<Customer> list = (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria);
        //this.getHibernateTemplate().getMaxResults();
        return list;
    }

    @Override
    public PageBean findByConditions(Customer customer, Integer currentPage) {
        PageBean pageBean=new PageBean();
        DetachedCriteria criteria=DetachedCriteria.forClass(Customer.class);
        //����������ʵ������ĸ�����
        criteria.add(Restrictions.like("custName","%"+customer.getCustName()+"%"));
        criteria.add(Restrictions.eq("dictLevel.did",customer.getDictLevel().getDid()));
        criteria.add(Restrictions.like("custSource","%"+customer.getCustSource()+"%"));
        //����hibernateTemplate�ķ����õ���ѯ�Ľ����
        List<Customer> list = (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria);
        int resultCount=list.size();
        System.out.println("��ѯ����У�"+resultCount);
        pageBean.setList(list);
        pageBean.setTotalCount(resultCount);
        return pageBean;
    }

    //������Դͳ��
    public List countSource() {
        /*1����ȡsession����
            2������sqlQuery����
            3�����ؽ������ת��
            4����ȡ�Żؽ��
        * */
        Session session=this.getSessionFactory().getCurrentSession();
        SQLQuery sqlQuery=session.createSQLQuery("SELECT COUNT(*) AS sourceNum,custSource FROM t_customer GROUP BY custSource");
 /*       Query query=session.createQuery("SELECT COUNT(*) AS ����,custSource FROM t_customer GROUP BY custSource");*/
        sqlQuery.setResultTransformer(Transformers.aliasToBean(HashMap.class));
        List list=sqlQuery.list();
        return list;
    }

    //����������ѯ
    public List<Customer> queryByParameters(Customer customer) {
        DetachedCriteria criteria=DetachedCriteria.forClass(Customer.class);
        //����������ʵ������ĸ�����
        if(customer.getCustName()!=null&&customer.getCustName()!="")
            criteria.add(Restrictions.like("custName","%"+customer.getCustName()+"%"));
/*        if(customer.getCustLevel()!=null&&customer.getCustLevel()!="")
            criteria.add(Restrictions.eq("custLevel",customer.getCustLevel()));*/
        if(customer.getCustSource()!=null&&customer.getCustSource()!="")
            criteria.add(Restrictions.eq("custSource",customer.getCustSource()));
        if(customer.getCustAddress()!=null&&customer.getCustAddress()!="")
            criteria.add(Restrictions.like("custAddress","%"+customer.getCustAddress()+"%"));
        //����hibernateTemplate�ķ����õ���ѯ�Ľ����
        List<Customer> list = (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria);
        return list;
    }

    //���ݿͻ�����ͳ��
    public List countLevel() {
        Session session=this.getSessionFactory().getCurrentSession();
        SQLQuery sqlQuery=session.createSQLQuery("SELECT COUNT(*) AS levelNum,dname FROM t_dict d,t_customer c WHERE d.did=c.custLevel GROUP BY dname ");
        sqlQuery.setResultTransformer(Transformers.aliasToBean(HashMap.class));
        List list=sqlQuery.list();
        return list;
    }
}
