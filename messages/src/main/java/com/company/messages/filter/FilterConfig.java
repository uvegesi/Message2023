package com.company.messages.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.Arrays;

@Component
public class FilterConfig implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        for (int i = 0; i < httpServletRequest.getMethod().length(); i++) {
            System.out.println(httpServletRequest.getMethod());
            //servletRequest.getParameterMap()
            //      .forEach((k, v) -> System.out.println(k + ": " + Arrays.toString(v)));
            //filterChain.doFilter(servletRequest, servletResponse);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
