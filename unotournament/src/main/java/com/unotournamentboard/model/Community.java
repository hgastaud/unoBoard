package com.unotournamentboard.model;

import java.util.Date;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

/**
 * Represents a Community of {@link Player}s.
 * 
 * @author hgastaud
 * 
 */
@NodeEntity
public class Community {

    @GraphId
    private long id;

    private String name;

    private Date creationDate;

    @RelatedTo(type = "MEMBER_OF", direction = Direction.INCOMING)
    private Set<Player> members;

    @RelatedTo(type = "HAVES", direction = Direction.OUTGOING)
    private Set<Tournament> tournaments;

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
     * @return the creationDate
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate
     *            the creationDate to set
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return the members
     */
    public Set<Player> getMembers() {
        return members;
    }

    /**
     * @param members
     *            the members to set
     */
    public void setMembers(Set<Player> members) {
        this.members = members;
    }

}
