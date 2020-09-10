package com.qf.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "user")
@Entity
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userid;

    @Column(name = "user_mail")
    private String userMail;

    private String addr;
    private String image;
    private String Setaside1;
    private String Setaside2;
}
