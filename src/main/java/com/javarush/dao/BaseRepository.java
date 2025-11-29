package com.javarush.dao;

import com.javarush.exception.CannotCreateEntityException;
import com.javarush.exception.UserNotFoundException;
import org.hibernate.Session;

public abstract class BaseRepository<T> {

    private final Class<T> classOfEntity;

    protected BaseRepository(Class<T> classOfEntity) {
        this.classOfEntity = classOfEntity;
    }

    public void create(T entity, Session session) {
        try {
            session.persist(entity);
        } catch (Exception e) {
            throw new CannotCreateEntityException("Cannot create entity: " + classOfEntity.getSimpleName());
        }
    }

    public T findById(Object id, Session session) {
        return session.find(classOfEntity, id);
    }

    public T update (T entity, Session session) {
        return (T) session.merge(entity);
    }

    public void deleteEntity(T entity, Session session) {
        try {
            session.remove(entity);
        } catch (Exception e) {
            throw new UserNotFoundException("User: " + classOfEntity.getSimpleName() + " not found!");
        }
    }
}
