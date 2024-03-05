package com.adego.project.domain.chat.presentation.dto;

import com.adego.project.domain.chat.presentation.types.MessageType;
import com.adego.project.domain.chat.service.ChatService;
import lombok.Builder;
import lombok.Getter;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;

@Getter
public class ChatRoom {
  // 채팅방 클래스는 해당 채팅방에 어떤 사람이 있는지에 대한 정보를 갖고 있어야 합니다
  // handleAction : Message Type에 따라서 session(클라이언트)에게 메시지를 전달하기 위한 메서드입니다.
  // type 이 ENTER인 경우 채팅방에 "환영하니다"를 띄우고 TALK 인 경우 채팅방에 클라이언트가 발생한 채팅내용(message)내용을 그대로 채팅방에 반영합니다.
  // sendMessage 는 sessions 에 담긴 모든 session에 handleAction 으로 부터 넘어온 message 를 전달할 수 있도록 하는 메서드 입니다.

  private String roomId;
  private String name;
  private Set<WebSocketSession> sessions = new HashSet<>();

  @Builder
  public ChatRoom(String roomId, String name) {
    this.roomId = roomId;
    this.name = name;
  }

  public void handleAction(WebSocketSession session, ChatDto message, ChatService service) {
    // message 에 담긴 타입을 확인한다
    if(message.getType().equals(MessageType.ENTER)) {
      // sessions 에 넘어온 session 을 담고,
      sessions.add(session);

      // message 에는 입장하였다는 메시지를 띄워줍니다.
      message.setMessage(message.getSender() + " 님이 입장하였습니다.");
      sendMessage(message, service);
    } else if (message.getType().equals(MessageType.TALK)) {
      message.setMessage(message.getMessage());
      sendMessage(message,service);
    }
  }

  public <T> void sendMessage(T message, ChatService service){
    sessions.parallelStream().forEach(
      sessions -> service.sendMessage(sessions,message)
    );
  }
}
