package com.example.springnativequery.dtos;

public class UserDTO {
    // TODO: เจอว่า h2 ตอนเรียกด้วย Native Query ต้องใช้ชื่อ field เป็นตัวใหญ่
//    private int ID;
//    private String USERNAME;

    private int id;
    private String username;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
