package com.demo.todoapps.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.http.HttpRequest;


@Component
public class RequstLoggingFilter implements Filter {
    private final Logger logger = LoggerFactory.getLogger(RequstLoggingFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        logger.info("Request URL: {}",httpServletRequest.getRequestURI());
        logger.info("Request Method: {}",httpServletRequest.getMethod());
        logger.info("Request AuthType: {}",httpServletRequest.getAuthType());
        logger.info("Request ServletContext: {}",httpServletRequest.getServletContext().getContextPath());


        chain.doFilter(request,response);
    }


}
