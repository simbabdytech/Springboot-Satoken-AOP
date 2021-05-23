package com.backend.framework.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.backend.framework.aop.AuthCheck;
import com.backend.framework.dto.user.LoginParam;
import com.backend.framework.dto.user.RegisterParam;
import com.backend.framework.model.User;
import com.backend.framework.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    IUserService userService;

    @GetMapping("/login")
    public SaTokenInfo login(LoginParam requests) {
        User user = userService.getOne(new QueryWrapper<User>().
                eq("username", requests.getUsername()).
                eq("password", SaSecureUtil.sha256(requests.getPassword())));
        if (user != null) {
            StpUtil.setLoginId(user.getId());
            return StpUtil.getTokenInfo();
        } else return null;
    }

    @SaCheckLogin
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
        StpUtil.logout();
        return "";
    }

    @AuthCheck(role = {"admin"})
    @GetMapping("/isLogin")
    public String isLogin(HttpServletRequest request) {
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
//        StpUtil.logout();
      return "";

    }

    @GetMapping("/register")
    public SaTokenInfo register(RegisterParam request) {
        if (request.getPassword().equals(request.getAgpassword())) {
            User newUser = new User();
            newUser.setUsername(request.getUsername());
            newUser.setPassword(SaSecureUtil.sha256(request.getPassword()));
            newUser.setRole(request.getRole());
            try {
                userService.save(newUser);
                StpUtil.setLoginId(newUser.getId());
                return StpUtil.getTokenInfo();
            }
            catch (Exception exception)
            {
                return null;
            }

        } else {
            return null;
        }
    }


    @GetMapping("/list")
    public List<User> register(HttpServletRequest request) {
        return userService.list();
    }

}
