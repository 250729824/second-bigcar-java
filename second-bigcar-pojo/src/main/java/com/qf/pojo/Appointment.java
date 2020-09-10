package com.qf.pojo;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appoint_id")
    private int appointId;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "appoint_time")
    private Timestamp appointTime;
}
