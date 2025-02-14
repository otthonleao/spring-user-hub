package dev.otthon.userhub.domain.mapper;

import dev.otthon.userhub.domain.dto.SubscriptionTypeDTO;
import dev.otthon.userhub.domain.dto.request.CreateSubscriptionTypeRequest;
import dev.otthon.userhub.domain.model.SubscriptionType;

public interface SubscriptionTypeMapper {

    SubscriptionType fromRequestToEntity(final CreateSubscriptionTypeRequest request);
    SubscriptionTypeDTO fromEntityToResponse(final SubscriptionType entity);

}
