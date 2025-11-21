package com.tarefa.aula.dtos.http;

import java.util.List;

import java.util.ArrayList;

public class ApiResult<T> {

    private boolean success;
    private List<String> errors = new ArrayList<>();
    private T data;

    public static <T> ApiResult<T> fromSuccess(T data) {
        ApiResult<T> result = new ApiResult<>();
        result.success = true;
        result.data = data;
        return result;
    }

    public static <T> ApiResult<T> fromError(String error) {
        ApiResult<T> result = new ApiResult<>();
        result.success = false;
        result.errors.add(error);
        return result;
    }

    public static <T> ApiResult<T> fromErrors(List<String> errors) {
        ApiResult<T> result = new ApiResult<>();
        result.success = false;
        result.errors = errors != null ? errors : new ArrayList<>();
        return result;
    }

    public boolean isSuccess() {
        return success;
    }

    public List<String> getErrors() {
        return errors;
    }

    public T getData() {
        return data;
    }
}

