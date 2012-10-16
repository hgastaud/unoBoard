/**
 * 
 */
package com.unotournamentboard.model;

/**
 * @author Hernan
 * 
 */
public class PlayerInTournament {

	private Player player;

	private Integer points;

	private Integer numberOfHooks;

	public PlayerInTournament(Player player) {
		this.setPlayer(player);
		this.setPoints(0);
		this.setNumberOfHooks(0);
	}

	public void addPoints(Integer points) {
		this.setPoints(this.getPoints() + points);
	}

	public void addHook(Integer newPoints) {
		this.setNumberOfHooks(this.getNumberOfHooks() + 1);
		this.setPoints(newPoints);
	}

	public Integer getNumberOfHooks() {
		return numberOfHooks;
	}

	protected void setNumberOfHooks(Integer numberOfHooks) {
		this.numberOfHooks = numberOfHooks;
	}

	protected Player getPlayer() {
		return player;
	}

	protected void setPlayer(Player player) {
		this.player = player;
	}

	public Integer getPoints() {
		return points;
	}

	protected void setPoints(Integer points) {
		this.points = points;
	}

}
