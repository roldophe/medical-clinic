package dev.radom.medicalclinic.pagination;


import lombok.Builder;

@Builder
public record PayloadApi<T>(Boolean success,
                            Integer code,
                            String message,
                            String error,
                            T payload) {
}