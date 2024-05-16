package dev.radom.medicalclinic.api.auth.dto;



import lombok.Builder;

@Builder
public record AuthDto(String accessToken,
                      String refreshToken,
                      String type) {
}