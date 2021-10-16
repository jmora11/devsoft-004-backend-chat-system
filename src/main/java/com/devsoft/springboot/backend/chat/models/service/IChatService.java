package com.devsoft.springboot.backend.chat.models.service;

import com.devsoft.springboot.backend.chat.models.Mensaje;

import java.util.List;

public interface IChatService {

    public List<Mensaje> obtenerUltimo10Mensajes();
    public Mensaje guardar(Mensaje mensaje);

}
