package com.qf.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "extra_config")
public class ExtraConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "extra_id")
    private Integer extraId;
    /**
     * 品牌id（l逻辑外键）
     */
    @Column(name = "cb_id")
    private Integer cbId;
    /**
     * 电动天窗
     */
    @Column(name = "ele_sunroof")
    private String eleSunroof;
    /**
     * 全景天窗
     */
    @Column(name = "all_skyroof")
    private String allSkyroof;
    /**
     * 电动吸合门
     */
    @Column(name = "ele_sucdoor")
    private String eleSucdoor;
    /**
     * 感应后备厢
     */
    @Column(name = "it_truk")
    private String itTruk;
    /**
     * 感应雨刷
     */
    @Column(name = "sensing_wiper")
    private String sensingWiper;
    /**
     * 后雨刷
     */
    @Column(name = "after_wiper")
    private String afterWiper;
    /**
     * 前/后电动车窗
     */
    @Column(name = "ba_elewindows")
    private String baElewindows;
    /**
     * 后视镜电动调节
     */

    @Column(name = "after_mirror")
    private String afterMirror;
    /**
     * 后视镜加热
     */
    @Column(name = "mirro_heating")
    private String mirroHeating;
    /**
     * 备用字段1
     */
    private String setaside1;
    /**
     * 备用字段2
     */
    private String setaside2;
}
