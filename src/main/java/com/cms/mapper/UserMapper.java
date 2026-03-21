package com.cms.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Service;

import com.cms.DTO.UserDTO;
import com.cms.entity.User;

@Service
public interface UserMapper {

    UserDTO toDTO(User user);

    User toEntity(UserDTO dto);
}