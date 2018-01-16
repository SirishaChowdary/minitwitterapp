package com.twitter.mini.service.impl;

import com.twitter.mini.service.FriendService;
import com.twitter.mini.domain.Friend;
import com.twitter.mini.repository.FriendRepository;
import com.twitter.mini.service.dto.FriendDTO;
import com.twitter.mini.service.mapper.FriendMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Friend.
 */
@Service
@Transactional
public class FriendServiceImpl implements FriendService{

    private final Logger log = LoggerFactory.getLogger(FriendServiceImpl.class);

    private final FriendRepository friendRepository;

    private final FriendMapper friendMapper;

    public FriendServiceImpl(FriendRepository friendRepository, FriendMapper friendMapper) {
        this.friendRepository = friendRepository;
        this.friendMapper = friendMapper;
    }

    /**
     * Save a friend.
     *
     * @param friendDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public FriendDTO save(FriendDTO friendDTO) {
        log.debug("Request to save Friend : {}", friendDTO);
        Friend friend = friendMapper.toEntity(friendDTO);
        friend = friendRepository.save(friend);
        return friendMapper.toDto(friend);
    }

    /**
     *  Get all the friends.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<FriendDTO> findAll() {
        log.debug("Request to get all Friends");
        return friendRepository.findAll().stream()
            .map(friendMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get one friend by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public FriendDTO findOne(Long id) {
        log.debug("Request to get Friend : {}", id);
        Friend friend = friendRepository.findOne(id);
        return friendMapper.toDto(friend);
    }

    /**
     *  Delete the  friend by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Friend : {}", id);
        friendRepository.delete(id);
    }
}
