package com.programacion.rest;

import java.util.List;

public interface HolaMundoDao {

    List<HolaMundoDto> findAll();
    void save(HolaMundoDto holaMundoDTO);
    boolean update(String id, HolaMundoDto holaMundoDTO);
    boolean delete(String id);

}
