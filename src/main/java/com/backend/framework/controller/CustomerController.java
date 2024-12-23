package com.backend.framework.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.backend.framework.aop.AuthCheck;
import com.backend.framework.dto.consumer.LoginParam;
import com.backend.framework.dto.consumer.RegisterParam;
import com.backend.framework.model.Customer;
import com.backend.framework.service.ICustomerService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/consumer")
public class CustomerController {
    @Autowired
    ICustomerService userService;

    @GetMapping("/login")
    public SaTokenInfo login(LoginParam requests) {
        Customer consumer = userService.getOne(new QueryWrapper<Customer>().
                eq("username", requests.getUsername()).
                eq("password", SaSecureUtil.sha256(requests.getPassword())));
        if (consumer != null) {
            StpUtil.login(consumer.getId());
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
            Customer newConsumer = new Customer();
            newConsumer.setUsername(request.getUsername());
            newConsumer.setPassword(SaSecureUtil.sha256(request.getPassword()));
            newConsumer.setRole(request.getRole());
            try {
                userService.save(newConsumer);
                StpUtil.login(newConsumer.getId());
                return StpUtil.getTokenInfo();
            } catch (Exception exception) {
                return null;
            }

        } else {
            return null;
        }
    }


    @GetMapping("/list")
    public List<Customer> register(HttpServletRequest request) {
        return userService.list();
    }

}

