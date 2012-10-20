package com.unotournamentboard.model;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

/**
 * @author hgastaud
 * 
 */
@RelationshipEntity
public class PlayedRelationship {

	@GraphId
	private Long id;
	
    @StartNode
    private Player player;

    @EndNode
    private Round round;

    private int points;

    private Boolean theWinner;

    public PlayedRelationship() {
        // Do nothing.
    }

    public PlayedRelationship(Player player, Round round, int points, boolean won) {
        this.setPlayer(player);
        this.setRound(round);
        this.setPoints(points);
        this.setTheWinner(won);
    }

    public PlayedRelationship(Player player, Round round, int points) {
        this(player, round, points, false);
    }

    public PlayedRelationship(Player player, Round round) {
        this(player, round, 0, true);
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

    /**
     * @return the theWinner
     */
    public boolean isTheWinner() {
        return theWinner;
    }

    /**
     * @param theWinner
     *            the theWinner to set
     */
    public void setTheWinner(boolean theWinner) {
        this.theWinner = theWinner;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getTheWinner() {
		return theWinner;
	}

	public void setTheWinner(Boolean theWinner) {
		this.theWinner = theWinner;
	}

}
