package com.ahn.dao;

import com.ahn.entity.Customer;
import com.ahn.entity.PageBean;

import java.util.List;

public interface CustomerDao {
    void add(Customer customer);

    List<Customer> findall();

    Customer findOne(int cid);

    void delete(Customer c);

    void update(Customer customer);

    int getTotalCount();

    List<Customer> getPageList(int beginIndex, int pageCount);

    List<Customer> findByCondition(Customer customer);

    PageBean findByConditions(Customer customer, Integer currentPage);

}
