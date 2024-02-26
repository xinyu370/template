package com.example.demo.controller;

import com.example.demo.common.R;
import com.example.demo.common.pub.PageResult;
import com.example.demo.common.vo.StudentVo;
import com.example.demo.pojo.Student;
import com.example.demo.service.StuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "学员管理")
public class StuManageController {
    @Autowired
    private StuService stuService;

    @PostMapping("/addStu")
    @ApiOperation("增加学生")
    public R addStu(@RequestBody Student student){
        stuService.addStu(student);
        return R.ok();
    }

    @DeleteMapping("/delStudent")
    @ApiOperation("删除")
    public R delStudent(@RequestParam("id") Long id){
        stuService.delStu(id);
        return R.ok();
    }

    @PostMapping("/updateStu")
    @ApiOperation("更新")
    public R updateStu(@RequestBody Student student){
        stuService.updateStu(student);
        return R.ok();
    }
    @GetMapping("/getStuById")
    @ApiOperation("根据id查学员信息")
    public R<StudentVo> getStuById(@RequestParam("id")Long id){
        return R.ok(stuService.getStudent(id));
    }

    @PostMapping("/getStuList")
    @ApiOperation("获取学生列表")
    public R<PageResult<Student>> getStuList(@RequestBody StudentVo studentVo){
        return R.ok(stuService.getStuList(studentVo));
    }
}
