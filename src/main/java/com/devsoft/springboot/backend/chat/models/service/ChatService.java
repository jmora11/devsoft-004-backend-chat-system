package com.devsoft.springboot.backend.chat.models.service;

import com.devsoft.springboot.backend.chat.models.DAO.ChatRepository;
import com.devsoft.springboot.backend.chat.models.Mensaje;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService implements IChatService{

    @Autowired
    ChatRepository chatRepository;

    @Override
    public List<Mensaje> obtenerUltimo10Mensajes() {
        return chatRepository.findFirst10ByOrderByFechaDesc();
    }

    @Override
    public Mensaje guardar(Mensaje mensaje) {
        return chatRepository.save(mensaje);
    }
}
