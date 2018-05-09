package com.ahn.service;

import com.ahn.dao.LinkManDao;
import com.ahn.entity.LinkMan;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class LinkManService {
    private LinkManDao linkManDao;

    public void setLinkManDao(LinkManDao linkManDao) {
        this.linkManDao = linkManDao;
    }

    public void insert(LinkMan linkMan) {
        linkManDao.insert(linkMan);
    }

    public List<LinkMan> list() {
        return linkManDao.list();
    }

    public LinkMan fineOneById(int lid) {
        return linkManDao.fineOneById(lid);
    }

    public void update(LinkMan linkMan) {
        linkManDao.update(linkMan);
    }

    public List<LinkMan> query(LinkMan linkMan) {
        return linkManDao.query(linkMan);
    }
}
