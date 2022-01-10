package com.example.calculator.model;

import java.math.BigDecimal;

public class Result {
    public BigDecimal result;

    private Result() {

    }

    public static Result getResult(BigDecimal result) {
        Result res = new Result();
        res.result = result;
        return res;
    }
}
