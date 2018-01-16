package com.twitter.mini.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Friend entity.
 */
public class FriendDTO implements Serializable {

    private Long id;

    private Long personId;

    private Long followerId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Long getFollowerId() {
        return followerId;
    }

    public void setFollowerId(Long personId) {
        this.followerId = personId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FriendDTO friendDTO = (FriendDTO) o;
        if(friendDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), friendDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FriendDTO{" +
            "id=" + getId() +
            "}";
    }
}
