package com.programing.controller;

import com.programing.model.Album;
import com.programing.model.Singer;
import com.programing.service.SingerService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.event.RowEditEvent;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class SingerController implements Serializable {

    @Inject
    private SingerService singerService;

    @Inject
    private Message message;

    @Getter
    @Setter
    private List<Singer> singerList;
    @Getter
    @Setter
    private List<Album> albumList;
    @Getter
    @Setter
    private Singer singer;
    @Getter
    @Setter
    private Album album;

    @PostConstruct
    private void init() {
        this.singerList = this.getAll();
        this.albumList = new ArrayList<>();
        this.singer = new Singer();
        this.album = new Album();
    }

    public List<Singer> getAll() {
        return this.singerService.findAll();
    }

    public void save() {
        try {
            this.albumList.add(album);
            this.singer.setAlbum(this.albumList);
            this.singerService.create(this.singer);
            this.message.sendMsg(FacesMessage.SEVERITY_INFO, "Cantante guardado");
        } catch (Exception e) {
            this.message.sendMsg(FacesMessage.SEVERITY_FATAL, "Error al guardar");
        }
    }

    public void delete(Integer id) {
        try {
            this.singerService.delete(id);
            this.message.sendMsg(FacesMessage.SEVERITY_INFO, "Cantante eliminado");
        } catch (Exception e) {
            this.message.sendMsg(FacesMessage.SEVERITY_FATAL, "Error al eliminar");
        }
    }

    public void update(Singer singer) {
        try {
            this.singerService.update(singer);
            this.message.sendMsg(FacesMessage.SEVERITY_INFO, "Cantante actualizado");
        } catch (Exception e) {
            this.message.sendMsg(FacesMessage.SEVERITY_FATAL, "Error al actualizar");
        }
    }

    public void onRowEdit(RowEditEvent<Singer> event) {
        this.update(event.getObject());
    }

    public void onRowCancel(RowEditEvent<Singer> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", "Cancelado");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
