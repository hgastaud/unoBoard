package com.unotournamentboard.model;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

/**
 * @author hgastaud
 * 
 */
public class PlayerInTournamentTest {

    private PlayerInTournament player;

    @Before
    public void setUp() {
        Player player = new Player("Hernan", "Gastaud", "hgastaud", "12345", "Argentina", "hasdkja@dfsd.com");
        player.setId(Long.valueOf(1));
        this.setPlayer(new PlayerInTournament(player));
    }

    @Test
    public void addPoints() {
        Assert.assertEquals(Integer.valueOf(0), this.getPlayer().getPoints());
        this.getPlayer().addPoints(Integer.valueOf(154));
        Assert.assertEquals(Integer.valueOf(154), this.getPlayer().getPoints());
        this.getPlayer().addPoints(Integer.valueOf(3));
        Assert.assertEquals(Integer.valueOf(157), this.getPlayer().getPoints());
    }

    @Test
    public void addHook() {
        Assert.assertEquals(Integer.valueOf(0), this.getPlayer().getPoints());
        Assert.assertEquals(Integer.valueOf(0), this.getPlayer().getNumberOfHooks());
        this.getPlayer().addHook(Integer.valueOf(400));
        Assert.assertEquals(Integer.valueOf(400), this.getPlayer().getPoints());
        Assert.assertEquals(Integer.valueOf(1), this.getPlayer().getNumberOfHooks());
        this.getPlayer().addHook(Integer.valueOf(400));
        Assert.assertEquals(Integer.valueOf(400), this.getPlayer().getPoints());
        Assert.assertEquals(Integer.valueOf(2), this.getPlayer().getNumberOfHooks());
        this.getPlayer().addHook(Integer.valueOf(487));
        Assert.assertEquals(Integer.valueOf(487), this.getPlayer().getPoints());
        Assert.assertEquals(Integer.valueOf(3), this.getPlayer().getNumberOfHooks());
    }

    /**
     * @return the player
     */
    protected PlayerInTournament getPlayer() {
        return player;
    }

    /**
     * @param player
     *            the player to set
     */
    protected void setPlayer(PlayerInTournament player) {
        this.player = player;
    }

}
