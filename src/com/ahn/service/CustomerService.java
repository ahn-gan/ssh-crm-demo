package com.ahn.service;

import com.ahn.dao.CustomerDao;
import com.ahn.entity.Customer;
import com.ahn.entity.Dict;
import com.ahn.entity.PageBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//ʹ�������Զ��ύ
@Transactional
public class CustomerService {
    private CustomerDao customerDao;

    public void setCustomerDao(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public void add(Customer customer) {
        customerDao.add(customer);
    }

    public List<Customer> findall() {
        return customerDao.findall();
    }

    public Customer findOne(int cid) {
        return customerDao.findOne(cid);
    }

    public void delete(Customer c) {
        customerDao.delete(c);
    }

    public void update(Customer customer) {
        customerDao.update(customer);
    }

    //����ҳ�����ݷ�װ��pageBean����
    public PageBean findByPagenum(Integer currentPage) {
        PageBean pageBean=new PageBean();
        //��װ��ǰ��
        pageBean.setCurrentPage(currentPage);
        //�õ��ܼ�¼���������dao�в�ѯ�ܼ�¼���ķ���
        int totalCount=customerDao.getTotalCount();
        pageBean.setTotalCount(totalCount);

        //�õ���ҳ�����ݣ���Ҫ����ÿҳ��ʾ�ļ�¼��
        int pageCount=3;

        //����ҳ��
        int totalPages=0;
        if(totalCount%pageCount==0){
            totalPages=totalCount/pageCount;
        }else {
            totalPages=totalCount/pageCount+1;
        }
        pageBean.setTotalPages(totalPages);
        //���㿪ʼ������
        int beginIndex=(currentPage-1)*pageCount;
        pageBean.setBeginIndex(beginIndex);
        //��ѯ�������list������
        List<Customer> list=customerDao.getPageList(beginIndex,pageCount);
        pageBean.setList(list);
        return pageBean;
    }

    public List<Customer> findByCondition(Customer customer) {
        return customerDao.findByCondition(customer);
    }

    public PageBean findByConditions(Customer customer, Integer currentPage) {
        //�õ���pageBean�������List���Ϻ��ܵļ�¼��
        PageBean pageBean=customerDao.findByConditions(customer,currentPage);
        //��װ��ǰ��
        pageBean.setCurrentPage(currentPage);
        //�õ��ܼ�¼���������dao�в�ѯ�ܼ�¼���ķ���
        int totalCount=pageBean.getTotalCount();
        //�õ���ҳ�����ݣ���Ҫ����ÿҳ��ʾ�ļ�¼��
        int pageCount=3;
        //����ҳ��
        int totalPages=0;
        if(totalCount%pageCount==0){
            totalPages=totalCount/pageCount;
        }else {
            totalPages=totalCount/pageCount+1;
        }
        pageBean.setTotalPages(totalPages);
        //���㿪ʼ������
        int beginIndex=(currentPage-1)*pageCount;
        pageBean.setBeginIndex(beginIndex);
        return pageBean;
    }

    public List<Customer> queryByParameters(Customer customer) {
        return customerDao.queryByParameters(customer);
    }

    public List<Dict> findLevel() {
        return customerDao.findLevel();
    }
}
