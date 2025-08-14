package com.hy.pojo;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class PlatBean {
    @ExcelProperty("活动id")
    private String cid;
    @ExcelProperty("活动名称")
    private String cname;
    @ExcelProperty("媒介平台")
    private String media;
    @ExcelProperty("数据加和")
    private String field;
    @ExcelProperty("触点类型")
    private String point;
}
