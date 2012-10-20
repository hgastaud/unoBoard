package com.unotournamentboard.model;

import java.util.HashSet;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.annotation.RelatedToVia;

/**
 * Represents a single player of an UNO Tournament.
 * 
 * @author Hernan
 * 
 */
@NodeEntity
public class Player {

    @GraphId
    private Long id;

    private String name;

    private String lastName;

    private String username;

    private String password;

    private String country;

    private String email;

    private Boolean active;

    private Integer wrongLogingAttemps;

    @RelatedTo(type = "FRIEND", direction = Direction.OUTGOING)
    private Set<Player> friends;

    @RelatedToVia(type = "MEMBER_OF", direction = Direction.OUTGOING)
    private Set<Member> memberships;

    public Player() {
        // Do nothing.
    }

    public Player(String name, String lastName, String username, String password, String country, String email) {
        this.setName(name);
        this.setLastName(lastName);
        this.setUsername(username);
        this.setPassword(password);
        this.setCountry(country);
        this.setEmail(email);
        this.setActive(false);
        this.setWrongLogingAttemps(0);
        this.setFriends(new HashSet<Player>());
        this.setMemberships(new HashSet<Member>());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Player playerToCompare = (Player) o;

        if (this.getId() != null ? !this.getId().equals(playerToCompare.getId()) : playerToCompare.getId() != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = this.getId() != null ? this.getId().hashCode() : 0;
        result = 31 * result + (this.getUsername() != null ? this.getUsername().hashCode() : 0);
        return result;
    }

    public void blockAccount() {
        this.setActive(false);
    }

    public void addNewFailLoginAttemp() {
        this.setWrongLogingAttemps(this.getWrongLogingAttemps() + 1);
        if (this.getWrongLogingAttemps() > 3) {
            this.blockAccount();
        }
    }

    public void activeAccount() {
        this.setActive(true);
        this.setWrongLogingAttemps(0);

    }

    public Friendship addANewFriend(Player player) {
        Friendship friendship = new Friendship(this, player);
        this.getFriends().add(player);
        return friendship;
    }

    public Set<Member> getMemberships() {
        return memberships;
    }

    public void setMemberships(Set<Member> memberships) {
        this.memberships = memberships;
    }

    public Set<Player> getFriends() {
        return friends;
    }

    public void setFriends(Set<Player> friends) {
        this.friends = friends;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     *            the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the active
     */
    public Boolean getActive() {
        return active;
    }

    /**
     * @param active
     *            the active to set
     */
    public void setActive(Boolean active) {
        this.active = active;
    }

    /**
     * @return the wrongLogingAttemps
     */
    public Integer getWrongLogingAttemps() {
        return wrongLogingAttemps;
    }

    /**
     * @param wrongLogingAttemps
     *            the wrongLogingAttemps to set
     */
    public void setWrongLogingAttemps(Integer wrongLogingAttemps) {
        this.wrongLogingAttemps = wrongLogingAttemps;
    }

}
