package com.ahn.service;

import com.ahn.dao.CustomerDao;
import com.ahn.entity.Customer;
import com.ahn.entity.Dict;
import com.ahn.entity.PageBean;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//使用事务自动提交
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

    //将分页的数据封装到pageBean对象
    public PageBean findByPagenum(Integer currentPage) {
        PageBean pageBean=new PageBean();
        //分装当前码
        pageBean.setCurrentPage(currentPage);
        //得到总记录数，需调用dao中查询总记录数的方法
        int totalCount=customerDao.getTotalCount();
        pageBean.setTotalCount(totalCount);

        //得到分页的数据，需要设置每页显示的记录数
        int pageCount=3;

        //计算页数
        int totalPages=0;
        if(totalCount%pageCount==0){
            totalPages=totalCount/pageCount;
        }else {
            totalPages=totalCount/pageCount+1;
        }
        pageBean.setTotalPages(totalPages);
        //计算开始的索引
        int beginIndex=(currentPage-1)*pageCount;
        pageBean.setBeginIndex(beginIndex);
        //查询结果放在list集合中
        List<Customer> list=customerDao.getPageList(beginIndex,pageCount);
        pageBean.setList(list);
        return pageBean;
    }

    public List<Customer> findByCondition(Customer customer) {
        return customerDao.findByCondition(customer);
    }

    public PageBean findByConditions(Customer customer, Integer currentPage) {
        //得到的pageBean对象包含List集合和总的记录数
        PageBean pageBean=customerDao.findByConditions(customer,currentPage);
        //分装当前码
        pageBean.setCurrentPage(currentPage);
        //得到总记录数，需调用dao中查询总记录数的方法
        int totalCount=pageBean.getTotalCount();
        //得到分页的数据，需要设置每页显示的记录数
        int pageCount=3;
        //计算页数
        int totalPages=0;
        if(totalCount%pageCount==0){
            totalPages=totalCount/pageCount;
        }else {
            totalPages=totalCount/pageCount+1;
        }
        pageBean.setTotalPages(totalPages);
        //计算开始的索引
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
