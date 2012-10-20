package com.unotournamentboard.model;

import java.util.Date;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

/**
 * @author hgastaud
 * 
 */
@RelationshipEntity(type = "MEMBER_OF")
public class Member {

	@GraphId
	private Long id;
	
	@StartNode
    private Player player;

    @EndNode
    private Community community;

    private Date from;

    public Member() {
		// Do nothing.
	}

    public Member(Player player, Community community) {
    	this.setPlayer(player);
    	this.setCommunity(community);
    	this.setFrom(new Date());
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
     * @return the community
     */
    public Community getCommunity() {
        return community;
    }

    /**
     * @param community
     *            the community to set
     */
    public void setCommunity(Community community) {
        this.community = community;
    }

    /**
     * @return the from
     */
    public Date getFrom() {
        return from;
    }

    /**
     * @param from
     *            the from to set
     */
    public void setFrom(Date from) {
        this.from = from;
    }
    
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
