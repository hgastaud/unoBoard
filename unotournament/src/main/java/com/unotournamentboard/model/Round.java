package com.unotournamentboard.model;

import java.util.Iterator;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedToVia;

/**
 * @author hgastaud
 * 
 */
@NodeEntity
public class Round {

    @GraphId
    private Long id;

    @RelatedToVia(type = "PLAYED", direction = Direction.INCOMING)
    private Iterable<PlayedRelationship> playersPlayed;

    public Round() {
        // Do nothing.
    }

    public PlayedRelationship endRoundFor(Player player, Integer finalPoints) {
        return new PlayedRelationship(player, this, finalPoints);
    }

    public PlayedRelationship wonTheRound(Player player) {
        return new PlayedRelationship(player, this);
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the playersPlayed
     */
    public Iterable<PlayedRelationship> getPlayersPlayed() {
        return playersPlayed;
    }

    /**
     * @param playersPlayed
     *            the playersPlayed to set
     */
    public void setPlayersPlayed(Iterable<PlayedRelationship> playersPlayed) {
        this.playersPlayed = playersPlayed;
    }

}
