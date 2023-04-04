package com.programing.service;

import com.programing.dto.Singer;

import java.util.List;

public interface SingerService {

    List<Singer> findAll();
    Singer findById(Integer id);
    void insert(Singer singer);
    void save(Singer singer);
    void delete(Integer id);

}
