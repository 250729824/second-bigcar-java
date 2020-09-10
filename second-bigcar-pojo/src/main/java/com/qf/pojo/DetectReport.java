package com.qf.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "detect_report")
@Entity
public class DetectReport {

    @Id
    @Column(name = "rep_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer repId;
    /**
     * 商品id(逻辑外键)
     */
    @Column(name = "car_id")
    private Integer carId;
    /**
     * 报告url
     */
    @Column(name = "rep_url")
    private String repUrl;
}
