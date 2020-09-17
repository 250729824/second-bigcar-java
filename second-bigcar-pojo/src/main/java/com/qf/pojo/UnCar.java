package com.qf.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 联合查询的类（car-car_brand-picture）
 */
@Data
public class UnCar implements Serializable {

    private String pic_url;//图片
    private Integer car_id;//id
    private Integer cb_id;//品牌id
    private Date car_update_time;//上架时间
    private String car_desc;//商品品概述
    private Date car_upload;//上牌时间
    private Double car_distance;//里程数
    private String car_displace;//排量
    private Integer car_gearbox;//变速箱
    private String car_standard;//排放标准
    private Integer car_usedtime;//车龄
    private String car_brand;//品牌
    private String car_series;//系列
    private Double newcar_price;//全新车价
    private Double nowcar_price;//现车价
    private String car_color;//颜色
    private String licence_location;//车牌所在地
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date createtime;
     private Double  car_evaluateprice;


}
