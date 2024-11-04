package ru.clevertec.util;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.Getter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JPAUtil {
    @Getter
    private static final EntityManagerFactory entityManagerFactory;

    static {
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("ru.clevertec.entity");
        } catch (Exception ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static void shutdown() {
        getEntityManagerFactory().close();
    }
}
