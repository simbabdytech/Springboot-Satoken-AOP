package com.backend.framework.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("consumer")
public class Customer {
    //    @TableId(type = IdType.AUTO)
    Integer id;
    String username;
    String password;
    String role;
}
