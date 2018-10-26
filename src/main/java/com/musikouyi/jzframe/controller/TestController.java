package com.musikouyi.jzframe.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.*;

@Slf4j
@RestController
@RequestMapping("/notify")
public class TestController {

    @PostMapping("/checkResult")
    public String notify(HttpServletRequest request) throws IOException {

        String head = request.getHeader("Content-Type");
        String fuck = request.getParameter("fuck");
        System.out.println("head: " + head);
        System.out.println("fuck: " + fuck);

//        String[] a = request.getParameterValues("transdata");

//        for (String item: a
//             ) {
//            System.out.println(item);
//        }

//        BufferedReader br= new BufferedReader(new InputStreamReader(request.getInputStream()));
//        String postData = null;
//        String line;
//        while((line = br.readLine()) != null){
//            postData += line;
//        }
//        log.info("postData: " + postData);

        return "success";
    }

}
