package com.programing.service;

import com.programing.dto.Album;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@ApplicationScoped
public class AlbumServiceImpl implements AlbumService {

    @Inject
    private EntityManager em;

    @Override
    public List<Album> findAll() {
        return em.createNamedQuery("Album.findAll").getResultList();
    }

    @Override
    public Album findById(Integer id) {
        return (Album) em.createNamedQuery("Album.findById").setParameter("id", id).getSingleResult();
    }

    @Override
    public void insert(Album album) {
        em.getTransaction().begin();
        em.merge(album);
        em.getTransaction().commit();
    }

    @Override
    public void save(Album album) {
        em.getTransaction().begin();
        em.merge(album);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Integer id) {
        em.getTransaction().begin();
        Album album = this.findById(id);
        if (album != null) {
            em.remove(album);
        }
        em.getTransaction().commit();
    }
}
