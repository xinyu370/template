package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo.common.BusinessException;
import com.example.demo.common.pub.PageResult;
import com.example.demo.common.vo.ExtReqVo;
import com.example.demo.common.resp.ExtRespVo;
import com.example.demo.pojo.ExtPojo;
import com.example.demo.pojo.mapper.ExtMapper;
import com.example.demo.pojo.repository.ExtPojoRepository;
import com.example.demo.service.ExtService1;
import com.example.demo.utils.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Slf4j
public class ExtService1Impl implements ExtService1 {

    @Autowired
    private ExtPojoRepository extPojoRepository;
    @Autowired
    private ExtMapper extMapper;

    @Override
    public void addExt(ExtReqVo reqVo) {
        extPojoRepository.save(copyExt(reqVo));
    }

    @Override
    public ExtPojo getExtById(Long id) {
        return extPojoRepository.findById(id).orElseThrow(()->new BusinessException("未找到数据"));
    }

    @Override
    public PageResult<ExtRespVo> getPageExt(ExtReqVo reqVo) {
        if(StringUtils.isEmpty(reqVo.getTag())){
            throw new BusinessException("业务标识不能为空");
        }
        Page<ExtRespVo> page = PageUtil.buildPage(reqVo.getPageNum(), reqVo.getPageSize());
        List<ExtRespVo> reqVos = extMapper.getExtPageList(page,reqVo);
        return PageResult.<ExtRespVo>builder()
                .data(reqVos)
                .count(page.getTotal())
                .build();
    }

    @Override
    public void deleteExt(Long id) {
        extPojoRepository.deleteById(id);
    }

    @Override
    public void updateExt(ExtReqVo reqVo) {
        if(reqVo.getId()==null){
            throw new BusinessException("更新数据id不能为空");
        }
        ExtPojo extPojo = copyExt(reqVo);
        extPojo.setId(reqVo.getId());
        extPojoRepository.save(extPojo);
    }

    private ExtPojo copyExt(ExtReqVo reqVo){
        ExtPojo extPojo = new ExtPojo();
        extPojo.setTag(reqVo.getTag());
        extPojo.setExt1(reqVo.getExt1());
        extPojo.setExt2(reqVo.getExt2());
        extPojo.setExt3(reqVo.getExt3());
        extPojo.setExt4(reqVo.getExt4());
        extPojo.setExt5(reqVo.getExt5());
        extPojo.setExt6(reqVo.getExt6());
        extPojo.setExt7(reqVo.getExt7());
        extPojo.setExt8(reqVo.getExt8());
        extPojo.setExt9(reqVo.getExt9());
        extPojo.setExt10(reqVo.getExt10());
        return extPojo;
    }
}
