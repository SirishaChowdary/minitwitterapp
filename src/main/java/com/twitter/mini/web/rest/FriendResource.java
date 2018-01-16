package com.twitter.mini.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.twitter.mini.service.FriendService;
import com.twitter.mini.web.rest.errors.BadRequestAlertException;
import com.twitter.mini.web.rest.util.HeaderUtil;
import com.twitter.mini.service.dto.FriendDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Friend.
 */
@RestController
@RequestMapping("/api")
public class FriendResource {

    private final Logger log = LoggerFactory.getLogger(FriendResource.class);

    private static final String ENTITY_NAME = "friend";

    private final FriendService friendService;

    public FriendResource(FriendService friendService) {
        this.friendService = friendService;
    }

    /**
     * POST  /friends : Create a new friend.
     *
     * @param friendDTO the friendDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new friendDTO, or with status 400 (Bad Request) if the friend has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/friends")
    @Timed
    public ResponseEntity<FriendDTO> createFriend(@RequestBody FriendDTO friendDTO) throws URISyntaxException {
        log.debug("REST request to save Friend : {}", friendDTO);
        if (friendDTO.getId() != null) {
            throw new BadRequestAlertException("A new friend cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FriendDTO result = friendService.save(friendDTO);
        return ResponseEntity.created(new URI("/api/friends/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /friends : Updates an existing friend.
     *
     * @param friendDTO the friendDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated friendDTO,
     * or with status 400 (Bad Request) if the friendDTO is not valid,
     * or with status 500 (Internal Server Error) if the friendDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/friends")
    @Timed
    public ResponseEntity<FriendDTO> updateFriend(@RequestBody FriendDTO friendDTO) throws URISyntaxException {
        log.debug("REST request to update Friend : {}", friendDTO);
        if (friendDTO.getId() == null) {
            return createFriend(friendDTO);
        }
        FriendDTO result = friendService.save(friendDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, friendDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /friends : get all the friends.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of friends in body
     */
    @GetMapping("/friends")
    @Timed
    public List<FriendDTO> getAllFriends() {
        log.debug("REST request to get all Friends");
        return friendService.findAll();
        }

    /**
     * GET  /friends/:id : get the "id" friend.
     *
     * @param id the id of the friendDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the friendDTO, or with status 404 (Not Found)
     */
    @GetMapping("/friends/{id}")
    @Timed
    public ResponseEntity<FriendDTO> getFriend(@PathVariable Long id) {
        log.debug("REST request to get Friend : {}", id);
        FriendDTO friendDTO = friendService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(friendDTO));
    }

    /**
     * DELETE  /friends/:id : delete the "id" friend.
     *
     * @param id the id of the friendDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/friends/{id}")
    @Timed
    public ResponseEntity<Void> deleteFriend(@PathVariable Long id) {
        log.debug("REST request to delete Friend : {}", id);
        friendService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
