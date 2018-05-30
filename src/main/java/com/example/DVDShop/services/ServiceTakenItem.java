package com.example.DVDShop.services;

import com.example.DVDShop.domain.*;
import com.example.DVDShop.repos.TakenItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Optional;

@Service
public class ServiceTakenItem implements TakenItemRepo {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private DVDService dvdService;

    @Override
    @Transactional
    public <S extends TakenItem> S save(S s) {

        em.persist(s);

        return s;
    }

    @Override
    public <S extends TakenItem> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<TakenItem> findById(Long aLong) {

        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<TakenItem> findAll() {
        CriteriaBuilder cb= em.getCriteriaBuilder();
        CriteriaQuery cq=cb.createQuery();
        Root<TakenItem> takenItemRoot=cq.from(TakenItem.class);
        cq.select(takenItemRoot);
        Query q=em.createQuery(cq);
        return q.getResultList();
    }

    public Iterable<TakenItem> findAllByUser(User user) {
        CriteriaBuilder cb= em.getCriteriaBuilder();
        CriteriaQuery cq=cb.createQuery();
        Root<TakenItem> takenItemRoot=cq.from(TakenItem.class);
        cq.select(takenItemRoot);
        cq.where(cb.and(
                cb.equal(takenItemRoot.get(TakenItem_.user),user),
                cb.isNull(takenItemRoot.get(TakenItem_.date_returned))));
        Query q=em.createQuery(cq);
        return q.getResultList();
    }

    public TakenItem findAllByDiskAndUser(DVDisk disk, User user) {
        CriteriaBuilder cb= em.getCriteriaBuilder();
        CriteriaQuery cq=cb.createQuery();
        Root<TakenItem> takenItemRoot=cq.from(TakenItem.class);
        cq.select(takenItemRoot);
        cq.where(
                cb.and(
                cb.equal(takenItemRoot.get(TakenItem_.disk),disk),
                cb.equal(takenItemRoot.get(TakenItem_.user),user),
                cb.isNull(takenItemRoot.get(TakenItem_.date_returned))
                )
        );
        Query q=em.createQuery(cq);
        return (TakenItem) q.getSingleResult();
    }




    @Override
    public Iterable<TakenItem> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    @Transactional
    public void deleteById(Long aLong) {

        CriteriaBuilder cb= em.getCriteriaBuilder();
        CriteriaDelete<TakenItem> takenItemCriteriaDelete=cb.createCriteriaDelete(TakenItem.class);
        Root<TakenItem> diskRoot = takenItemCriteriaDelete.from(TakenItem.class);

        takenItemCriteriaDelete.where(cb.equal(diskRoot.get(TakenItem_.id),aLong));
        em.createQuery(takenItemCriteriaDelete).executeUpdate();
    }

    @Override
    @Transactional
    public void delete(TakenItem takenItem) {
        deleteById(takenItem.getId());
        if(takenItem.getDate_returned()==null) {
            dvdService.changAvailable(takenItem.getDisk(), true);

        }
    }

    @Transactional
    public void deleteByDisk(DVDisk dvDisk) {

        CriteriaBuilder cb= em.getCriteriaBuilder();
        CriteriaDelete<TakenItem> takenItemCriteriaDelete=cb.createCriteriaDelete(TakenItem.class);
        Root<TakenItem> diskRoot = takenItemCriteriaDelete.from(TakenItem.class);

        takenItemCriteriaDelete.where(cb.equal(diskRoot.get(TakenItem_.disk),dvDisk));
        em.createQuery(takenItemCriteriaDelete).executeUpdate();
    }

    @Override
    public void deleteAll(Iterable<? extends TakenItem> iterable) {

    }

    @Override
    public void deleteAll() {

    }
}
