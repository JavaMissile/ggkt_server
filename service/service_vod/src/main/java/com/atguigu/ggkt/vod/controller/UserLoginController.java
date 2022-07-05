package com.atguigu.ggkt.vod.controller;

import com.atguigu.ggkt.result.Result;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Missile
 * @Date 2022-06-30-14:31
 */
//@CrossOrigin//跨域临时解决方案
@RestController
@RequestMapping("/admin/vod/user")
public class UserLoginController {


    @PostMapping("login")
    public Result login(){
        Map<String,Object> map= new HashMap<>();
        map.put("token","admin-token");
        return Result.ok(map);
    }

    @GetMapping("info")
    public Result info(){
        Map<String,Object> map= new HashMap<>();
        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        map.put("introduction","I am a super administrator");
        map.put("name","Super Admin");
        map.put("roles","admin");
        return Result.ok(map);
    }
    @PostMapping("logout")
    public Result logout(){
        Map<String,Object> map= new HashMap<>();
        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        map.put("introduction","I am a super administrator");
        map.put("name","Super Admin");
        map.put("roles","admin");
        return Result.ok(map);
    }
}
