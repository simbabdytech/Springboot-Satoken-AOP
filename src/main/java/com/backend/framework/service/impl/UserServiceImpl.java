package com.backend.framework.service.impl;
import com.backend.framework.mapper.UserMapper;
import com.backend.framework.model.User;
import com.backend.framework.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service("UserService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
}
