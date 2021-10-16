package com.devsoft.springboot.backend.chat.models.DAO;

import com.devsoft.springboot.backend.chat.models.Mensaje;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ChatRepository extends MongoRepository<Mensaje, String> {
    public List<Mensaje> findFirst10ByOrderByFechaDesc();
}
