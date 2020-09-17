package com.qf.dao;

import com.qf.pojo.CarParameter;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ICarParameterDao {

    CarParameter getAllCarParameter(Integer cb_id);
}
