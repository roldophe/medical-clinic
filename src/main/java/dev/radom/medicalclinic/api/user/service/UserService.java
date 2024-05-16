package dev.radom.medicalclinic.api.user.service;

import dev.radom.medicalclinic.api.user.web.NewUserDto;
import dev.radom.medicalclinic.api.user.web.UpdateUserDto;
import dev.radom.medicalclinic.api.user.web.UserDto;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserDto me(Authentication authentication );

    /**
     * This method is used to create a new user resource into a database
     *
     * @param newUserDto   is request data from a client
     * @param verifiedCode
     */
    void createNewUser(NewUserDto newUserDto, String verifiedCode);

    /**
     * Updates the information of a user.
     *
     * @param uuid The unique identifier of the user whose information is being updated.
     * @param updateUserDto The DTO containing the new user information.
     */
    void updateByUuid(UUID uuid, UpdateUserDto updateUserDto);

    /**
     * This method is used to retrieve a user resource in the database base on its UUID
     *
     * @param uuid the UUID of the user to be retrieved
     * @return a UserDto object representing the user
     */
    UserDto findByUuid(UUID uuid);

    /**
     * This method is used to delete a user resource in the database based on its UUID (Permanently)
     *
     * @param uuid the UUID of the user to be deleted
     */
    void deleteByUuid(UUID uuid);


    /**
     * Updates the deactivated status of a user.
     *
     * @param uuid The unique identifier of the user whose status is being updated.
     * @param isDeletedDto The DTO containing the new deactivated status.
     */
    void updateIsDeletedByUuid(UUID uuid, Boolean isDeletedDto);

    /**
     * This method is used to retrieve a list of users in the database
     *
     * @return a list of UserDto object representing the user
     */
    List<UserDto> findAll();
}
