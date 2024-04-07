package com.discord.boot.DAO.interfaces;

import java.util.List;

public interface RepositorioJpql<T> {
    public List<T> getAll();

    public T getAllbyPK(T obj);

    public T insert(T obj) throws Exception;

    public T update (T obj);

    public void delete (T obj);
}
