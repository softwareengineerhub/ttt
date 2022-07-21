package com.app.controller;

import com.app.producer.MyProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MyController {
    @Autowired
    private MyProducer myProducer;

    @GetMapping("/produce")
    public void produce(HttpServletRequest request) {
        String msg = request.getParameter("msg");
        myProducer.send(msg);
    }
}
