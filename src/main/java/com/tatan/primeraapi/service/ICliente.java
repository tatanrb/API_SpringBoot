package com.tatan.primeraapi.service;

import com.tatan.primeraapi.model.dto.ClienteDto;
import com.tatan.primeraapi.model.entity.Cliente;

public interface ICliente {
    Cliente save(ClienteDto cliente);

    Cliente findById(Integer id);

    void delete(Cliente cliente);
}
