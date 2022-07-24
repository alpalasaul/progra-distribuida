package com.krypton.service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.krypton.db.Album;

import java.util.List;

public interface AlbumService {

    List<Album> findAll();
    Album findById(Integer id);
    void insert(ObjectNode item);
    void save(Integer id, ObjectNode album);
    void delete(Integer id);

}
