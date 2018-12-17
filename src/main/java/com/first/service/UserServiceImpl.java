package com.first.service;

import com.first.dao.UserMapperDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/11/7.
 */

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserMapperDao userMapperDao;

    @Override
    public List<Map<String, Object>> findAllUser() {
        return userMapperDao.findAllUser();
    }
}
