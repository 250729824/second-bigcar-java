package com.qf.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import com.qf.pojo.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginFilter extends ZuulFilter {

    private String LOGIN_URL = "/second-bigcar-user/user/loginUser";
    private String SEND_URL = "/second-bigcar-user/user/sendMail";

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER;
    }

    @Override
    public boolean shouldFilter() {

        //拿到当前请求
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        //从reuqest请求中获取请求的路径
        String requestURI = request.getRequestURI();
        if(requestURI.equals(LOGIN_URL) || requestURI.equals(SEND_URL)) {
            return false;
        }
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();
        //从cookie中获取设置到前台的token，在使用token从redis中获取，获取成功，说明用户已经登录，失败，说明用户登陆失败
        Cookie[] cookies = request.getCookies();
        String token = getToken(cookies);
        if(token!=null&&redisTemplate.hasKey(token)){
            return null;
        }
        //终止路由
        currentContext.setSendZuulResponse(false);
        //返回前端数据
        BaseResult baseResult = new BaseResult();
        baseResult.setMessage("用户未登录");
        currentContext.setResponseBody(baseResult.toString());
        HttpServletResponse response = currentContext.getResponse();
        response.setContentType("application/json;charset=utf-8");
        currentContext.setResponse(response);
        return null;
    }

    public String getToken(Cookie[] cookies){

        if(cookies!=null){
            for (Cookie c:cookies){
                if(c.getName().equals("token")){
                    String value = c.getValue();
                    return value;
                }
            }
            return null;
        }
        return null;
    }
}
