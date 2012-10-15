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
    private long id;

    @RelatedToVia(type = "PLAYED", direction = Direction.INCOMING)
    private Iterator<PlayedRelationship> playersPlayed;

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
     * @return the playersPlayed
     */
    public Iterator<PlayedRelationship> getPlayersPlayed() {
        return playersPlayed;
    }

    /**
     * @param playersPlayed
     *            the playersPlayed to set
     */
    public void setPlayersPlayed(Iterator<PlayedRelationship> playersPlayed) {
        this.playersPlayed = playersPlayed;
    }

}
