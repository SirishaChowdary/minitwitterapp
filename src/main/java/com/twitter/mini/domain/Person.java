package com.twitter.mini.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Person.
 */
@Entity
@Table(name = "person")
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "person")
    @JsonIgnore
    private Set<Message> messages = new HashSet<>();

    @OneToMany(mappedBy = "person")
    @JsonIgnore
    private Set<Friend> friends = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public Person firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Person lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserId() {
        return userId;
    }

    public Person userId(String userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public Person email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public Person messages(Set<Message> messages) {
        this.messages = messages;
        return this;
    }

    public Person addMessage(Message message) {
        this.messages.add(message);
        message.setPerson(this);
        return this;
    }

    public Person removeMessage(Message message) {
        this.messages.remove(message);
        message.setPerson(null);
        return this;
    }

    public void setMessages(Set<Message> messages) {
        this.messages = messages;
    }

    public Set<Friend> getFriends() {
        return friends;
    }

    public Person friends(Set<Friend> friends) {
        this.friends = friends;
        return this;
    }

    public Person addFriend(Friend friend) {
        this.friends.add(friend);
        friend.setPerson(this);
        return this;
    }

    public Person removeFriend(Friend friend) {
        this.friends.remove(friend);
        friend.setPerson(null);
        return this;
    }

    public void setFriends(Set<Friend> friends) {
        this.friends = friends;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Person person = (Person) o;
        if (person.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), person.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Person{" +
            "id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", userId='" + getUserId() + "'" +
            ", email='" + getEmail() + "'" +
            "}";
    }
}
