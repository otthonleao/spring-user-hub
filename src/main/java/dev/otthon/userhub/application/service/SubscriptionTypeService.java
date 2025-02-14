package dev.otthon.userhub.application.service;

import dev.otthon.userhub.domain.dto.SubscriptionTypeDTO;
import dev.otthon.userhub.domain.dto.request.CreateSubscriptionTypeRequest;

public interface SubscriptionTypeService {

    SubscriptionTypeDTO create(final CreateSubscriptionTypeRequest request);

}
