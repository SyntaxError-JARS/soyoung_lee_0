package com.revature.richbank.dos;

import java.io.IOException;

// Generic Type T
public interface Crudable<T> {

    T create(T newObject);

    T[] findAll() throws IOException;

    T findById(String id);

    boolean update(T updateObject);

    boolean delete(String id);

}
