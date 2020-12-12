package com.xcq.blogserver.utils;

import com.xcq.blogserver.constants.ServiceExceptionEnum;
import com.xcq.blogserver.core.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * cookie的工具类
 */
public class CookieUtils {

    private static Logger logger = LoggerFactory.getLogger(CookieUtils.class);

    public static void setCookie(HttpServletRequest request,
                                 HttpServletResponse response,
                                 String cookieName, String cookieValue) {
        //cookie设置时间为1天
        doSetCookie(request, response, cookieName, cookieValue, 86400, false);
    }

    private static void doSetCookie(HttpServletRequest request,
                                    HttpServletResponse response,
                                    String cookieName, String cookieValue,
                                    int cookieMaxAge, boolean isEncode) {
        try {
            if (cookieValue == null) {
                cookieValue = "";
            } else if (isEncode) {
                cookieValue = URLEncoder.encode(cookieValue, "utf-8");
            }
            Cookie cookie = new Cookie(cookieName, cookieValue);

            if (cookieMaxAge > 0) {
                cookie.setMaxAge(cookieMaxAge);
            }
            if (request != null) {
                String domainName = getDomainName(request);
                if(!"localhost".equals(domainName)){
                    cookie.setDomain(domainName);
                }
            }
            cookie.setPath("/");
            response.addCookie(cookie);
        } catch (Exception e) {
            throw new ServiceException(ServiceExceptionEnum.SYS_ERROR);
        }

    }

    private static String getDomainName(HttpServletRequest request) throws MalformedURLException {
        //request.getSession()会自动创建一个jsessionid的cookie返回客户端
        StringBuffer url = request.getRequestURL();
        return new URL(url.delete(url.length() -
                request.getRequestURI().length(),
                url.length()).append(request.getSession().
                getServletContext().getContextPath()).append("/").toString()).getHost();
    }
}
