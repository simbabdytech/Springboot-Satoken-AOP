package com.backend.framework;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import cn.dev33.satoken.SaManager;

@SpringBootApplication
@MapperScan("com.backend.framework.mapper")
public class BackendFrameworkApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendFrameworkApplication.class, args);
//     	System.out.println("启动成功：sa-token配置如下：" +SaManager.getConfig() );
	}

}
