package dev.otthon.userhub.application.service;

import dev.otthon.userhub.domain.dto.SubscriptionTypeDTO;
import dev.otthon.userhub.domain.dto.request.CreateSubscriptionTypeRequest;

import java.util.List;

public interface SubscriptionTypeService {

    SubscriptionTypeDTO create(final CreateSubscriptionTypeRequest request);

    List<SubscriptionTypeDTO> listAll();

    SubscriptionTypeDTO getById(Long id);

    void deleteById(Long id);

    SubscriptionTypeDTO update(Long id, CreateSubscriptionTypeRequest request);
}
