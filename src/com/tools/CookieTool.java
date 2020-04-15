package com.tools;

import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.Map;

public class CookieTool {
    public static Map<String,Cookie> setCookieMap(Cookie[] cookies){
        Map<String,Cookie> ckMap = new HashMap<>();
        if(cookies!=null){
            for (Cookie cookie : cookies) {
                ckMap.put(cookie.getName(),cookie);
            }
        }
        return ckMap;
    }

    public static Cookie readCookie(String name,Map<String,Cookie> ckMap){
        Cookie cookie = null;
        if(ckMap.containsKey(name)){
            cookie = ckMap.get(name);
        }
        return cookie;
    }
}
