package com.revature.richbank.services;

import java.util.List;

public interface Serviceable <T> {

    T create(T newObject);

    List<T> readAll();

    T readById(String id);

    T update(T updateObject);

    boolean delete(String id);

    boolean validInput(T object);

}
