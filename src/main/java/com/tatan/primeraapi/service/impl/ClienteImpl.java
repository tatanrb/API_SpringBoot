package com.tatan.primeraapi.service.impl;

import com.tatan.primeraapi.model.dao.ClienteDao;
import com.tatan.primeraapi.model.dto.ClienteDto;
import com.tatan.primeraapi.model.entity.Cliente;
import com.tatan.primeraapi.service.ICliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClienteImpl implements ICliente {

    @Autowired
    private ClienteDao clienteDao;

    @Transactional
    @Override
    public Cliente save(ClienteDto clienteDto) {
        // Usamos el patrón builder para no exponer la entidad
        Cliente cliente = Cliente.builder()
                .idCliente(clienteDto.getIdCliente())
                .nombre(clienteDto.getNombre())
                .apellido(clienteDto.getApellido())
                .fechaRegistro(clienteDto.getFechaRegistro())
                .correo(clienteDto.getCorreo())
                .build();

        return clienteDao.save(cliente);
    }

    @Transactional(readOnly = true)
    @Override
    public Cliente findById(Integer id) {
        return clienteDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Cliente cliente) {
        clienteDao.delete(cliente);
    }
}
