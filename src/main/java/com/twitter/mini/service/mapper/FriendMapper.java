package com.twitter.mini.service.mapper;

import com.twitter.mini.domain.*;
import com.twitter.mini.service.dto.FriendDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Friend and its DTO FriendDTO.
 */
@Mapper(componentModel = "spring", uses = {PersonMapper.class})
public interface FriendMapper extends EntityMapper<FriendDTO, Friend> {

    @Mapping(source = "person.id", target = "personId")
    @Mapping(source = "follower.id", target = "followerId")
    FriendDTO toDto(Friend friend); 

    @Mapping(source = "personId", target = "person")
    @Mapping(source = "followerId", target = "follower")
    Friend toEntity(FriendDTO friendDTO);

    default Friend fromId(Long id) {
        if (id == null) {
            return null;
        }
        Friend friend = new Friend();
        friend.setId(id);
        return friend;
    }
}
