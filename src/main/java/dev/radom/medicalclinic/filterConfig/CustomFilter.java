package dev.radom.medicalclinic.filterConfig;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class CustomFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        // Log the start of the filter execution
        System.out.println("CustomFilter started. Request URL: " + request.getRequestURI());

        // Check if the request is for a static resource (e.g., CSS, JS, images)
        String[] pathsToSkip = {"/css/", "/js/", "/images/"};
        boolean skipProcessing = false;
        for (String path : pathsToSkip) {
            if (request.getRequestURI().startsWith(path)) {
                skipProcessing = true;
                break;
            }
        }

        // Skip processing for static resources
        if (skipProcessing) {
            chain.doFilter(request, response); // Proceed to the next filter in the chain
            return; // Exit the method early
        }

        try {
            // Your custom logic here
            // For demonstration, let's just log the request URI again
            System.out.println("Processing request: " + request.getRequestURI());

            // Call the next filter in the chain
            chain.doFilter(request, response);
        } finally {
            // Log the end of the filter execution
            System.out.println("CustomFilter ended.");
        }
    }

    @Bean
    public FilterRegistrationBean<CustomFilter> CustomFilterRegistration(CustomFilter customFilter) {
        FilterRegistrationBean<CustomFilter> registrationBean = new FilterRegistrationBean<>(customFilter);
        registrationBean.addUrlPatterns("/api/v1/auth/*"); // Apply the filter to all URLs starting with /api/v1/auth/login
        return registrationBean;
    }
}
