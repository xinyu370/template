package com.example.demo.service.impl;

import com.example.demo.service.HealthRecordService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class HealthRecordServiceImpl implements HealthRecordService {
    @Override
    public List<String> getHealthRecordsByUsername(String userName) {

        return Arrays.asList("hello","world");
    }

    @Override
    @PreAuthorize("hasAuthority('DELETE')")
    public String executeDel() {
        return "success";
    }
}
