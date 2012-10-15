package com.unotournamentboard.model;

import java.util.Date;

import org.springframework.data.neo4j.annotation.EndNode;
import org.springframework.data.neo4j.annotation.RelationshipEntity;
import org.springframework.data.neo4j.annotation.StartNode;

/**
 * @author hgastaud
 * 
 */
@RelationshipEntity(type = "MEMBER_OF")
public class Member {

    @StartNode
    private Player player;

    @EndNode
    private Community community;

    private Date from;

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

}
