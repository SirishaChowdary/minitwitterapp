package com.twitter.mini.service;

import com.twitter.mini.service.dto.FriendDTO;
import java.util.List;

/**
 * Service Interface for managing Friend.
 */
public interface FriendService {

    /**
     * Save a friend.
     *
     * @param friendDTO the entity to save
     * @return the persisted entity
     */
    FriendDTO save(FriendDTO friendDTO);

    /**
     *  Get all the friends.
     *
     *  @return the list of entities
     */
    List<FriendDTO> findAll();

    /**
     *  Get the "id" friend.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    FriendDTO findOne(Long id);

    /**
     *  Delete the "id" friend.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
