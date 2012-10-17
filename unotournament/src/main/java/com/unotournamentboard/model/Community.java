package com.unotournamentboard.model;

import java.util.Date;
import java.util.HashSet;
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
    private Long id;

    private String name;

    private Date creationDate;

    @RelatedTo(type = "MEMBER_OF", direction = Direction.INCOMING)
    private Set<Player> members;

    @RelatedTo(type = "HAVES", direction = Direction.OUTGOING)
    private Set<Tournament> tournaments;

    public Community() {
        // Do nothing.
    }

    public Community(String name) {
        this.setName(name);
        this.setMembers(new HashSet<Player>());
        this.setTournaments(new HashSet<Tournament>());
        this.setCreationDate(new Date());
    }

    public Member createNewMemberShip(Player player) {
        this.getMembers().add(player);
        return new Member(player, this);
    }

    public void addNewTournament(Tournament tournament) {
        this.getTournaments().add(tournament);
    }

    public void removeTournament(Tournament tournament) {
        this.getTournaments().remove(tournament);
    }

    public Set<Tournament> getTournaments() {
        return tournaments;
    }

    public void setTournaments(Set<Tournament> tournaments) {
        this.tournaments = tournaments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Community communityToCompare = (Community) o;

        if (this.getId() != null ? !this.getId().equals(communityToCompare.getId()) : communityToCompare.getId() != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = this.getId() != null ? this.getId().hashCode() : 0;
        result = 31 * result + (this.getName() != null ? this.getName().hashCode() : 0);
        return result;
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
