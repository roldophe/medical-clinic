//package dev.radom.medicalclinic.filterConfig;
//
//import jakarta.servlet.*;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//
//@Component
//public class StaticKeyAuthenticationFilter implements Filter {
//    @Value("${authorization.key}")
//    private String authorizationKey;
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//            var request = (HttpServletRequest) servletRequest;
//            var response = (HttpServletResponse) servletResponse;
//
//            String authorization = request.getHeader("Authorization");
//            if (authorization != null && authorization.startsWith(authorizationKey)) {
//                filterChain.doFilter(request, response);
//            }else {
//                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            }
//    }
//    @Bean
//    public FilterRegistrationBean<StaticKeyAuthenticationFilter> staticKeyAuthenticationFilterRegistration() {
//        FilterRegistrationBean<StaticKeyAuthenticationFilter> registrationBean = new FilterRegistrationBean<>(this);
//        registrationBean.addUrlPatterns("/api/v1/auth/*"); // Apply the filter to all URLs starting with /api/v1/auth/*
////        registrationBean.addUrlPatterns("/api/v1/users/me"); // Apply the filter to all URLs starting with /api/v1/auth/*
//        return registrationBean;
//    }
//}
