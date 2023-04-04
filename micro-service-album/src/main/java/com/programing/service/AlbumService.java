package com.programing.service;


import com.programing.dto.Album;

import java.util.List;

public interface AlbumService {

    List<Album> findAll();
    Album findById(Integer id);
    void insert(Album album);
    void save(Album album);
    void delete(Integer id);

}
