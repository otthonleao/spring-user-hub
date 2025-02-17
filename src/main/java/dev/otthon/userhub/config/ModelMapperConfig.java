package dev.otthon.userhub.config;

import dev.otthon.userhub.domain.dto.request.UserRequest;
import dev.otthon.userhub.domain.model.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {

        ModelMapper modelMapper = new ModelMapper();

        modelMapper.addMappings(new PropertyMap<UserRequest, User>() {
            @Override
            protected void configure() {
                map(source.getUserType(), destination.getUserType());
            }
        });

        return modelMapper;
    }
}
