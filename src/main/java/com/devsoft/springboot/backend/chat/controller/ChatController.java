package com.devsoft.springboot.backend.chat.controller;

import com.devsoft.springboot.backend.chat.models.Mensaje;
import com.devsoft.springboot.backend.chat.models.service.IChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.Random;

@Controller
public class ChatController {

    private String[] colores = {"red", "greenm", "blue", "magenta", "purple", "orange"};

    @Autowired
    private IChatService chatService;

    @Autowired
    private SimpMessagingTemplate webSocked;

    @MessageMapping("/mensaje")
    @SendTo("/chat/mensaje")
    public Mensaje recibeMesaje(Mensaje mensaje) {

        mensaje.setFecha(new Date().getTime());

        if (mensaje.getTipo().equals("NUEVO_USUARIO")) {
            mensaje.setColor(colores[new Random().nextInt(colores.length)]);
            mensaje.setTexto("Nuevo usuario conectado");
        } else {
            chatService.guardar(mensaje);
        }

        return mensaje;
    }

    @MessageMapping("/escribiendo")
    @SendTo("/chat/escribiendo")
    public String escribiendo(String username) {
        return username.concat(" está escribiendo...");
    }

    @MessageMapping("/historial")
    public void historial(String clienteId) {
        webSocked.convertAndSend("/chat/historial/"+clienteId, chatService.obtenerUltimo10Mensajes());
    }
}
