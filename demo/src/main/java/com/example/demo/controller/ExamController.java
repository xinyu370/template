package com.example.demo.controller;

import com.example.demo.common.R;
import com.example.demo.common.pub.PageResult;
import com.example.demo.common.vo.ExamVo;
import com.example.demo.service.ExamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "考试试卷")
public class ExamController {

    @Autowired
    private ExamService examService;

    @PostMapping("/addExam")
    @ApiOperation("新增试卷")
    public R addExam(@RequestBody ExamVo examVo){
        examService.addExam(examVo);
        return R.ok();
    }

    @DeleteMapping("/deleteMapping")
    @ApiOperation("删除")
    public R deleteExam(@RequestParam("id")Long id){
        examService.deleteExam(id);
        return R.ok();
    }

    @PostMapping("/updateExam")
    @ApiOperation("更新试卷")
    public R updateExam(@RequestBody ExamVo examVo){
        examService.updateExam(examVo);
        return R.ok();
    }

    @PostMapping("/getExamPageList")
    @ApiOperation("查询试卷分页列表")
    public R<PageResult> getExamPageList(@RequestBody ExamVo examVo){
        final PageResult examPageList = examService.getExamPageList(examVo);
        return R.ok(examPageList);
    }

    @GetMapping("/getExamById")
    @ApiOperation("根据id获取试卷详情")
    public R<ExamVo> getExamById(@RequestParam("id")Long id){
        return R.ok(examService.getExamById(id));
    }
}
