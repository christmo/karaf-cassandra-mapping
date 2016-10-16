package io.twim.persister;

/**
 * Created by christmo on 15/10/16.
 */
public interface CRUD {

    <E> void save(E entity,Class<E> clazz);

}
