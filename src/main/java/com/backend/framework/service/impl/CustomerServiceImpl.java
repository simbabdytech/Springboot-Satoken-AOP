package com.backend.framework.service.impl;

import com.backend.framework.mapper.CustomerMapper;
import com.backend.framework.model.Customer;
import com.backend.framework.service.ICustomerService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service("ConsumerService")
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements ICustomerService {
}

