package com.twitter.mini.service.mapper;

import com.twitter.mini.domain.*;
import com.twitter.mini.service.dto.MessageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Message and its DTO MessageDTO.
 */
@Mapper(componentModel = "spring", uses = {PersonMapper.class})
public interface MessageMapper extends EntityMapper<MessageDTO, Message> {

    @Mapping(source = "person.id", target = "personId")
    MessageDTO toDto(Message message); 

    @Mapping(source = "personId", target = "person")
    Message toEntity(MessageDTO messageDTO);

    default Message fromId(Long id) {
        if (id == null) {
            return null;
        }
        Message message = new Message();
        message.setId(id);
        return message;
    }
}
