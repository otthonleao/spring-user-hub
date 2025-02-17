package dev.otthon.userhub.domain.mapper;

import dev.otthon.userhub.domain.dto.UserDTO;
import dev.otthon.userhub.domain.dto.request.UserRequest;
import dev.otthon.userhub.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {

    private final ModelMapper modelMapper;

    @Override
    public User fromRequestToEntity(UserRequest request) {
        return modelMapper.map(request, User.class);
    }

    @Override
    public UserDTO fromEntityToResponse(User entity) {
        return modelMapper.map(entity, UserDTO.class);
    }
}
