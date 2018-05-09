package com.ahn.service;

import com.ahn.dao.VisitDao;
import com.ahn.entity.Visit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public class VisitService {

    private VisitDao visitDao;

    public void setVisitDao(VisitDao visitDao) {
        this.visitDao = visitDao;
    }

    public void insert(Visit visit) {
        visitDao.insert(visit);
    }

    public List<Visit> findAll() {
        return visitDao.findAll();
    }

    public Visit findOneById(int vid) {
        return visitDao.findOneById(vid);
    }

    public void update(Visit visit) {
        visitDao.update(visit);
    }
}
