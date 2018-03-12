package com.bays.utils;

public class Field {

    //TODO: 2017/10/27 全局字段声明

    public static final String persent= "%";
    public static final String coma = ",";
    public static final String spot = ".";
    public static final int startPage = 1;
    public static final int endPage = 5;
    public static final String TRUE ="true";
    public static final String FALSE ="false";
    public static final String ENCODE="UTF-8";
    public static final String SECRETKEY=SysUtil.secretKey();
    /**
     * 公共字段
     */
    public class Common{
        public static final String RESP_CODE="respcode";
        public static final String RESP_MSG="respmsg";
        public static final String DATAS = "datas";
        public static final String TABLE = "table";
        public static final String CLOUMN = "cloumn";
        public static final String MD5 = "MD5";
    }

    /**
     * 数据库表名
     */
    public class Table{
        public static final String USER_INFO="user_info";
    }

    /**
     * 数据库字段
     */
    public class Cloumn{
        public static final String USER_NAME = "user_name";
        public static final String PASSWORD = "password";
        public static final String EMAIL = "email";
        public static final String ADD_TIME = "add_time";
        public static final String STATUS = "status";
        public static final String LATEST_LOGIN_TIME = "latest_login_time";
    }

}
