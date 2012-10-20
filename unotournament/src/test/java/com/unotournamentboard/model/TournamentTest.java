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
        this.setTournament(new Tournament("T1", 1000));
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
        round.setPlayersPlayed(plays);

        this.getTournament().addTheFinalPointsForAllPlayers(round);
        Assert.assertEquals(Integer.valueOf(300), players.get(0).getPoints());
        Assert.assertEquals(Integer.valueOf(250), players.get(1).getPoints());
        Assert.assertEquals(Integer.valueOf(440), players.get(2).getPoints());
        Assert.assertEquals(Integer.valueOf(0), players.get(3).getPoints());
        Assert.assertEquals(Integer.valueOf(1), players.get(4).getPoints());
        Assert.assertEquals(Integer.valueOf(0), players.get(5).getPoints());

        round.setPlayersPlayed(plays);
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

    @Test
    public void getTheLastPositionPoints() {
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
        Assert.assertEquals(Integer.valueOf(500), Integer.valueOf(this.getTournament().getTheLastPositionPoints()));

    }

    @Test
    public void removePlayersAndAddHooks() {
        this.getTournament().setMaxOfHooks(3);
        this.getTournament().startTournament();
        this.getTournament().getTournamentPlayers().get(0).setPoints(1000);
        this.getTournament().getTournamentPlayers().get(0).setNumberOfHooks(3);
        this.getTournament().getTournamentPlayers().get(1).setPoints(1500);
        this.getTournament().getTournamentPlayers().get(2).setPoints(1200);
        this.getTournament().getTournamentPlayers().get(2).setFinishRound(4);
        this.getTournament().getTournamentPlayers().get(2).setNumberOfHooks(3);
        this.getTournament().getTournamentPlayers().get(3).setPoints(54);
        this.getTournament().getTournamentPlayers().get(4).setPoints(1000);
        this.getTournament().getTournamentPlayers().get(4).setNumberOfHooks(2);
        this.getTournament().getTournamentPlayers().get(5).setPoints(1001);
        this.getTournament().getTournamentPlayers().get(5).setFinishRound(5);
        this.getTournament().getTournamentPlayers().get(5).setNumberOfHooks(3);
        this.getTournament().sortTheTournamentPlayer();

        this.getTournament().removePlayersAndAddHooks(6, this.getTournament().getTheLastPositionPoints());
        this.getTournament().sortTheTournamentPlayer();

        Assert.assertEquals(54, this.getTournament().getTournamentPlayers().get(0).getPoints().intValue());
        Assert.assertEquals(-1, this.getTournament().getTournamentPlayers().get(0).getFinishRound());
        Assert.assertEquals(0, this.getTournament().getTournamentPlayers().get(0).getNumberOfHooks().intValue());

        Assert.assertEquals(54, this.getTournament().getTournamentPlayers().get(1).getPoints().intValue());
        Assert.assertEquals(-1, this.getTournament().getTournamentPlayers().get(1).getFinishRound());
        Assert.assertEquals(3, this.getTournament().getTournamentPlayers().get(1).getNumberOfHooks().intValue());

        Assert.assertEquals(54, this.getTournament().getTournamentPlayers().get(2).getPoints().intValue());
        Assert.assertEquals(-1, this.getTournament().getTournamentPlayers().get(2).getFinishRound());
        Assert.assertEquals(1, this.getTournament().getTournamentPlayers().get(2).getNumberOfHooks().intValue());

        Assert.assertEquals(1000, this.getTournament().getTournamentPlayers().get(3).getPoints().intValue());
        Assert.assertEquals(6, this.getTournament().getTournamentPlayers().get(3).getFinishRound());
        Assert.assertEquals(3, this.getTournament().getTournamentPlayers().get(3).getNumberOfHooks().intValue());

        Assert.assertEquals(1001, this.getTournament().getTournamentPlayers().get(4).getPoints().intValue());
        Assert.assertEquals(5, this.getTournament().getTournamentPlayers().get(4).getFinishRound());
        Assert.assertEquals(3, this.getTournament().getTournamentPlayers().get(4).getNumberOfHooks().intValue());

        Assert.assertEquals(1200, this.getTournament().getTournamentPlayers().get(5).getPoints().intValue());
        Assert.assertEquals(4, this.getTournament().getTournamentPlayers().get(5).getFinishRound());
        Assert.assertEquals(3, this.getTournament().getTournamentPlayers().get(5).getNumberOfHooks().intValue());
    }

    @Test
    public void isTournamentFinsih() {
        this.getTournament().startTournament();
        Assert.assertFalse(this.getTournament().isTournamentFinsih());
        this.getTournament().getTournamentPlayers().get(0).setPoints(1500);
        this.getTournament().getTournamentPlayers().get(0).setFinishRound(3);
        this.getTournament().getTournamentPlayers().get(1).setPoints(845);
        this.getTournament().getTournamentPlayers().get(2).setPoints(154);
        this.getTournament().getTournamentPlayers().get(3).setPoints(1020);
        this.getTournament().getTournamentPlayers().get(3).setFinishRound(11);
        this.getTournament().getTournamentPlayers().get(4).setPoints(550);
        this.getTournament().getTournamentPlayers().get(5).setPoints(440);
        this.getTournament().sortTheTournamentPlayer();
        Assert.assertFalse(this.getTournament().isTournamentFinsih());
        this.getTournament().getTournamentPlayers().get(0).setPoints(1500);
        this.getTournament().getTournamentPlayers().get(0).setFinishRound(3);
        this.getTournament().getTournamentPlayers().get(1).setPoints(1000);
        this.getTournament().getTournamentPlayers().get(1).setFinishRound(12);
        this.getTournament().getTournamentPlayers().get(2).setPoints(1500);
        this.getTournament().getTournamentPlayers().get(2).setFinishRound(13);
        this.getTournament().getTournamentPlayers().get(3).setPoints(1020);
        this.getTournament().getTournamentPlayers().get(3).setFinishRound(13);
        this.getTournament().getTournamentPlayers().get(4).setPoints(1500);
        this.getTournament().getTournamentPlayers().get(4).setFinishRound(16);
        this.getTournament().getTournamentPlayers().get(5).setPoints(440);
        this.getTournament().sortTheTournamentPlayer();
        Assert.assertTrue(this.getTournament().isTournamentFinsih());
    }

    @Test
    public void emuleATournament() {
        this.getTournament().startTournament();
        this.getTournament().setMaxOfHooks(1);
        this.getTournament().setMaxOfPoints(10);
        Assert.assertFalse(this.getTournament().isTournamentFinsih());
        Round round1 = new Round();
        List<PlayedRelationship> round1PLayers = new ArrayList<PlayedRelationship>();
        round1PLayers.add(round1.wonTheRound((this.getTournament().getTournamentPlayers().get(0).getPlayer())));
        round1PLayers.add(round1.endRoundFor((this.getTournament().getTournamentPlayers().get(1).getPlayer()), 10));
        round1PLayers.add(round1.endRoundFor((this.getTournament().getTournamentPlayers().get(2).getPlayer()), 2));
        round1PLayers.add(round1.endRoundFor((this.getTournament().getTournamentPlayers().get(3).getPlayer()), 5));
        round1PLayers.add(round1.endRoundFor((this.getTournament().getTournamentPlayers().get(4).getPlayer()), 12));
        round1PLayers.add(round1.endRoundFor((this.getTournament().getTournamentPlayers().get(5).getPlayer()), 7));
        round1.setPlayersPlayed(round1PLayers);
        this.getTournament().addANewRound(round1);
        
        Assert.assertFalse(this.getTournament().isTournamentFinsih());
        Assert.assertEquals(0, this.getTournament().getTournamentPlayers().get(0).getPoints().intValue());
        Assert.assertEquals(-1, this.getTournament().getTournamentPlayers().get(0).getFinishRound());
        Assert.assertEquals(0, this.getTournament().getTournamentPlayers().get(0).getNumberOfHooks().intValue());
        Assert.assertEquals(2, this.getTournament().getTournamentPlayers().get(1).getPoints().intValue());
        Assert.assertEquals(-1, this.getTournament().getTournamentPlayers().get(1).getFinishRound());
        Assert.assertEquals(0, this.getTournament().getTournamentPlayers().get(1).getNumberOfHooks().intValue());
        Assert.assertEquals(5, this.getTournament().getTournamentPlayers().get(2).getPoints().intValue());
        Assert.assertEquals(-1, this.getTournament().getTournamentPlayers().get(2).getFinishRound());
        Assert.assertEquals(0, this.getTournament().getTournamentPlayers().get(2).getNumberOfHooks().intValue());
        Assert.assertEquals(7, this.getTournament().getTournamentPlayers().get(3).getPoints().intValue());
        Assert.assertEquals(-1, this.getTournament().getTournamentPlayers().get(3).getFinishRound());
        Assert.assertEquals(0, this.getTournament().getTournamentPlayers().get(3).getNumberOfHooks().intValue());
        Assert.assertEquals(7, this.getTournament().getTournamentPlayers().get(4).getPoints().intValue());
        Assert.assertEquals(-1, this.getTournament().getTournamentPlayers().get(4).getFinishRound());
        Assert.assertEquals(1, this.getTournament().getTournamentPlayers().get(4).getNumberOfHooks().intValue());
        Assert.assertEquals(7, this.getTournament().getTournamentPlayers().get(5).getPoints().intValue());
        Assert.assertEquals(-1, this.getTournament().getTournamentPlayers().get(5).getFinishRound());
        Assert.assertEquals(1, this.getTournament().getTournamentPlayers().get(5).getNumberOfHooks().intValue());
        
        // Other Round
        Round round2 = new Round();
        List<PlayedRelationship> round2Players = new ArrayList<PlayedRelationship>();
        round2Players.add(round2.endRoundFor(this.getTournament().getTournamentPlayers().get(0).getPlayer(), 1));
        round2Players.add(round2.wonTheRound(this.getTournament().getTournamentPlayers().get(1).getPlayer()));
        round2Players.add(round2.endRoundFor(this.getTournament().getTournamentPlayers().get(2).getPlayer(), 3));
        round2Players.add(round2.endRoundFor(this.getTournament().getTournamentPlayers().get(3).getPlayer(), 3));
        round2Players.add(round2.endRoundFor(this.getTournament().getTournamentPlayers().get(4).getPlayer(), 8));
        round2Players.add(round2.endRoundFor(this.getTournament().getTournamentPlayers().get(5).getPlayer(), 0));
        round2.setPlayersPlayed(round2Players);
        this.getTournament().addANewRound(round2);
        
        Assert.assertFalse(this.getTournament().isTournamentFinsih());
        Assert.assertEquals(1, this.getTournament().getTournamentPlayers().get(0).getPoints().intValue());
        Assert.assertEquals(-1, this.getTournament().getTournamentPlayers().get(0).getFinishRound());
        Assert.assertEquals(0, this.getTournament().getTournamentPlayers().get(0).getNumberOfHooks().intValue());
        Assert.assertEquals(2, this.getTournament().getTournamentPlayers().get(1).getPoints().intValue());
        Assert.assertEquals(-1, this.getTournament().getTournamentPlayers().get(1).getFinishRound());
        Assert.assertEquals(0, this.getTournament().getTournamentPlayers().get(1).getNumberOfHooks().intValue());
        Assert.assertEquals(7, this.getTournament().getTournamentPlayers().get(2).getPoints().intValue());
        Assert.assertEquals(-1, this.getTournament().getTournamentPlayers().get(2).getFinishRound());
        Assert.assertEquals(1, this.getTournament().getTournamentPlayers().get(2).getNumberOfHooks().intValue());
        Assert.assertEquals(8, this.getTournament().getTournamentPlayers().get(3).getPoints().intValue());
        Assert.assertEquals(-1, this.getTournament().getTournamentPlayers().get(3).getFinishRound());
        Assert.assertEquals(0, this.getTournament().getTournamentPlayers().get(3).getNumberOfHooks().intValue());
        Assert.assertEquals(8, this.getTournament().getTournamentPlayers().get(4).getPoints().intValue());
        Assert.assertEquals(-1, this.getTournament().getTournamentPlayers().get(4).getFinishRound());
        Assert.assertEquals(1, this.getTournament().getTournamentPlayers().get(4).getNumberOfHooks().intValue());
        Assert.assertEquals(15, this.getTournament().getTournamentPlayers().get(5).getPoints().intValue());
        Assert.assertEquals(2, this.getTournament().getTournamentPlayers().get(5).getFinishRound());
        Assert.assertEquals(1, this.getTournament().getTournamentPlayers().get(5).getNumberOfHooks().intValue());

        // Other Round
        Round round3 = new Round();
        List<PlayedRelationship> round3Players = new ArrayList<PlayedRelationship>();
        round3Players.add(round3.endRoundFor(this.getTournament().getTournamentPlayers().get(0).getPlayer(), 4));
        round3Players.add(round3.wonTheRound(this.getTournament().getTournamentPlayers().get(1).getPlayer()));
        round3Players.add(round3.endRoundFor(this.getTournament().getTournamentPlayers().get(2).getPlayer(), 1));
        round3Players.add(round3.endRoundFor(this.getTournament().getTournamentPlayers().get(3).getPlayer(), 3));
        round3Players.add(round3.endRoundFor(this.getTournament().getTournamentPlayers().get(4).getPlayer(), 1));
        round3.setPlayersPlayed(round3Players);
        this.getTournament().addANewRound(round3);
        
        Assert.assertFalse(this.getTournament().isTournamentFinsih());
        Assert.assertEquals(2, this.getTournament().getTournamentPlayers().get(0).getPoints().intValue());
        Assert.assertEquals(-1, this.getTournament().getTournamentPlayers().get(0).getFinishRound());
        Assert.assertEquals(0, this.getTournament().getTournamentPlayers().get(0).getNumberOfHooks().intValue());
        Assert.assertEquals(5, this.getTournament().getTournamentPlayers().get(1).getPoints().intValue());
        Assert.assertEquals(-1, this.getTournament().getTournamentPlayers().get(1).getFinishRound());
        Assert.assertEquals(0, this.getTournament().getTournamentPlayers().get(1).getNumberOfHooks().intValue());
        Assert.assertEquals(8, this.getTournament().getTournamentPlayers().get(2).getPoints().intValue());
        Assert.assertEquals(-1, this.getTournament().getTournamentPlayers().get(2).getFinishRound());
        Assert.assertEquals(1, this.getTournament().getTournamentPlayers().get(2).getNumberOfHooks().intValue());
        Assert.assertEquals(9, this.getTournament().getTournamentPlayers().get(3).getPoints().intValue());
        Assert.assertEquals(-1, this.getTournament().getTournamentPlayers().get(3).getFinishRound());
        Assert.assertEquals(1, this.getTournament().getTournamentPlayers().get(3).getNumberOfHooks().intValue());
        Assert.assertEquals(9, this.getTournament().getTournamentPlayers().get(4).getPoints().intValue());
        Assert.assertEquals(-1, this.getTournament().getTournamentPlayers().get(4).getFinishRound());
        Assert.assertEquals(1, this.getTournament().getTournamentPlayers().get(4).getNumberOfHooks().intValue());
        Assert.assertEquals(15, this.getTournament().getTournamentPlayers().get(5).getPoints().intValue());
        Assert.assertEquals(2, this.getTournament().getTournamentPlayers().get(5).getFinishRound());
        Assert.assertEquals(1, this.getTournament().getTournamentPlayers().get(5).getNumberOfHooks().intValue());
    
        
        // Other Round
        Round round4 = new Round();
        List<PlayedRelationship> round4Players = new ArrayList<PlayedRelationship>();
        round4Players.add(round4.endRoundFor(this.getTournament().getTournamentPlayers().get(0).getPlayer(), 5));
        round4Players.add(round4.endRoundFor(this.getTournament().getTournamentPlayers().get(1).getPlayer(), 4));
        round4Players.add(round4.endRoundFor(this.getTournament().getTournamentPlayers().get(2).getPlayer(), 10));
        round4Players.add(round4.wonTheRound(this.getTournament().getTournamentPlayers().get(3).getPlayer()));
        round4Players.add(round4.endRoundFor(this.getTournament().getTournamentPlayers().get(4).getPlayer(), 0));
        round4.setPlayersPlayed(round4Players);
        this.getTournament().addANewRound(round4);
        
        Assert.assertFalse(this.getTournament().isTournamentFinsih());
        Assert.assertEquals(7, this.getTournament().getTournamentPlayers().get(0).getPoints().intValue());
        Assert.assertEquals(-1, this.getTournament().getTournamentPlayers().get(0).getFinishRound());
        Assert.assertEquals(0, this.getTournament().getTournamentPlayers().get(0).getNumberOfHooks().intValue());
        Assert.assertEquals(9, this.getTournament().getTournamentPlayers().get(1).getPoints().intValue());
        Assert.assertEquals(-1, this.getTournament().getTournamentPlayers().get(1).getFinishRound());
        Assert.assertEquals(0, this.getTournament().getTournamentPlayers().get(1).getNumberOfHooks().intValue());
        Assert.assertEquals(9, this.getTournament().getTournamentPlayers().get(2).getPoints().intValue());
        Assert.assertEquals(-1, this.getTournament().getTournamentPlayers().get(2).getFinishRound());
        Assert.assertEquals(1, this.getTournament().getTournamentPlayers().get(2).getNumberOfHooks().intValue());
        Assert.assertEquals(9, this.getTournament().getTournamentPlayers().get(3).getPoints().intValue());
        Assert.assertEquals(-1, this.getTournament().getTournamentPlayers().get(3).getFinishRound());
        Assert.assertEquals(1, this.getTournament().getTournamentPlayers().get(3).getNumberOfHooks().intValue());
        Assert.assertEquals(18, this.getTournament().getTournamentPlayers().get(4).getPoints().intValue());
        Assert.assertEquals(4, this.getTournament().getTournamentPlayers().get(4).getFinishRound());
        Assert.assertEquals(1, this.getTournament().getTournamentPlayers().get(4).getNumberOfHooks().intValue());
        Assert.assertEquals(15, this.getTournament().getTournamentPlayers().get(5).getPoints().intValue());
        Assert.assertEquals(2, this.getTournament().getTournamentPlayers().get(5).getFinishRound());
        Assert.assertEquals(1, this.getTournament().getTournamentPlayers().get(5).getNumberOfHooks().intValue());

        // Other Round
        Round round5 = new Round();
        List<PlayedRelationship> round5Players = new ArrayList<PlayedRelationship>();
        round5Players.add(round5.wonTheRound(this.getTournament().getTournamentPlayers().get(0).getPlayer()));
        round5Players.add(round5.endRoundFor(this.getTournament().getTournamentPlayers().get(1).getPlayer(), 4));
        round5Players.add(round5.endRoundFor(this.getTournament().getTournamentPlayers().get(2).getPlayer(), 8));
        round5Players.add(round5.endRoundFor(this.getTournament().getTournamentPlayers().get(3).getPlayer(), 2));
        round5.setPlayersPlayed(round5Players);
        this.getTournament().addANewRound(round5);
        
        Assert.assertFalse(this.getTournament().isTournamentFinsih());
        Assert.assertEquals(7, this.getTournament().getTournamentPlayers().get(0).getPoints().intValue());
        Assert.assertEquals(-1, this.getTournament().getTournamentPlayers().get(0).getFinishRound());
        Assert.assertEquals(0, this.getTournament().getTournamentPlayers().get(0).getNumberOfHooks().intValue());
        Assert.assertEquals(7, this.getTournament().getTournamentPlayers().get(1).getPoints().intValue());
        Assert.assertEquals(-1, this.getTournament().getTournamentPlayers().get(1).getFinishRound());
        Assert.assertEquals(1, this.getTournament().getTournamentPlayers().get(1).getNumberOfHooks().intValue());
        Assert.assertEquals(11, this.getTournament().getTournamentPlayers().get(2).getPoints().intValue());
        Assert.assertEquals(5, this.getTournament().getTournamentPlayers().get(2).getFinishRound());
        Assert.assertEquals(1, this.getTournament().getTournamentPlayers().get(2).getNumberOfHooks().intValue());
        Assert.assertEquals(17, this.getTournament().getTournamentPlayers().get(3).getPoints().intValue());
        Assert.assertEquals(5, this.getTournament().getTournamentPlayers().get(3).getFinishRound());
        Assert.assertEquals(1, this.getTournament().getTournamentPlayers().get(3).getNumberOfHooks().intValue());
        Assert.assertEquals(18, this.getTournament().getTournamentPlayers().get(4).getPoints().intValue());
        Assert.assertEquals(4, this.getTournament().getTournamentPlayers().get(4).getFinishRound());
        Assert.assertEquals(1, this.getTournament().getTournamentPlayers().get(4).getNumberOfHooks().intValue());
        Assert.assertEquals(15, this.getTournament().getTournamentPlayers().get(5).getPoints().intValue());
        Assert.assertEquals(2, this.getTournament().getTournamentPlayers().get(5).getFinishRound());
        Assert.assertEquals(1, this.getTournament().getTournamentPlayers().get(5).getNumberOfHooks().intValue());

        // Other Round
        Round round6 = new Round();
        List<PlayedRelationship> round6Players = new ArrayList<PlayedRelationship>();
        round6Players.add(round6.wonTheRound(this.getTournament().getTournamentPlayers().get(0).getPlayer()));
        round6Players.add(round6.endRoundFor(this.getTournament().getTournamentPlayers().get(1).getPlayer(), 1));
        round6.setPlayersPlayed(round6Players);
        this.getTournament().addANewRound(round6);
        
        Assert.assertFalse(this.getTournament().isTournamentFinsih());
        Assert.assertEquals(7, this.getTournament().getTournamentPlayers().get(0).getPoints().intValue());
        Assert.assertEquals(-1, this.getTournament().getTournamentPlayers().get(0).getFinishRound());
        Assert.assertEquals(0, this.getTournament().getTournamentPlayers().get(0).getNumberOfHooks().intValue());
        Assert.assertEquals(8, this.getTournament().getTournamentPlayers().get(1).getPoints().intValue());
        Assert.assertEquals(-1, this.getTournament().getTournamentPlayers().get(1).getFinishRound());
        Assert.assertEquals(1, this.getTournament().getTournamentPlayers().get(1).getNumberOfHooks().intValue());
        Assert.assertEquals(11, this.getTournament().getTournamentPlayers().get(2).getPoints().intValue());
        Assert.assertEquals(5, this.getTournament().getTournamentPlayers().get(2).getFinishRound());
        Assert.assertEquals(1, this.getTournament().getTournamentPlayers().get(2).getNumberOfHooks().intValue());
        Assert.assertEquals(17, this.getTournament().getTournamentPlayers().get(3).getPoints().intValue());
        Assert.assertEquals(5, this.getTournament().getTournamentPlayers().get(3).getFinishRound());
        Assert.assertEquals(1, this.getTournament().getTournamentPlayers().get(3).getNumberOfHooks().intValue());
        Assert.assertEquals(18, this.getTournament().getTournamentPlayers().get(4).getPoints().intValue());
        Assert.assertEquals(4, this.getTournament().getTournamentPlayers().get(4).getFinishRound());
        Assert.assertEquals(1, this.getTournament().getTournamentPlayers().get(4).getNumberOfHooks().intValue());
        Assert.assertEquals(15, this.getTournament().getTournamentPlayers().get(5).getPoints().intValue());
        Assert.assertEquals(2, this.getTournament().getTournamentPlayers().get(5).getFinishRound());
        Assert.assertEquals(1, this.getTournament().getTournamentPlayers().get(5).getNumberOfHooks().intValue());

        // Other Round
        Round round7 = new Round();
        List<PlayedRelationship> round7Players = new ArrayList<PlayedRelationship>();
        round7Players.add(round7.endRoundFor(this.getTournament().getTournamentPlayers().get(0).getPlayer(), 10));
        round7Players.add(round7.wonTheRound(this.getTournament().getTournamentPlayers().get(1).getPlayer()));
        round7.setPlayersPlayed(round7Players);
        this.getTournament().addANewRound(round7);
        
        Assert.assertFalse(this.getTournament().isTournamentFinsih());
        Assert.assertEquals(8, this.getTournament().getTournamentPlayers().get(0).getPoints().intValue());
        Assert.assertEquals(-1, this.getTournament().getTournamentPlayers().get(0).getFinishRound());
        Assert.assertEquals(1, this.getTournament().getTournamentPlayers().get(0).getNumberOfHooks().intValue());
        Assert.assertEquals(8, this.getTournament().getTournamentPlayers().get(1).getPoints().intValue());
        Assert.assertEquals(-1, this.getTournament().getTournamentPlayers().get(1).getFinishRound());
        Assert.assertEquals(1, this.getTournament().getTournamentPlayers().get(1).getNumberOfHooks().intValue());
        Assert.assertEquals(11, this.getTournament().getTournamentPlayers().get(2).getPoints().intValue());
        Assert.assertEquals(5, this.getTournament().getTournamentPlayers().get(2).getFinishRound());
        Assert.assertEquals(1, this.getTournament().getTournamentPlayers().get(2).getNumberOfHooks().intValue());
        Assert.assertEquals(17, this.getTournament().getTournamentPlayers().get(3).getPoints().intValue());
        Assert.assertEquals(5, this.getTournament().getTournamentPlayers().get(3).getFinishRound());
        Assert.assertEquals(1, this.getTournament().getTournamentPlayers().get(3).getNumberOfHooks().intValue());
        Assert.assertEquals(18, this.getTournament().getTournamentPlayers().get(4).getPoints().intValue());
        Assert.assertEquals(4, this.getTournament().getTournamentPlayers().get(4).getFinishRound());
        Assert.assertEquals(1, this.getTournament().getTournamentPlayers().get(4).getNumberOfHooks().intValue());
        Assert.assertEquals(15, this.getTournament().getTournamentPlayers().get(5).getPoints().intValue());
        Assert.assertEquals(2, this.getTournament().getTournamentPlayers().get(5).getFinishRound());
        Assert.assertEquals(1, this.getTournament().getTournamentPlayers().get(5).getNumberOfHooks().intValue());

        // Other Round
        Round round8 = new Round();
        List<PlayedRelationship> round8Players = new ArrayList<PlayedRelationship>();
        round8Players.add(round8.endRoundFor(this.getTournament().getTournamentPlayers().get(0).getPlayer(), 10));
        round8Players.add(round8.wonTheRound(this.getTournament().getTournamentPlayers().get(1).getPlayer()));
        round8.setPlayersPlayed(round8Players);
        this.getTournament().addANewRound(round8);
        
        Assert.assertTrue(this.getTournament().isTournamentFinsih());
        Assert.assertEquals(8, this.getTournament().getTournamentPlayers().get(0).getPoints().intValue());
        Assert.assertEquals(-1, this.getTournament().getTournamentPlayers().get(0).getFinishRound());
        Assert.assertEquals(1, this.getTournament().getTournamentPlayers().get(0).getNumberOfHooks().intValue());
        Assert.assertEquals(18, this.getTournament().getTournamentPlayers().get(1).getPoints().intValue());
        Assert.assertEquals(8, this.getTournament().getTournamentPlayers().get(1).getFinishRound());
        Assert.assertEquals(1, this.getTournament().getTournamentPlayers().get(1).getNumberOfHooks().intValue());
        Assert.assertEquals(11, this.getTournament().getTournamentPlayers().get(2).getPoints().intValue());
        Assert.assertEquals(5, this.getTournament().getTournamentPlayers().get(2).getFinishRound());
        Assert.assertEquals(1, this.getTournament().getTournamentPlayers().get(2).getNumberOfHooks().intValue());
        Assert.assertEquals(17, this.getTournament().getTournamentPlayers().get(3).getPoints().intValue());
        Assert.assertEquals(5, this.getTournament().getTournamentPlayers().get(3).getFinishRound());
        Assert.assertEquals(1, this.getTournament().getTournamentPlayers().get(3).getNumberOfHooks().intValue());
        Assert.assertEquals(18, this.getTournament().getTournamentPlayers().get(4).getPoints().intValue());
        Assert.assertEquals(4, this.getTournament().getTournamentPlayers().get(4).getFinishRound());
        Assert.assertEquals(1, this.getTournament().getTournamentPlayers().get(4).getNumberOfHooks().intValue());
        Assert.assertEquals(15, this.getTournament().getTournamentPlayers().get(5).getPoints().intValue());
        Assert.assertEquals(2, this.getTournament().getTournamentPlayers().get(5).getFinishRound());
        Assert.assertEquals(1, this.getTournament().getTournamentPlayers().get(5).getNumberOfHooks().intValue());
        
        
        
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
