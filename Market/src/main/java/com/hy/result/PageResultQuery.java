package com.hy.result;

import lombok.Data;

import java.util.List;
@Data
public class PageResultQuery {
    private Integer pageNum;
    private Integer  pageSize;
    private String cname;
    private List<String> medias;
    private List<String> contends;
    private List<String> direction;
    private List<String> level;
    private List<String> type;
    private List<String> kolName;
    //分活动1、分活动分媒介2、分活动分媒介分内容形式3、分活动分媒介分内容方向4
    // 分活动分媒介分达人等级5、分活动分媒介分达人类型6、分活动分帖子7
    private Integer status;
}
