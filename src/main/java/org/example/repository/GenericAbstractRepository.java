package org.example.repository;

import org.example.exceptions.AppBankException;
import org.example.model.Model;

import java.util.Collection;

public interface GenericAbstractRepository <T extends Model> {
    Collection<T> getAll();

    T getById(Long id) throws AppBankException;

      T create(T item) throws AppBankException;

    T update(T item) throws AppBankException;

    void delete(Long id) throws AppBankException;

    void saveAll(Collection<T> saveCollection) throws AppBankException;

    T createWithId(T item) throws AppBankException;

    T createOrUpdate(T item) throws AppBankException;
}
