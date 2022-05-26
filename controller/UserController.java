package edu.wtbu.controller;

import edu.wtbu.pojo.Result;
import edu.wtbu.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;

@Controller
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public Object login(String email,String password){
        HashMap map = new HashMap();
        map.put("email",email);
        map.put("password",password);
        Result result = userService.login(map);
        return result;
    }

    @RequestMapping("/updatePassword")
    @ResponseBody
    public Object updatePassword(Integer userId,String password){
        HashMap map = new HashMap();
        map.put("userId",userId);
        map.put("password",password);
        Result result = userService.updatePassword(map);
        return result;
    }

    @RequestMapping("/userList")
    @ResponseBody
    public Object userList(Integer roleId,String name,Integer startPage,Integer pageSize){
        HashMap map = new HashMap();
        map.put("roleId",roleId);
        map.put("name",name);
        map.put("startPage",startPage);
        map.put("pageSize",pageSize);
        Result result = userService.getUserList(map);
        return result;
    }

    @RequestMapping("/addUser")
    @ResponseBody
    public Object addUser(String email,String firstName,String lastName,
                          String gender,String dateOfBirth,String phone,
                          String photo,String address,Integer roleId){
        HashMap map = new HashMap();
        map.put("email",email);
        map.put("firstName",firstName);
        map.put("lastName",lastName);
        map.put("gender",gender);
        map.put("dateOfBirth",dateOfBirth);
        map.put("phone",phone);
        map.put("photo",photo);
        map.put("address",address);
        map.put("roleId",roleId);
        String password = email.split("@")[0];
        if(password.length()>6){
            password = password.substring(0,6);
        }
        map.put("password",password);
        Result result = userService.addUser(map);
        return result;
    }

}
