package dev.otthon.userhub.application.service;

import dev.otthon.userhub.domain.dto.UserDTO;
import dev.otthon.userhub.domain.dto.request.UserRequest;
import dev.otthon.userhub.domain.dto.request.UserUpdateRequest;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;

public interface UserService {

    UserDTO create(final UserRequest request);

    void deleteById(Long id);

    List<UserDTO> listAll();

    UserDTO getById(Long id);

    UserDTO update(Long id, UserUpdateRequest request);

    UserDTO updatePatch(Long id, @Valid Map<String, Object> request);
}
