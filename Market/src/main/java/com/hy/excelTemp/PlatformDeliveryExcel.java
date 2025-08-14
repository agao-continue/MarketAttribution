package com.hy.excelTemp;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class PlatformDeliveryExcel {
    @ExcelProperty("项目Id")
    private String cid;
    @ExcelProperty("项目名称")
    private String cname;
    @ExcelProperty("媒介平台")
    private String media;
    @ExcelProperty("数据加和")
    private String field;
}
