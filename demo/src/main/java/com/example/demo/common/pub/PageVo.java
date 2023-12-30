package com.example.demo.common.pub;

import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = false)
public class PageVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 每页数据的数量
     */
    private Integer pageSize=25;

    /**
     * 页码
     */
    private Integer pageNum=0;

    /**
     * 全选所有
     * @return
     */
    private String selectAllFlag;


    public String getSelectAllFlag() {
        return selectAllFlag;
    }

    public void setSelectAllFlag(String selectAllFlag) {
        this.selectAllFlag = selectAllFlag;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }
}
