package org.example.repository;


import org.example.model.Client;

import java.util.Collections;
import java.util.List;


public interface ClientRepo extends GenericAbstractRepository<Client> {
        default List<Long> getAllIds(){
        return Collections.emptyList();
    }

}