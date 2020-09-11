package com.qf.pojo;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class BaseResult<T> {

    private List<T> list;
    private Long total;
    private String Message;
    private Integer MessageId;

}
