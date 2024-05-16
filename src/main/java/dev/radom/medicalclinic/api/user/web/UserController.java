package dev.radom.medicalclinic.api.user.web;

import dev.radom.medicalclinic.api.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * AuthController class for managing user-related operations in the medical clinic application.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    /**
     * Retrieves the authenticated user's profile.
     *
     * @param authentication The authentication object containing user details.
     * @return A DTO representing the user's profile.
     */
    @Operation(summary = "Retrieves the authenticated user's profile.", description = "REST API to Retrieves the authenticated user's profile inside Medical Clinic Solution.")
    @GetMapping("/me")
    public UserDto me(Authentication authentication) {
        return userService.me(authentication);
    }

    /**
     * Creates a new user in the system.
     *
     * @param newUserDto The DTO containing the new user's details.
     */
    @Operation(summary = "Creates a new user in the system.", description = "REST API to Creates a new user in the system Medical Clinic Solution.")
    @PostMapping
    public void createNewUser(@RequestBody @Valid NewUserDto newUserDto) {
        userService.createNewUser(newUserDto, null);
    }

    /**
     * Finds a user by their UUID.
     *
     * @param uuid The unique identifier of the user.
     * @return A DTO representing the found user.
     */
    @Operation(summary = "Finds a user by their UUID.", description = "REST API to Finds a user by their UUID inside Medical Clinic Solution.")
    @GetMapping("/{uuid}")
    public UserDto findUserByUuid(@PathVariable UUID uuid) {
        return userService.findByUuid(uuid);
    }

    /**
     * Retrieves all active users in the system.
     *
     * @return A list of DTOs representing all active users.
     */
    @Operation(summary = "Retrieves all active users in the system.", description = "REST API to Read All User Activated inside Medical Clinic Solution.")
    @GetMapping()
    public List<UserDto> getAllUsers() {
        return userService.findAll();
    }

    /**
     * Deactivates a user by their UUID.
     *
     * @param uuid The unique identifier of the user to deactivate.
     */
    @Operation(summary = "Deactivates a user by their UUID.", description = "REST API to Deactivates a user by their UUID inside Medical Clinic Solution.")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{uuid}")
    public void deleteByUuid(@PathVariable UUID uuid) {
        userService.deleteByUuid(uuid);
    }

    /**
     * Updates the deactivated status of a user.
     *
     * @param uuid        The unique identifier of the user whose status is being updated.
     * @param deactivated The DTO containing the new deactivated status.
     */
    @Operation(summary = "Updates the deactivated status of a user.", description = "REST API to updates the deactivated status of a user inside Medical Clinic Solution.")
    @PutMapping("/{uuid}")
    public void updateIsDeletedByUuid(@PathVariable UUID uuid, @RequestBody IsDeletedDto deactivated) {
        userService.updateIsDeletedByUuid(uuid, deactivated.isDeleted());
    }

    /**
     * Updates the information of a user.
     *
     * @param uuid          The unique identifier of the user whose information is being updated.
     * @param updateUserDto The DTO containing the new user information.
     */
    @Operation(summary = "Updates the information of a user", description = "REST API to Updates the information of a user inside Medical Clinic Solution.")
    @ResponseStatus(HttpStatus.OK)
    @PatchMapping("/{uuid}")
    public void updateUserByUuid(@PathVariable UUID uuid, @RequestBody UpdateUserDto updateUserDto) {
        userService.updateByUuid(uuid, updateUserDto);
    }
}
