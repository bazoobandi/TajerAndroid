package com.zbam.tajer.data.model.api.base;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by z.bazoubandi on 7/18/2018.
 */

public class Settings {

    private String success;
    private String message;
    private int pageSize;
    private List<String> fields = new ArrayList<String>();

    public String getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getFields() {
        return fields;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setFields(List<String> fields) {
        this.fields = fields;
    }

    public boolean issuccess()
    {
        if (success != null && (success.equalsIgnoreCase("1") || success.equalsIgnoreCase("2") )) {
            return true;
        } else {
            return false;
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
