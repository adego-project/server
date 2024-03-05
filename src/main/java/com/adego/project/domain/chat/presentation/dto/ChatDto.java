package com.adego.project.domain.chat.presentation.dto;

import com.adego.project.domain.chat.presentation.types.MessageType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChatDto {
  @Enumerated(EnumType.STRING)
  private MessageType type;
  private String roomId;
  private String sender;
  private String message;

  public void setMessage(String message) {
    this.message = message;
  }

  private String time;
}
