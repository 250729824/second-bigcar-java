package com.qf.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "internal_config")
public class InternalConfig {

    @Id
    @Column(name = "internal_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer internalId;
    /**
     * 品牌id(逻辑外键)
     */
    @Column(name = "cb_id")
    private Integer cbId;
    /**
     * 多功能方向盘
     */
    @Column(name = "multi_wheel")
    private String multiWheel;
    /**
     * 定速巡航
     */
    @Column(name = "curise_control")
    private String curiseControl;
    /**
     * 后排独立空调
     */
    @Column(name = "back_indep_condition")
    private String backIndepCondition;
    /**
     * 空调控制方式
     */
    @Column(name = "aircondition_control")
    private String airconditionControl;
    /**
     * GPS导航
     */
    private String gps;
    /**
     * 倒车雷达
     */
    @Column(name = "reverse_radar")
    private String reverseRadar;
    /**
     * 倒车影像系统
     */
    @Column(name = "rev_video_sys")
    private String revVideoSys;
    /**
     * 真皮座椅
     */
    @Column(name = "leather_seat")
    private String leatherSeat;
    /**
     * 前/后排座椅加热
     */
    @Column(name = "seat_heat")
    private String seatHeat;

}
