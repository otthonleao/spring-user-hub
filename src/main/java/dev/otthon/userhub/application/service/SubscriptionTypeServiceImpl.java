package dev.otthon.userhub.application.service;

import dev.otthon.userhub.core.exception.ConstraintViolationException;
import dev.otthon.userhub.core.exception.ResourceNotFoundException;
import dev.otthon.userhub.domain.dto.SubscriptionTypeDTO;
import dev.otthon.userhub.domain.dto.request.CreateSubscriptionTypeRequest;
import dev.otthon.userhub.domain.mapper.SubscriptionTypeMapper;
import dev.otthon.userhub.domain.model.SubscriptionType;
import dev.otthon.userhub.repository.SubscriptionTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Override
    @Transactional(readOnly = true)
    public List<SubscriptionTypeDTO> listAll() {
        List<SubscriptionType> response = subscriptionTypeRepository.findAll();
        return response.stream()
                .map(SubscriptionTypeMapper::fromEntityToResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public SubscriptionTypeDTO getById(Long id) {
        SubscriptionType response = subscriptionTypeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Subscription Type with id %d not found", id)
                ));
        return SubscriptionTypeMapper.fromEntityToResponse(response);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!subscriptionTypeRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    String.format("Subscription Type with id %d not found", id)
            );
        }

        try {
            subscriptionTypeRepository.deleteById(id);
        } catch (DataIntegrityViolationException cause) {
            throw new ConstraintViolationException(
                    String.format("Subscription Type with id %d cannot be deleted due to data integrity constraints", id), cause
            );
        }
    }

    @Override
    @Transactional
    public SubscriptionTypeDTO update(Long id, CreateSubscriptionTypeRequest request) {
        if (!subscriptionTypeRepository.existsById(id)) {
            throw new ResourceNotFoundException(
                    String.format("Subscription Type with id %d not found", id)
            );
        }

        SubscriptionType fromRequest = SubscriptionTypeMapper.fromRequestToEntity(request);

        SubscriptionType toSave = subscriptionTypeRepository.getReferenceById(id);
        toSave.setName(fromRequest.getName());
        toSave.setAccessMonth(fromRequest.getAccessMonth());
        toSave.setPrice(fromRequest.getPrice());
        toSave.setProductKey(fromRequest.getProductKey());

        SubscriptionType updated = subscriptionTypeRepository.save(toSave);

        return SubscriptionTypeMapper.fromEntityToResponse(updated);

    }

}
