package com.hy.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    private String code;
    private String message;
    private Object data;

    public Result(String code, String message) {
        this.code = code;
        this.message = message;
    }

}