package com.revature.richbank.dos;

// Generic Type T
public interface Crudable<T> {

    T create(T newObject);

    T[] findAlll();

    T findById(String id);

    boolean update(T updateObject);

    boolean delete(String id);

}
