package com.wujie.common.util;

public class UserContext {
    private static ThreadLocal<String> userId = new ThreadLocal<>();

    public static void setUserId(String id){
        userId.set(id);
    }

    public static void removeUserid(){
        userId.remove();
    }

    public static String getUserId(){
        return userId.get();
    }
}
