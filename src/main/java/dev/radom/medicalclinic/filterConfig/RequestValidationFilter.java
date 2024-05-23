package dev.radom.medicalclinic.filterConfig;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import java.io.IOException;

public class RequestValidationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        var httpRequest = (HttpServletRequest) request;
        var httpResponse = (HttpServletResponse) response;

        String requestId = httpRequest.getHeader("Request-Id");
        if (requestId == null || requestId.isBlank()) {
//            httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            httpResponse.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing Request-Id header");
            return;
        }
        filterChain.doFilter(request, response);
    }

    @Bean
    public FilterRegistrationBean<RequestValidationFilter> requestValidationFilterRegistration(RequestValidationFilter requestValidationFilter) {
        FilterRegistrationBean<RequestValidationFilter> registrationBean = new FilterRegistrationBean<>(requestValidationFilter);
        registrationBean.addUrlPatterns("/api/v1/auth/*"); // Apply the filter to all URLs starting with /api/v1/auth/login
        return registrationBean;
    }
}
