package com.unotournamentboard.model;

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
	private long id;

	private String name;

	private String lastName;

	private String username;

	private String password;

	private String country;

	@RelatedTo(type = "FRIEND", direction = Direction.OUTGOING)
	private Set<Player> friends;

	@RelatedToVia(type = "MEMBER_OF", direction = Direction.OUTGOING)
	private Iterable<Member> memberships;

	public Player() {
		// Do nothing.
	}

	public Player(String name, String lastName, String username, String password, String country) {
		this.setName(name);
		this.setLastName(lastName);
		this.setUsername(username);
		this.setPassword(password);
		this.setCountry(country);
	}

	public Friendship addANewFriend(Player player) {
		Friendship friendship = new Friendship(this, player);
		this.getFriends().add(player);
		return friendship;
	}
	
	public Iterable<Member> getMemberships() {
		return memberships;
	}

	public void setMemberships(Iterable<Member> memberships) {
		this.memberships = memberships;
	}

	public Set<Player> getFriends() {
		return friends;
	}

	public void setFriends(Set<Player> friends) {
		this.friends = friends;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

}
