package com.qf.pojo;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "security_config")
public class SecurityConfig {

    @Id
    @Column(name = "security_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer securityId;
    /**
     * 品牌id(逻辑外键)
     */
    @Column(name = "cb_id")
    private Integer cbId;
    /**
     * 主副驾驶安全气囊
     */
    @Column(name = "mv_airbag")
    private String mvAirbag;
    /**
     * 前后排侧气囊
     */
    @Column(name = "fb_side_airbag")
    private String fbSideAirbag;
    /**
     * 前后排头部气囊
     */
    @Column(name = "fb_head_airbag")
    private String fbHeadAirbag;
    /**
     * 胎压监测
     */
    @Column(name = "pressure_detect")
    private String pressureDetect;
    /**
     * 车内中控锁
     */
    private String lock;
    /**
     * 儿童座椅接口
     */
    private String babysit;
    /**
     * 无钥匙启动
     */
    private String nokeystartup;
    /**
     * 防抱死
     */
    private String abs;
    /**
     * 车身稳定控制
     */
    private String esp;
    /**
     * 备用字段1
     */
    private String setaside1;
    /**
     *
     */
    private String setaside2;
}
