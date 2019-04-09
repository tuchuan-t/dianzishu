package com.titian.dianzishu;

import com.titian.dianzishu.service.BookResourceServiceImpl_ed2kers;
import com.titian.dianzishu.service.GetBookResourceService;
import com.titian.dianzishu.utils.KeyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//@SpringBootApplication
public class Application {

//    public static void main(String[] args) {
//        SpringApplication.run(Application.class, args);
//    }

    public static void main(String[] args) {

        GetBookResourceService gbrs = new BookResourceServiceImpl_ed2kers();
        gbrs.searchBookResource(KeyConfig.ed2kersUrl);

    }

}



