package com.qf.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "car_brand")
@Entity
public class Brand {
    @Id
    @Column(name = "cb_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int brandId;

    @Column(name = "car_brand")
    private String brand;

    @Column(name = "car_series")
    private String series;

    private String setaside1;
    private String setaside2;

}
