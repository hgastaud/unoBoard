package com.unotournamentboard.model;

import java.util.Date;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

/**
 * @author hgastaud
 * 
 */
@NodeEntity
public class Tournament {

    @GraphId
    private long id;

    private String name;

    private Date startDate;

    private Date endDate;
    
    private int maxOfPoints = 0;
    
    private int maxOfHooks = 0;
    
    @RelatedTo(type = "PLAYED_IN", direction = Direction.INCOMING)
    private Set<Player> players;
    
    @RelatedTo(type = "ROUND_OF", direction = Direction.OUTGOING)
    private Set<Round> rounds;
    
    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate
     *            the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate
     *            the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the players
     */
    public Set<Player> getPlayers() {
        return players;
    }

    /**
     * @param players
     *            the players to set
     */
    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    /**
     * @return the rounds
     */
    public Set<Round> getRounds() {
        return rounds;
    }

    /**
     * @param rounds the rounds to set
     */
    public void setRounds(Set<Round> rounds) {
        this.rounds = rounds;
    }

    /**
     * @return the maxOfPoints
     */
    public int getMaxOfPoints() {
        return maxOfPoints;
    }

    /**
     * @param maxOfPoints the maxOfPoints to set
     */
    public void setMaxOfPoints(int maxOfPoints) {
        this.maxOfPoints = maxOfPoints;
    }

    /**
     * @return the maxOfHooks
     */
    public int getMaxOfHooks() {
        return maxOfHooks;
    }

    /**
     * @param maxOfHooks the maxOfHooks to set
     */
    public void setMaxOfHooks(int maxOfHooks) {
        this.maxOfHooks = maxOfHooks;
    }

}
