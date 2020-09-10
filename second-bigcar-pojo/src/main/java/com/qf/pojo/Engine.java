package com.qf.pojo;

import lombok.Data;

import javax.persistence.*;

@Table
@Data
@Entity
public class Engine {
    @Id
    @Column(name = "engine_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer engineId;
    /**
     * 品牌id(逻辑外键)
     */
    @Column(name = "cb_id")
    private Integer cbId;
    /**
     * 排量
     */
    private String displace;
    /**
     * 进气形式
     */
    private String airintake;
    /**
     * 气缸数
     */
    private Integer cylinders;
    /**
     * 最大马力(ps)
     */
    @Column(name = "max_hp")
    private Integer maxHp;
    /**
     * 最大扭矩(N*m)
     */
    @Column(name = "max_torque")
    private Double maxTorque;
    /**
     * 燃料类型
     */
    @Column(name = "fuel_type")
    private String fuelType;
    /**
     * 燃油标号
     */
    @Column(name = "fuel_mark")
    private String fuelMark;
    /**
     * 供油方式
     */
    @Column(name = "fuel_supply")
    private String fuelSupply;
    /**
     * 排放标准
     */
    private String standard;
    /**
     * 备用字段1
     */
    private String setaside1;
    /**
     * 备用字段2
     */
    private String setaside2;
}
