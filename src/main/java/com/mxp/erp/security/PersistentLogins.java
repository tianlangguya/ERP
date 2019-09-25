package com.mxp.erp.security;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

import java.sql.Timestamp;

@TableName("PERSISTENT_LOGINS")
public class PersistentLogins {
    public static final String USER_NAME = "USER_NAME";
    public static final String SERIES = "SERIES";
    public static final String TOKEN = "TOKEN";
    public static final String LAST_USED = "LAST_USED";
    @TableField("USERNAME")
    private String userName;
    @TableField("SERIES")
    private String series;
    @TableField("TOKEN")
    private String token;
    @TableField("LAST_USED")
    private Timestamp lastUsed;

    public String getUserName() {
        return userName;
    }

    public String getSeries() {
        return series;
    }

    public String getToken() {
        return token;
    }

    public Timestamp getLastUsed() {
        return lastUsed;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setLastUsed(Timestamp lastUsed) {
        this.lastUsed = lastUsed;
    }
}
