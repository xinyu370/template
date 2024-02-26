package com.example.demo.controller;

import com.example.demo.common.BusinessException;
import com.example.demo.common.R;
import com.example.demo.common.pub.PageResult;
import com.example.demo.common.vo.ExtReqVo;
import com.example.demo.common.resp.ExtRespVo;
import com.example.demo.pojo.ExtPojo;
import com.example.demo.service.ExtService1;
import com.example.demo.utils.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "通用CRUD")
public class ExtController {

    @Autowired
    private ExtService1 extService1;

    @PostMapping("/addExt")
    @ApiOperation("新增")
    public R addExt(@RequestBody ExtReqVo reqVo){
        if(StringUtils.isEmpty(reqVo.getTag())){
            throw new BusinessException("请输入业务标识");
        }
        extService1.addExt(reqVo);
        return R.ok();
    }

    @DeleteMapping("/deleteExt")
    @ApiOperation("删除")
    public R deleteExt(@RequestParam("id") Long id){
        extService1.deleteExt(id);
        return R.ok();
    }

    @PostMapping("/updateExt")
    @ApiOperation("更新")
    public R updateExt(@RequestBody ExtReqVo reqVo){
        if(StringUtils.isEmpty(reqVo.getTag())){
            throw new BusinessException("请输入业务标识");
        }
        extService1.updateExt(reqVo);
        return R.ok();
    }

    @PostMapping("/getExtList")
    @ApiOperation("查询分页列表")
    public R<PageResult> getExtList(@RequestBody ExtReqVo reqVo){
        if(StringUtils.isEmpty(reqVo.getTag())){
            throw new BusinessException("请输入业务标识");
        }
        final PageResult<ExtRespVo> pageExt = extService1.getPageExt(reqVo);
        return R.ok(pageExt);
    }

    @GetMapping("/getExtById")
    @ApiOperation("根据id获取")
    public R<ExtPojo> getExtById(@RequestParam("id") Long id){

        return R.ok(extService1.getExtById(id));
    }
}
