package com.first.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/11/7.
 */

@Mapper
public interface UserMapperDao {
    List<Map<String,Object>> findAllUser();
}
