package com.qf.pojo;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Table(name = "car")
@Entity
public class Car {
    @Id
    @Column(name = "car_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int carid;

    @Column(name = "car_update_time")
    private Timestamp updateTime;

    @Column(name = "car_desc")
    private String desc;

    @Column(name = "car_upload")
    private Date uploadTime;

    @Column(name = "car_distance")
    private double distance;

    @Column(name = "car_displace")
    private String carDisplace;

    @Column(name = "car_gearbox")
            private int gearbox;

    @Column(name = "car_standard")
    private String standard;

    @Column(name = "car_usedtime")
    private int usedTime;

    @Column(name = "cb_id")
            private int cbId;

    @Column(name = "newcar_price")
            private double newPrice;

    @Column(name = "nowcar_price")
            private double nowPrice;

    @Column(name = "car_color")
            private String color;

    @Column(name = "licence_location")
    private String licenceLocation;

    private String setaside1;
    private String setaside2;
}
