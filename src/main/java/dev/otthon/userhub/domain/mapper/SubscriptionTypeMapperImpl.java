package dev.otthon.userhub.domain.mapper;

import dev.otthon.userhub.domain.dto.SubscriptionTypeDTO;
import dev.otthon.userhub.domain.dto.request.CreateSubscriptionTypeRequest;
import dev.otthon.userhub.domain.dto.request.UpdateSubscriptionTypeRequest;
import dev.otthon.userhub.domain.model.SubscriptionType;
import org.springframework.stereotype.Component;

@Component
public class SubscriptionTypeMapperImpl implements SubscriptionTypeMapper {

    @Override
    public SubscriptionType fromRequestToEntity(CreateSubscriptionTypeRequest request) {
        if (request == null) return null;

        return SubscriptionType.builder()
                .name(request.getName())
                .accessMonth(request.getAccessMonth())
                .price(request.getPrice())
                .productKey(request.getProductKey())
                .build();
    }

    @Override
    public SubscriptionType fromRequestToEntity(UpdateSubscriptionTypeRequest request) {
        return SubscriptionType.builder()
                .name(request.getName())
                .accessMonth(request.getAccessMonth())
                .price(request.getPrice())
                .productKey(request.getProductKey())
                .build();
    }

    @Override
    public SubscriptionTypeDTO fromEntityToResponse(SubscriptionType entity) {
        return SubscriptionTypeDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .accessMonth(entity.getAccessMonth())
                .price(entity.getPrice())
                .productKey(entity.getProductKey())
                .build();
    }
}
