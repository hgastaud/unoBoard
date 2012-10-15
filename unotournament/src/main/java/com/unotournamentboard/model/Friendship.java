package com.unotournamentboard.model;
import java.util.Date;

import org.springframework.data.neo4j.annotation.EndNode;
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

	@StartNode
	private Player startNode;

	@EndNode
	private Player endNode;

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

}
