package com.krypton.service;


import com.krypton.db.Singer;

import java.sql.SQLException;
import java.util.List;

public interface SingerService {

    List<Singer> findAll() throws SQLException;
    Singer findById(Integer id);
    void insert(Singer singer);
    void save(Integer id, Singer singer);
    void delete(Integer id);

}
