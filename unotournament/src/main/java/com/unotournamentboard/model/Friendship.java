package com.unotournamentboard.model;

import java.util.Date;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

/**
 * This is a Relationship between two {@link Player}s.
 * 
 * @author Hernan
 * 
 */
@RelationshipEntity(type = "FRIEND")
public class Friendship {

	@GraphId
	private Long id;
	
	@StartNode
	private Player startNode;

	@EndNode
	private Player endNode;

	public Friendship() {
		// Do nothing.
	}

	public Friendship(Player friend1, Player friend2) {
		this.setStartNode(friend1);
		this.setEndNode(friend2);
	}

	public Player getStartNode() {
		return startNode;
	}

	public void setStartNode(Player startNode) {
		this.startNode = startNode;
	}

	public Player getEndNode() {
		return endNode;
	}

	public void setEndNode(Player endNode) {
		this.endNode = endNode;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	private Date date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
