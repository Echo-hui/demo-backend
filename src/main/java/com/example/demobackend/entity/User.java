package com.example.demobackend.entity;
import lombok.Data;

@Data
public class User {

  private long id;
  private String username;
  private String password;
  private String email;
  private String phone;
  private java.sql.Timestamp createTime;
  private java.sql.Timestamp updateTime;
  private String state;

}
