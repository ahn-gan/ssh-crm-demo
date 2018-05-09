package com.ahn.dao;

import com.ahn.entity.LinkMan;

import java.util.List;

public interface LinkManDao {
    void insert(LinkMan linkMan);

    List<LinkMan> list();

    LinkMan fineOneById(int lid);

    void update(LinkMan linkMan);

    List<LinkMan> query(LinkMan linkMan);
}
