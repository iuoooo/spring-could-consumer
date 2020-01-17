package com.iuoooo.controller;

import com.iuoooo.remote.HelloRemote;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Random;

@RestController
public class HelloController {
    @Resource
    HelloRemote helloRemote;

    @RequestMapping("/hello/{name}")
    public String index(@PathVariable("name") String name) {
        return helloRemote.hello(name);
    }

    @GetMapping("/get/{id}")
    @HystrixCommand(fallbackMethod = "queryUserByIdFallback")
    public String queryUserById(Long id){
        // 为了演示超时现象，我们在这里然线程休眠,时间随机 0~2000毫秒
        long l = id / 0;
        return "success";
    }

    public String queryUserByIdFallback(Long id){
        String str="紧急处理返回";
        return str;
    }
}
