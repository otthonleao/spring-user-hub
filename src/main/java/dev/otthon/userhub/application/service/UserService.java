package dev.otthon.userhub.application.service;

import dev.otthon.userhub.domain.dto.UserDTO;
import dev.otthon.userhub.domain.dto.request.UserRequest;

import java.util.List;

public interface UserService {

    UserDTO create(final UserRequest request);

    void deleteById(Long id);

    List<UserDTO> listAll();

    UserDTO getById(Long id);
}
