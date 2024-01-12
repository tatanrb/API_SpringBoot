package com.tatan.primeraapi.model.dao;

import com.tatan.primeraapi.model.entity.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface ClienteDao extends CrudRepository<Cliente, Integer> {
}
