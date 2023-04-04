package com.programing.service;

import com.programing.dto.Singer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;

import java.util.List;

@ApplicationScoped
public class SingerServiceImpl implements SingerService {

    @Inject private EntityManager em;

    @Override
    public List<Singer> findAll() {
        return em.createNamedQuery("Singer.findAll").getResultList();
    }

    @Override
    public Singer findById(Integer id) {
        return (Singer) em.createNamedQuery("Singer.findById")
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public void insert(Singer singer) {
        em.getTransaction().begin();
        em.persist(singer);
        em.getTransaction().commit();
    }

    @Override
    public void save(Singer singer) {
        em.getTransaction().begin();
        em.merge(singer);
        em.getTransaction().commit();
    }

    @Override
    public void delete(Integer id) {
        em.getTransaction().begin();
        Singer singer = this.findById(id);
        if (singer != null) {
            em.remove(singer);
        }
        em.getTransaction().commit();
    }
}
