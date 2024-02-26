package com.example.demo.service.impl;

import com.example.demo.common.BusinessException;
import com.example.demo.common.pub.PageResult;
import com.example.demo.common.vo.ExamVo;
import com.example.demo.pojo.ExamDetail;
import com.example.demo.pojo.ExamTitle;
import com.example.demo.pojo.repository.ExamDetailRepository;
import com.example.demo.pojo.repository.ExamTitleRepository;
import com.example.demo.service.ExamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamTitleRepository examTitleRepository;
    @Autowired
    private ExamDetailRepository examDetailRepository;

    @Override
    public void addExam(ExamVo examVo) {
        ExamTitle title = new ExamTitle();
        title.setExamName(examVo.getExamName());
        title.setExamStartTime(examVo.getExamStartTime());
        title.setCreateCer(examVo.getCreateCer());
        title.setExamEndTime(examVo.getExamEndTime());
        examTitleRepository.save(title);
        List<ExamDetail> questionList = examVo.getQuestionList();
        if(!CollectionUtils.isEmpty(questionList)){
            questionList = questionList.stream().peek(examDetail -> examDetail.setExamId(title.getId())).collect(Collectors.toList());
            examDetailRepository.saveAll(questionList);
        }
    }

    @Override
    public void deleteExam(Long id) {
        examTitleRepository.deleteById(id);
        examDetailRepository.removeByExamId(id);
    }

    @Override
    public void updateExam(ExamVo examVo) {
        if(examVo.getId()==null){
            throw new BusinessException("更新数据id不能为空");
        }
        deleteExam(examVo.getId());
        addExam(examVo);
    }

    @Override
    public ExamVo getExamById(Long id) {
        ExamVo res = new ExamVo();
        ExamTitle examTitle = examTitleRepository.getById(id);
        BeanUtils.copyProperties(examTitle,res);
        if(examTitle!=null){
            res.setQuestionList(examDetailRepository.getExamDetailByExamId(res.getId()));
        }
        return res;
    }

    @Override
    public PageResult<ExamTitle> getExamPageList(ExamVo examVo) {
        PageRequest page = PageRequest.of(examVo.getPageNum()-1, examVo.getPageSize());
        Page<ExamTitle> all = examTitleRepository.findAll(page);
        return PageResult.<ExamTitle>builder()
                .data(all.getContent())
                .count(all.getTotalElements())
                .build();
    }
}
