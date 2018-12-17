package com.first.controller.user;

import com.first.service.UserService;
import com.first.timetask.TimeTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/11/7.
 */

@Controller
@RequestMapping("/first/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/getAllUser")
    public String getAllUser(ModelMap model){
        model.put("list",userService.findAllUser());
       return "/user/userlist";
    }

    @RequestMapping("/default")
    public ModelAndView toDefault(ModelAndView modelAndView){
        modelAndView.addObject("weqe","欢迎来到默认页面！！！！！");
        modelAndView.setViewName("default");
        return modelAndView;
    }

    @RequestMapping("/class")
    @ResponseBody
    public String forclass(ModelAndView modelAndView) throws ClassNotFoundException {
        Class clazz = Class.forName("com.first.timetask.TimeTask");
        Method[] methods = clazz.getMethods();
        for(Method method : methods){
            System.out.println(method);
        }
        return "";
    }

    @RequestMapping("/redis")
    @ResponseBody
    public String redis(ModelAndView modelAndView) throws ClassNotFoundException {
        Jedis jedis = new Jedis("localhost");
        System.out.println("连接成功");
        //查看服务是否运行
        System.out.println("服务正在运行: "+jedis.ping());
        jedis.set("runoobkey", "www.runoob.com");
        // 获取存储的数据并输出
        System.out.println("redis 存储的字符串为: "+ jedis.get("runoobkey"));

        jedis.lpush("site-list", "Runoob");
        jedis.lpush("site-list", "Google");
        jedis.lpush("site-list", "Taobao");
        // 获取存储的数据并输出
        List<String> list = jedis.lrange("site-list", 0 ,2);
        for(int i=0; i<list.size(); i++) {
            System.out.println("列表项为: "+list.get(i));
        }

        return "";
    }


}
