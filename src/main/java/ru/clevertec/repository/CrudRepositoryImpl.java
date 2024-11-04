package ru.clevertec.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import ru.clevertec.util.JPAUtil;

import java.util.List;
import java.util.Optional;

public class CrudRepositoryImpl<T, ID> implements CrudRepository<T, ID> {
    private final Class<T> clazz;
    private final EntityManagerFactory entityManagerFactory = JPAUtil.getEntityManagerFactory();

    protected CrudRepositoryImpl(final Class<T> domainClass) {
        this.clazz = domainClass;
    }

    @Override
    public <S extends T> S save(S entity) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(entity);
            transaction.commit();
            return entity;
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public <S extends T> S update(S entity) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(entity);
            transaction.commit();
            return entity;
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Optional<T> findById(ID id) {
        EntityManager entityManager = getEntityManager();
        T entity = entityManager.find(clazz, id);
        if (entity == null) return Optional.empty();
        entityManager.detach(entity);
        entityManager.close();
        return Optional.of(entity);
    }

    @Override
    public List<T> findAll() {
        EntityManager entityManager = getEntityManager();
        String qlString = "FROM %s".formatted(clazz.getName());
        TypedQuery<T> query = entityManager.createQuery(qlString, clazz);
        return query.getResultList();
    }

    @Override
    public void deleteById(ID id) {
        EntityManager entityManager = getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            T entity = entityManager.find(clazz, id);
            entityManager.remove(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }

    protected EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
