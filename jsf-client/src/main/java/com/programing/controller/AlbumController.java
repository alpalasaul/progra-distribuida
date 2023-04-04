package com.programing.controller;

import com.programing.model.Album;
import com.programing.model.Singer;
import com.programing.service.AlbumService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.primefaces.event.RowEditEvent;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class AlbumController implements Serializable {

    @Inject
    @RestClient
    private AlbumService albumService;

    @Inject
    private Message message;

    @Getter
    @Setter
    private List<Album> albumList;
    @Getter
    @Setter
    private Singer singer;
    @Getter
    @Setter
    private Album album;
    @Getter
    @Setter
    private Integer id;

    @PostConstruct
    private void init() {
        this.albumList = this.getAll();
        this.singer = new Singer();
        this.album = new Album();
    }

    public List<Album> getAll() {
        return this.albumService.findAll();
    }

    public void save() {
        try {
            this.album.setSinger(this.singer);
            this.albumService.create(this.album);
            this.message.sendMsg(FacesMessage.SEVERITY_INFO, "Album guardado");
        } catch (Exception e) {
            this.message.sendMsg(FacesMessage.SEVERITY_FATAL, "Error al guardar");
        }
    }

    public void delete(Integer id) {
        try {
            this.albumService.delete(id);
            this.message.sendMsg(FacesMessage.SEVERITY_INFO, "Album eliminado");
        } catch (Exception e) {
            this.message.sendMsg(FacesMessage.SEVERITY_FATAL, "Error al eliminar");
        }
    }

    public void update(Album album) {
        try {
            this.albumService.update(album);
            this.message.sendMsg(FacesMessage.SEVERITY_INFO, "Album actualizado");
        } catch (Exception e) {
            this.message.sendMsg(FacesMessage.SEVERITY_FATAL, "Error al actualizar");
        }
    }

    public void onRowEdit(RowEditEvent<Album> event) {
        this.update(event.getObject());
    }

    public void onRowCancel(RowEditEvent<Album> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", "Cancelado");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
