package com.unotournamentboard.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.util.CollectionUtils;

/**
 * @author hgastaud
 * 
 */
@NodeEntity
public class Tournament {

	@GraphId
	private Long id;

	private String name;

	private Date startDate;

	private Date endDate;

	private Integer maxOfPoints = 0;

	private Integer maxOfHooks = 0;

	@RelatedTo(type = "PLAYED_IN", direction = Direction.INCOMING)
	private Set<Player> players;

	@RelatedTo(type = "ROUND_OF", direction = Direction.OUTGOING)
	private Set<Round> rounds;

	private transient List<PlayerInTournament> tournamentPlayers;

	public Tournament() {
		// Do nothing.
	}

	public Tournament(String name, int maxPoints, int maxHooks, Date start, Date end) {
		this.setName(name);
		this.setMaxOfPoints(maxOfPoints);
		this.setMaxOfHooks(maxOfHooks);
		this.setStartDate(start);
		this.setEndDate(end);
		this.setPlayers(new HashSet<Player>());
		this.setRounds(new HashSet<Round>());
		this.setTournamentPlayers(new ArrayList<PlayerInTournament>());
	}

	public void startTournament() {
		for (Player eachPlayer : this.getPlayers()) {
			this.getTournamentPlayers().add(new PlayerInTournament(eachPlayer));
		}
	}
	
	public void addANewRound(Round round){
		this.getRounds().add(round);
		Iterator<PlayedRelationship> playersPlayed = round.getPlayersPlayed();
		while(playersPlayed.hasNext()){
			PlayedRelationship eachPlayerPlayed = playersPlayed.next();
			// TODO
		}
	}

	public Tournament(String name, int maxPoints, int maxHooks, Date start) {
		this(name, maxPoints, maxHooks, start, null);
	}

	public Tournament(String name, int maxPoints, int maxHooks) {
		this(name, maxPoints, maxHooks, new Date(), null);
	}

	public Tournament(String name, int maxPoints) {
		this(name, maxPoints, 0, new Date(), null);
	}

	public void addPlayer(Player player) {
		this.getPlayers().add(player);
	}

	public void removePlayer(Player player) {
		this.getPlayers().remove(player);
	}

	public int getRoundsPLayed() {
		return this.getRounds().size();
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
	 * @param rounds
	 *            the rounds to set
	 */
	public void setRounds(Set<Round> rounds) {
		this.rounds = rounds;
	}

	/**
	 * @return the maxOfPoints
	 */
	public Integer getMaxOfPoints() {
		return maxOfPoints;
	}

	/**
	 * @param maxOfPoints
	 *            the maxOfPoints to set
	 */
	public void setMaxOfPoints(Integer maxOfPoints) {
		this.maxOfPoints = maxOfPoints;
	}

	/**
	 * @return the maxOfHooks
	 */
	public Integer getMaxOfHooks() {
		return maxOfHooks;
	}

	/**
	 * @param maxOfHooks
	 *            the maxOfHooks to set
	 */
	public void setMaxOfHooks(Integer maxOfHooks) {
		this.maxOfHooks = maxOfHooks;
	}

	protected List<PlayerInTournament> getTournamentPlayers() {
		return tournamentPlayers;
	}

	protected void setTournamentPlayers(List<PlayerInTournament> tournamentPlayers) {
		this.tournamentPlayers = tournamentPlayers;
	}

}
