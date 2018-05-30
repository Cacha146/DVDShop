package com.example.DVDShop.services;

import com.example.DVDShop.domain.DVDisk;
import com.example.DVDShop.domain.DVDisk_;
import com.example.DVDShop.repos.DVDRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;
import java.util.Optional;

@Service
public class DVDService implements DVDRepo {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private ServiceTakenItem serviceTakenItem;

    public List<DVDisk> findByName(String name) {
        CriteriaBuilder cb= em.getCriteriaBuilder();
        CriteriaQuery cq=cb.createQuery();
        Root<DVDisk> dvDiskRoot=cq.from(DVDisk.class);
        cq.select(dvDiskRoot);
        cq.where(
                cb.equal(dvDiskRoot.get(DVDisk_.name),name)
                );
        Query q=em.createQuery(cq);
        return q.getResultList();
    }

    @Override
    @Transactional
    public <S extends DVDisk> S save(S s) {

        em.persist(s);

        return s;
    }

    @Override
    public <S extends DVDisk> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<DVDisk> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<DVDisk> findAll() {
        CriteriaBuilder cb= em.getCriteriaBuilder();
        CriteriaQuery cq=cb.createQuery();
        Root<DVDisk> dvDiskRoot=cq.from(DVDisk.class);
        cq.select(dvDiskRoot);
        Query q=em.createQuery(cq);
        return q.getResultList();
    }

    @Override
    public Iterable<DVDisk> findAllById(Iterable<Long> iterable) {
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
        CriteriaDelete<DVDisk> diskCriteriaDelete=cb.createCriteriaDelete(DVDisk.class);
        Root<DVDisk> diskRoot = diskCriteriaDelete.from(DVDisk.class);

        diskCriteriaDelete.where(cb.equal(diskRoot.get(DVDisk_.id),aLong));
        em.createQuery(diskCriteriaDelete).executeUpdate();
    }

    @Override
    @Transactional
    public void delete(DVDisk dvDisk) {
       deleteById(dvDisk.getId());
        serviceTakenItem.deleteByDisk(dvDisk);
    }

    @Override
    public void deleteAll(Iterable<? extends DVDisk> iterable) {

    }

    @Override
    public void deleteAll() {

    }

    public Iterable<DVDisk> findAllFree() {
        CriteriaBuilder cb= em.getCriteriaBuilder();
        CriteriaQuery cq=cb.createQuery();
        Root<DVDisk> dvDiskRoot=cq.from(DVDisk.class);
        cq.select(dvDiskRoot);
        cq.where(
                cb.equal(
                        dvDiskRoot.get(DVDisk_.is_available),Boolean.TRUE
                )
        );
        Query q=em.createQuery(cq);
        return q.getResultList();

    }

    public void changAvailable(DVDisk disk, Boolean available){

        disk.setIs_available(available);
        save(disk);
    }

    @Transactional
    public void updateDisk(DVDisk disk, String name, String description) {



        CriteriaBuilder cb= em.getCriteriaBuilder();
        CriteriaUpdate<DVDisk> criteriaUpdate=cb.createCriteriaUpdate(DVDisk.class);
        Root<DVDisk> diskRoot = criteriaUpdate.from(DVDisk.class);
        if(!name.isEmpty()&&name!=null) {
        criteriaUpdate.set(diskRoot.get(DVDisk_.name),name);
        }
        if(!description.isEmpty()&&description!=null){
        criteriaUpdate.set(diskRoot.get(DVDisk_.description),description);

        }
        criteriaUpdate.where(cb.equal(diskRoot.get(DVDisk_.id),disk.getId()));
        em.createQuery(criteriaUpdate).executeUpdate();

    }
}
