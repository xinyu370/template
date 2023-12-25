package com.example.demo.service;

import java.util.List;

public interface HealthRecordService {
    List<String> getHealthRecordsByUsername(String userName);

    String executeDel();
}
