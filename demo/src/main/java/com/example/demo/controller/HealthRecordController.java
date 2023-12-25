package com.example.demo.controller;

import com.example.demo.service.HealthRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HealthRecordController {

    @Autowired
    private HealthRecordService healthRecordService;
    @GetMapping("/deleteA")
    public String deleteA(){
        return healthRecordService.executeDel();
    }



    @GetMapping("/healthrecord")
    public String main(Authentication a, Model model) {
        String userName = a.getName();
        model.addAttribute("username", userName);
        model.addAttribute("healthRecords", healthRecordService.getHealthRecordsByUsername(userName));
        return "health_record.html";

    }

}