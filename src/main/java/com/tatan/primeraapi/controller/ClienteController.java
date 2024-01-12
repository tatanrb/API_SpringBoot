package com.tatan.primeraapi.controller;

import com.tatan.primeraapi.model.dto.ClienteDto;
import com.tatan.primeraapi.model.entity.Cliente;
import com.tatan.primeraapi.model.payload.MensajeResponse;
import com.tatan.primeraapi.service.ICliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ClienteController {

    @Autowired
    private ICliente clienteService;

    @PostMapping("/cliente")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> create(@RequestBody ClienteDto clienteDto){
        ClienteDto cliente;

        try {
            Cliente clienteBBDD = clienteService.save(clienteDto);

            cliente = ClienteDto.builder()
                    .idCliente(clienteBBDD.getIdCliente())
                    .nombre(clienteBBDD.getNombre())
                    .apellido(clienteBBDD.getApellido())
                    .fechaRegistro(clienteBBDD.getFechaRegistro())
                    .correo(clienteBBDD.getCorreo())
                    .build();

            MensajeResponse mensaje = MensajeResponse.builder()
                    .mensaje("Creado correctamente")
                    .object(cliente)
                    .build();

            return new ResponseEntity<>(mensaje, HttpStatus.CREATED);

        } catch (Exception e){
            MensajeResponse mensaje = MensajeResponse.builder()
                    .mensaje(e.getMessage())
                    .object(null)
                    .build();

            return new ResponseEntity<>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/cliente")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> update(@RequestBody ClienteDto clienteDto){
        ClienteDto cliente;

        try{
            cliente = ClienteDto.builder()
                    .idCliente(clienteDto.getIdCliente())
                    .nombre(clienteDto.getNombre())
                    .apellido(clienteDto.getApellido())
                    .fechaRegistro(clienteDto.getFechaRegistro())
                    .correo(clienteDto.getCorreo())
                    .build();

            clienteService.save(cliente);

            MensajeResponse mensaje = MensajeResponse.builder()
                    .mensaje("Actualizado correctamente")
                    .object(cliente)
                    .build();

            return new ResponseEntity<>(mensaje, HttpStatus.OK);
        } catch (DataAccessException e){
            MensajeResponse mensaje = MensajeResponse.builder()
                    .mensaje("Actualizado correctamente")
                    .object(null)
                    .build();

            return new ResponseEntity<>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/cliente/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        try {
            Cliente clienteBBDD = clienteService.findById(id);

            clienteService.delete(clienteBBDD);

            MensajeResponse mensaje = MensajeResponse.builder()
                    .mensaje("Creado correctamente")
                    .object(clienteBBDD)
                    .build();

            return new ResponseEntity<>(mensaje, HttpStatus.NO_CONTENT);
        } catch (Exception e){
            MensajeResponse mensaje = MensajeResponse.builder()
                    .mensaje(e.getMessage())
                    .object(null)
                    .build();
            return new ResponseEntity<>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cliente/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> show_by_id(@PathVariable Integer id){
        Cliente cliente;
        try {
            cliente = clienteService.findById(id);

            if (cliente != null){
                MensajeResponse mensaje = MensajeResponse.builder()
                        .mensaje("Cliente obtenido correctamente!")
                        .object(cliente)
                        .build();

                return new ResponseEntity<>(mensaje, HttpStatus.OK);
            } else {
                MensajeResponse mensaje = MensajeResponse.builder()
                        .mensaje("No se encontró clientes con el id " + id)
                        .object(null)
                        .build();

                return new ResponseEntity<>(mensaje, HttpStatus.NOT_FOUND);
            }
        } catch (DataAccessException e){
            MensajeResponse mensaje = MensajeResponse.builder()
                    .mensaje(e.getMessage())
                    .object(null)
                    .build();

            return new ResponseEntity<>(mensaje, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
