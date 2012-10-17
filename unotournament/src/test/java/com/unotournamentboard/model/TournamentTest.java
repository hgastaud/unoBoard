package com.unotournamentboard.model;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

/**
 * @author hgastaud
 * 
 */
public class TournamentTest {

    private Tournament tournament;

    @Before
    public void setUp() {
        this.setTournament(new Tournament("T1", 100));
        for (int i = 1; i < 7; i++) {
            this.getTournament().addPlayer(createAPlayer(Long.valueOf(i)));
        }
    }

    @Test
    public void startTournament() {
        Assert.assertEquals(6, this.getTournament().getPlayers().size());
        Assert.assertEquals(0, this.getTournament().getTournamentPlayers().size());
        this.getTournament().startTournament();
        Assert.assertEquals(6, this.getTournament().getTournamentPlayers().size());
    }

    @Test
    public void addTheFinalPointsForAllPlayers() {
        this.getTournament().startTournament();
        Round round = new Round();
        List<PlayedRelationship> plays = new ArrayList<PlayedRelationship>();
        ArrayList<PlayerInTournament> players = new ArrayList<PlayerInTournament>(this.getTournament().getTournamentPlayers());
        plays.add(round.endRoundFor(players.get(0).getPlayer(), Integer.valueOf(300)));
        plays.add(round.endRoundFor(players.get(1).getPlayer(), Integer.valueOf(250)));
        plays.add(round.endRoundFor(players.get(2).getPlayer(), Integer.valueOf(440)));
        plays.add(round.endRoundFor(players.get(3).getPlayer(), Integer.valueOf(0)));
        plays.add(round.endRoundFor(players.get(4).getPlayer(), Integer.valueOf(1)));
        plays.add(round.wonTheRound(players.get(5).getPlayer()));
        round.setPlayersPlayed(plays.iterator());

        this.getTournament().addTheFinalPointsForAllPlayers(round);
        Assert.assertEquals(Integer.valueOf(300), players.get(0).getPoints());
        Assert.assertEquals(Integer.valueOf(250), players.get(1).getPoints());
        Assert.assertEquals(Integer.valueOf(440), players.get(2).getPoints());
        Assert.assertEquals(Integer.valueOf(0), players.get(3).getPoints());
        Assert.assertEquals(Integer.valueOf(1), players.get(4).getPoints());
        Assert.assertEquals(Integer.valueOf(0), players.get(5).getPoints());

        round.setPlayersPlayed(plays.iterator());
        this.getTournament().addTheFinalPointsForAllPlayers(round);
        Assert.assertEquals(Integer.valueOf(600), players.get(0).getPoints());
        Assert.assertEquals(Integer.valueOf(500), players.get(1).getPoints());
        Assert.assertEquals(Integer.valueOf(880), players.get(2).getPoints());
        Assert.assertEquals(Integer.valueOf(0), players.get(3).getPoints());
        Assert.assertEquals(Integer.valueOf(2), players.get(4).getPoints());
        Assert.assertEquals(Integer.valueOf(0), players.get(5).getPoints());

    }

    @Test
    public void sortTheTournamentPlayer() {
        this.getTournament().startTournament();
        this.getTournament().getTournamentPlayers().get(0).setPoints(500);
        this.getTournament().getTournamentPlayers().get(1).setPoints(250);
        this.getTournament().getTournamentPlayers().get(2).setPoints(600);
        this.getTournament().getTournamentPlayers().get(2).setFinishRound(4);
        this.getTournament().getTournamentPlayers().get(3).setPoints(54);
        this.getTournament().getTournamentPlayers().get(4).setPoints(154);
        this.getTournament().getTournamentPlayers().get(5).setPoints(877);
        this.getTournament().getTournamentPlayers().get(5).setFinishRound(5);
        this.getTournament().sortTheTournamentPlayer();
        
        Assert.assertEquals(Integer.valueOf(54), this.getTournament().getTournamentPlayers().get(0).getPoints());
        Assert.assertEquals(Integer.valueOf(154), this.getTournament().getTournamentPlayers().get(1).getPoints());
        Assert.assertEquals(Integer.valueOf(250), this.getTournament().getTournamentPlayers().get(2).getPoints());
        Assert.assertEquals(Integer.valueOf(500), this.getTournament().getTournamentPlayers().get(3).getPoints());
        Assert.assertEquals(Integer.valueOf(877), this.getTournament().getTournamentPlayers().get(4).getPoints());
        Assert.assertEquals(Integer.valueOf(600), this.getTournament().getTournamentPlayers().get(5).getPoints());
    }

    /**
     * @param id
     * @return
     */
    protected Player createAPlayer(Long id) {
        Player player = new Player();
        player.setId(id);
        return player;
    }

    /**
     * @return the tournament
     */
    protected Tournament getTournament() {
        return tournament;
    }

    /**
     * @param tournament
     *            the tournament to set
     */
    protected void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

}
