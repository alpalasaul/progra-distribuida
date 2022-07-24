package com.programacion.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

public class HolaMundoDaoImpl implements HolaMundoDao {

    private static List<HolaMundoDto> data = new ArrayList<>();

    @Override
    public void save(HolaMundoDto holaMundoDTO) {
        data.add(holaMundoDTO);
    }

    @Override
    public boolean update(String id, HolaMundoDto holaMundoDTO) {
        AtomicBoolean updated = new AtomicBoolean(false);
        data.forEach(e -> {
            if (e.getId().equals(id)) {
                e.setMensaje(holaMundoDTO.getMensaje());
                e.setFecha(holaMundoDTO.getFecha());
                updated.set(true);
                return;
            }
        });
        return updated.get();
    }

    @Override
    public boolean delete(String id) {
        List<HolaMundoDto> holaMundoDto = data.stream()
                .filter(e -> e.getId().equals(id))
                .collect(Collectors.toList());
        if (!holaMundoDto.isEmpty()) {
            data.remove(holaMundoDto);
            return true;
        }
        return false;
    }

    @Override
    public List<HolaMundoDto> findAll() {
        return data;
    }

}
