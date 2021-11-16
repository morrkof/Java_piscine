package edu.school21.chat.models;

import java.time.Instant;
import java.util.List;
import java.util.Objects;

public class Message {
    private int id;
    private int authorId;
    private int roomId;
    private String text;
    private Instant date;

    public Message(int id, int authorId, int roomId, String text, Instant date) {
        this.id = id;
        this.authorId = authorId;
        this.roomId = roomId;
        this.text = text;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return id == message.id && authorId == message.authorId && roomId == message.roomId && Objects.equals(text, message.text) && Objects.equals(date, message.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, authorId, roomId, text, date);
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", authorId=" + authorId +
                ", roomId=" + roomId +
                ", text='" + text + '\'' +
                ", date=" + date +
                '}';
    }
}
