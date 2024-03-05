package com.adego.project.domain.chat;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Room {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "chat_room_id")
  private Long id;
  private String roomName;
  private String roomPrimaryId;

  @Builder
  public Room(Long id, String roomName, String roomPrimaryId) {
    this.id = id;
    this.roomName = roomName;
    this.roomPrimaryId = roomPrimaryId;
  }
}
