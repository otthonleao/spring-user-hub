package dev.otthon.userhub.application.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.otthon.userhub.core.exception.ConstraintViolationException;
import dev.otthon.userhub.core.exception.ResourceNotFoundException;
import dev.otthon.userhub.domain.dto.UserDTO;
import dev.otthon.userhub.domain.dto.request.UserRequest;
import dev.otthon.userhub.domain.dto.request.UserUpdateRequest;
import dev.otthon.userhub.domain.mapper.UserMapper;
import dev.otthon.userhub.domain.model.SubscriptionType;
import dev.otthon.userhub.domain.model.User;
import dev.otthon.userhub.domain.model.UserType;
import dev.otthon.userhub.repository.UserRepository;
import dev.otthon.userhub.repository.UserTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.FrameworkServlet;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserTypeRepository userTypeRepository;
    private final UserMapper userMapper;
    private final FrameworkServlet frameworkServlet;

    @Override
    @Transactional
    public UserDTO create(UserRequest request) {
        User fromUserRequest = userMapper.fromRequestToEntity(request);
        UserType fromUserTyeRequest = findUserTypeById(request.getUserType());

        checkIfCpfExists(fromUserRequest.getCpf());
        checkIfEmailExists(fromUserRequest.getEmail());

        fromUserRequest.setUserType(fromUserTyeRequest);
        return userMapper.fromEntityToResponse(userRepository.save(fromUserRequest));

    }

    @Override
    public void deleteById(Long id) {
        checkIdExists(id);
        try {
            userRepository.deleteById(id);
        } catch (DataIntegrityViolationException cause) {
            throw new ConstraintViolationException(
                    String.format("User with id %d cannot be deleted due to data integrity constraints. Type: %s", id, SubscriptionType.class.getSimpleName()), cause
            );
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> listAll() {
        List<User> fromEntity = userRepository.findAll();
        List<UserDTO> resultList = new ArrayList<>();
        for (User user : fromEntity) {
            resultList.add(userMapper.fromEntityToResponse(user));
        }
        return resultList;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO getById(Long id) {
        User response = findUserById(id);
        return userMapper.fromEntityToResponse(response);
    }

    @Override
    @Transactional
    public UserDTO update(Long id, UserUpdateRequest request) {

        User entity = findUserById(id);
        userMapper.fromRequestToUpdate(request, entity);

        if (request.getUserType() != null) {
            UserType userType = findUserTypeById(request.getUserType());
            entity.setUserType(userType);
        }
        return userMapper.fromEntityToResponse(userRepository.save(entity));

    }

    @Override
    @Transactional
    public UserDTO updatePatch(Long id, Map<String, Object> request) {

        User entity = findUserById(id);
        applyUpdate(request, entity);

        return userMapper.fromEntityToResponse(userRepository.save(entity));
    }

    private void applyUpdate(Map<String, Object> sourceField, User targetEntity) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Object userTypeValue = sourceField.remove("user_type");

        User partialUpdate = objectMapper.convertValue(sourceField, User.class);

        ReflectionUtils.doWithFields(User.class, field -> {
            if (!java.lang.reflect.Modifier.isStatic(field.getModifiers())) {
                field.setAccessible(true);
                Object value = ReflectionUtils.getField(field, partialUpdate);
                if (value != null) {
                    ReflectionUtils.setField(field, targetEntity, value);
                }
            }
        });

        if (userTypeValue != null) {
            Long userTypeId = ((Number) userTypeValue).longValue();
            UserType userType = findUserTypeById(userTypeId);
            targetEntity.setUserType(userType);
        }
    }

    private UserType findUserTypeById(Long id) {
        return userTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User Type with id %d not found. Type: %s".formatted(id, UserType.class.getSimpleName())
                ));
    }

    private User findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "User with id %d not found. Type: %s".formatted(id, User.class.getSimpleName())
                ));
    }

    private void checkIdExists(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    "User with id %d not found. Type: %s".formatted(id, User.class.getSimpleName())
            );
        }
    }

    private void checkIfCpfExists(String cpf) {
        if (userRepository.existsByCpf(cpf)) {
            throw new ConstraintViolationException(
                    "User with CPF %s already exists. Type: %s".formatted(cpf, UserDTO.class.getSimpleName())
            );
        }
    }

    private void checkIfEmailExists(String email) {
        if (userRepository.existsByEmail(email)) {
            throw new ConstraintViolationException(
                    "User with email %s already exists. Type: %s".formatted(email, UserDTO.class.getSimpleName())
            );
        }
    }

}
