package com.cms.DTO;

import java.util.List;
import java.util.Map;

import java.util.List;
import java.util.Map;

public class ApiResponse {

    private boolean success;
    private String message;
    private Object data;
    private Map<String, List<String>> fieldErrors;

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public Object getData() { return data; }
    public void setData(Object data) { this.data = data; }

    public Map<String, List<String>> getFieldErrors() { return fieldErrors; }
    public void setFieldErrors(Map<String, List<String>> fieldErrors) { this.fieldErrors = fieldErrors; }
}