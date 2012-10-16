package com.unotournamentboard.model;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

/**
 * @author hgastaud
 * 
 */
@RelationshipEntity
public class PlayedRelationship {

	@StartNode
	private Player player;

	@EndNode
	private Round round;

	private int points;

	public PlayedRelationship() {
		// Do nothing.
	}

	public PlayedRelationship(Player player, Round round, int points) {
		this.setPlayer(player);
		this.setRound(round);
		this.setPoints(points);
	}

	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * @param player
	 *            the player to set
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * @return the round
	 */
	public Round getRound() {
		return round;
	}

	/**
	 * @param round
	 *            the round to set
	 */
	public void setRound(Round round) {
		this.round = round;
	}

	/**
	 * @return the points
	 */
	public int getPoints() {
		return points;
	}

	/**
	 * @param points
	 *            the points to set
	 */
	public void setPoints(int points) {
		this.points = points;
	}

}
