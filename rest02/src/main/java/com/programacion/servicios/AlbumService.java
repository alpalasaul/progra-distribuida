package com.programacion.servicios;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.programacion.db.Album;

import java.util.List;

public interface AlbumService {

    List<Album> findAll();
    Album findById(Integer id);
    void insert(ObjectNode item);
    void save(Integer id, ObjectNode album);
    void delete(Integer id);

}
