package com.hy.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult<T> {
    private Integer pageNum;
    private Integer  pageSize;
    private long  totalPage;
    private List<T> list;
}
