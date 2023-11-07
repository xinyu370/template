package com.example.demo.controller;

import com.example.demo.po.UserEntity;
import com.example.demo.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Api(tags = "用户接口")
public class TestController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/addUser")
    @ApiOperation("添加用户")
    public R addUser(@RequestBody UserEntity userEntity){
        userRepository.save(userEntity);
        return R.ok();
    }

    @PostMapping("/getList")
    @ApiOperation("查所有")
    public R<List<UserEntity>> getList(){
        final List<UserEntity> all = userRepository.findAll();
        return R.ok(all);
    }

    @GetMapping("/getUser")
    @ApiOperation("根据id获取用户")
    public R getUser(@RequestParam("id") Long id){
        UserEntity userEntity = userRepository.findById(id).get();
        return R.ok(userEntity);
    }

    @GetMapping("/getByClassfyId")
    @ApiOperation("根据classfyId获取用户")
    public R<List<UserEntity>> getByClassfyId(@RequestParam("classfyId") String classfyId){
        List<UserEntity> allByClassfyId = userRepository.findAllByClassfyId(classfyId);
        return R.ok(allByClassfyId);
    }

    @DeleteMapping("delete")
    @ApiOperation("根据id删除")
    public R delete(@RequestParam("id") Long id){
        userRepository.deleteById(id);
        return R.ok();
    }
}
