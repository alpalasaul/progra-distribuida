package com.programacion.servicios;

import com.programacion.db.Singer;

import java.util.List;

public interface SingerService {

    List<Singer> findAll();
    Singer findById(Integer id);
    void insert(Singer singer);
    void save(Integer id, Singer singer);
    void delete(Integer id);

}
