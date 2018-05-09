package com.ahn.dao;

import com.ahn.entity.Visit;

import java.util.List;

public interface VisitDao {
    void insert(Visit visit);

    List<Visit> findAll();

    Visit findOneById(int vid);

    void update(Visit visit);
}
