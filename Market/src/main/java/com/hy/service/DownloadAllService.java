package com.hy.service;

import com.hy.result.PageResultQuery;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface DownloadAllService {
    void downloadAll(HttpServletResponse response, PageResultQuery query) throws IOException;
}
