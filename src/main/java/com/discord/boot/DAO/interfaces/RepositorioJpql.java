package com.discord.boot.DAO.interfaces;

import java.util.List;

public interface RepositorioJpql<T> {
    public List<T> getAll();

    public T getAllbyPK(Integer codigo);

    public T insert(T obj) throws Exception;

    public T update (Integer codigo, T obj);

    public void delete (T obj);
}
