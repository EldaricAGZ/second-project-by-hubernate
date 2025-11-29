package com.javarush.dao.factory;

import com.javarush.dao.BaseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RepositoryFactory {
    private static final Logger logger = LoggerFactory.getLogger(RepositoryFactory.class);
    private static final Map<Class<?>, BaseRepository<?>> storageOfRepo = new ConcurrentHashMap<>();

    private RepositoryFactory() {}

    @SuppressWarnings("unchecked")
    public static <T> BaseRepository<T> getRepository(Class<T> entityClass) {
        return (BaseRepository<T>) storageOfRepo.computeIfAbsent(entityClass, clazz -> {
            StringBuilder stringBuilder = new StringBuilder(entityClass.getSimpleName());
            stringBuilder.append("Repository");
            try {
                return (BaseRepository<?>) Class.forName("com.javarush.dao." + stringBuilder).getConstructor().newInstance();
            } catch (Exception e) {
                logger.warn(e.getMessage());
                throw new RuntimeException(e);
            }
        });
    }
}
