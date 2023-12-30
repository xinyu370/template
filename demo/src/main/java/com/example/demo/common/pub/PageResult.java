package com.example.demo.common.pub;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 分页实体类
 *
 * @author dmp
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> implements Serializable {
    private static final long serialVersionUID = -275582248840137389L;
    /**
     * 总数
     */
    private Long count;

    /**
     * 当前页结果集
     */
    private List<T> data;


    public static <T> PageResult<T> empty() {
        return new PageResult<>(0L, new ArrayList<>());
    }

}
