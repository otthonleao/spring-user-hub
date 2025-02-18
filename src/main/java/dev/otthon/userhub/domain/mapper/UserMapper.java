package dev.otthon.userhub.domain.mapper;

import dev.otthon.userhub.domain.dto.UserDTO;
import dev.otthon.userhub.domain.dto.request.UserRequest;
import dev.otthon.userhub.domain.dto.request.UserUpdateRequest;
import dev.otthon.userhub.domain.model.User;

public interface UserMapper {

    User fromRequestToEntity(UserRequest request);

    void fromRequestToUpdate(UserUpdateRequest request, User entity);

    UserDTO fromEntityToResponse(User entity);

}
