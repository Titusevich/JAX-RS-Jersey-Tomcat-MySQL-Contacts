package org.example.app.service;

import java.util.List;

public interface BaseService<T,S> {

    T create(S request);
    List<T> getAll();

    // ---- Path Params ----------------------

    T getById(Long id);
    T update(Long id, S request);
    boolean deleteById(Long id);
}
