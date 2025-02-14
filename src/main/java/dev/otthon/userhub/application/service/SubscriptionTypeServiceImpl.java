package dev.otthon.userhub.application.service;

import dev.otthon.userhub.domain.dto.SubscriptionTypeDTO;
import dev.otthon.userhub.domain.dto.request.CreateSubscriptionTypeRequest;
import dev.otthon.userhub.domain.mapper.SubscriptionTypeMapper;
import dev.otthon.userhub.repository.SubscriptionTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SubscriptionTypeServiceImpl implements SubscriptionTypeService {

    private final SubscriptionTypeRepository subscriptionTypeRepository;
    private final SubscriptionTypeMapper SubscriptionTypeMapper;

    @Override
    @Transactional
    public SubscriptionTypeDTO create(CreateSubscriptionTypeRequest request) {
        var toEntity = SubscriptionTypeMapper.fromRequestToEntity(request);
        var saved = subscriptionTypeRepository.save(toEntity);
        return SubscriptionTypeMapper.fromEntityToResponse(saved);
    }

}
