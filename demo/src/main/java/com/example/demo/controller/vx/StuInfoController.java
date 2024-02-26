package com.example.demo.controller.vx;

import com.example.demo.common.R;
import com.example.demo.pojo.StudyInfo;
import com.example.demo.service.StuService;
import com.example.demo.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "课程进度管理")
public class StuInfoController {

    @Autowired
    private StuService stuService;

    @PostMapping("/addAndUpdateStuInfo")
    @ApiOperation("新增或者更新课程进度")
    public R addAndUpdateStuInfo(@RequestBody StudyInfo req){
        stuService.addAndUpdateStudyInfo(req);
        return R.ok();
    }

    @GetMapping("/getStudyInfo")
    @ApiOperation("获取课程进度")
    public R<List<StudyInfo>> getStudyInfo(@RequestParam("name") String name){
        Assert.hasText(name,"姓名不能为空");
        return R.ok(stuService.getStudyInfo(name));
    }
}
