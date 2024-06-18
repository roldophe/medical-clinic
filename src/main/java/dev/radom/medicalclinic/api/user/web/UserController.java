package dev.radom.medicalclinic.api.user.web;

import dev.radom.medicalclinic.api.user.service.UserService;
import dev.radom.medicalclinic.pagination.PageWrapper;
import dev.radom.medicalclinic.pagination.PayloadApi;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * AuthController class for managing user-related operations in the medical clinic application.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public PayloadApi<?> me(Authentication authentication) {
//        UserDto data = userService.me(authentication);
        UserDto data = null;
        try {
            data= userService.me(authentication);

        }catch (Exception e){
            e.printStackTrace();
        }
        return PayloadApi.builder()
                .success(true)
                .code(200)
                .message("Authenticated user retrieved successfully")
                .error(null)
                .payload(data)
                .build();
    }

    @PostMapping
    public PayloadApi<?> createNewUser(@RequestBody @Valid NewUserDto newUserDto) {
        userService.createNewUser(newUserDto, null); // Assuming this method returns a UUID upon successful creation
        return PayloadApi.builder()
                .success(true)
                .code(201) // Created
                .message("New user created successfully")
                .error(null)
                .payload(null) // No payload needed for this operation
                .build();
    }


    @GetMapping("/{uuid}")
    public PayloadApi<?> findUserByUuid(@PathVariable UUID uuid) {
        UserDto data = userService.findByUuid(uuid);
        return PayloadApi.builder()
                .success(true)
                .code(200)
                .message("User found successfully")
                .error(null)
                .payload(data)
                .build();
    }

    @GetMapping()
    public PayloadApi<?> getAllUsers(@RequestParam(defaultValue = "0") Integer pageNum,
                                     @RequestParam(defaultValue = "10") Integer pageSize) {

        PageWrapper<UserDto> users = userService.findAll(pageNum,pageSize);

        return PayloadApi.builder()
                .success(true)
                .code(200)
                .message("All active users retrieved successfully")
                .error(null)
                .payload(users)
                .build();
    }

    @DeleteMapping("/{uuid}")
    public PayloadApi<?> deleteByUuid(@PathVariable UUID uuid) {
        userService.deleteByUuid(uuid);
        return PayloadApi.builder()
                .success(true)
                .code(204) // No Content
                .message("User deactivated successfully")
                .error(null)
                .payload(null)
                .build();
    }

    @PutMapping("/{uuid}")
    public PayloadApi<?> updateIsDeletedByUuid(@PathVariable UUID uuid, @RequestBody IsDeletedDto deactivated) {
        userService.updateIsDeletedByUuid(uuid, deactivated.isDeleted());
        return PayloadApi.builder()
                .success(true)
                .code(200)
                .message("Deactivated status updated successfully")
                .error(null)
                .payload(null)
                .build();
    }

    @PatchMapping("/{uuid}")
    public PayloadApi<?> updateUserByUuid(@PathVariable UUID uuid, @RequestBody UpdateUserDto updateUserDto) {
        userService.updateByUuid(uuid, updateUserDto);
        return PayloadApi.builder()
                .success(true)
                .code(200)
                .message("User information updated successfully")
                .error(null)
                .payload(null)
                .build();
    }
}
