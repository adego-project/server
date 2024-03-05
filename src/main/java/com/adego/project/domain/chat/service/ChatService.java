package com.adego.project.domain.chat.service;

import com.adego.project.domain.chat.presentation.dto.ChatRoom;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.*;

@Slf4j
@Getter
@Service
public class ChatService {
  private final ObjectMapper mapper;
  private Map<String, ChatRoom> chatRooms;

  public ChatService(ObjectMapper mapper) {
    this.mapper = mapper;
  }

  @PostConstruct
  private void init() {
    chatRooms = new HashMap<>();
  }

  public List<ChatRoom> findAllRoom() {
    return new ArrayList<>(chatRooms.values());
  }

  public ChatRoom findRoomById(String roomId) {
    return chatRooms.get(roomId);
  }

  public ChatRoom createRoom(String name) {
    String roomId = UUID.randomUUID().toString();
    log.info("roomId = {}", roomId);

    ChatRoom room = ChatRoom.builder()
      .roomId(roomId)
      .name(name)
      .build();

    chatRooms.put(roomId, room); // 랜덤 아이디와 room 정보를 Map 에 저장

    return room;
  }

  public <T> void sendMessage(WebSocketSession session, T message) {
    try {
      session.sendMessage(new TextMessage(mapper.writeValueAsString(message)));
    } catch (IOException e) {
      log.error(e.getMessage(), e);
    }
  }
}
