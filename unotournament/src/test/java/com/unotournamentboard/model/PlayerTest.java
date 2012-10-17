////////////////////////////////////////////////////////////////////////////////
// This file is the confidential property of Greenline Financial Technologies.
// Possession, use, transmission, or disclosure of this file is prohibited
// without express written authorization from Greenline Financial Technologies.
// Copyright 2008 Greenline Financial Technologies. All rights reserved.
////////////////////////////////////////////////////////////////////////////////

/**
 * 
 */
package com.unotournamentboard.model;

import java.util.Iterator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author hgastaud
 * 
 */
public class PlayerTest {

    private Player player;

    @Before
    public void setUp() {
        this.setPlayer(new Player("Hernan", "Gastaud", "hgastaud", "12345", "Argentina", "hgastaud@w.com"));
        this.getPlayer().setId(new Long(1));
    }

    @Test
    public void blockAccount() {
        Assert.assertFalse(this.getPlayer().getActive());
        this.getPlayer().setActive(true);
        Assert.assertTrue(this.getPlayer().getActive());
        this.getPlayer().blockAccount();
        Assert.assertFalse(this.getPlayer().getActive());
    }

    @Test
    public void addNewFailLoginAttemp() {
        this.getPlayer().setActive(true);
        Assert.assertTrue(this.getPlayer().getActive());
        Assert.assertEquals(Integer.valueOf(0), this.getPlayer().getWrongLogingAttemps());
        this.getPlayer().addNewFailLoginAttemp();
        Assert.assertTrue(this.getPlayer().getActive());
        Assert.assertEquals(Integer.valueOf(1), this.getPlayer().getWrongLogingAttemps());
        this.getPlayer().addNewFailLoginAttemp();
        Assert.assertTrue(this.getPlayer().getActive());
        Assert.assertEquals(Integer.valueOf(2), this.getPlayer().getWrongLogingAttemps());
        this.getPlayer().addNewFailLoginAttemp();
        Assert.assertTrue(this.getPlayer().getActive());
        Assert.assertEquals(Integer.valueOf(3), this.getPlayer().getWrongLogingAttemps());
        this.getPlayer().addNewFailLoginAttemp();
        Assert.assertFalse(this.getPlayer().getActive());
        Assert.assertEquals(Integer.valueOf(4), this.getPlayer().getWrongLogingAttemps());
    }

    @Test
    public void activeAccount() {
        this.getPlayer().setActive(true);
        Assert.assertTrue(this.getPlayer().getActive());
        Assert.assertEquals(Integer.valueOf(0), this.getPlayer().getWrongLogingAttemps());
        this.getPlayer().addNewFailLoginAttemp();
        this.getPlayer().addNewFailLoginAttemp();
        this.getPlayer().addNewFailLoginAttemp();
        this.getPlayer().addNewFailLoginAttemp();
        Assert.assertFalse(this.getPlayer().getActive());
        Assert.assertEquals(Integer.valueOf(4), this.getPlayer().getWrongLogingAttemps());
        this.getPlayer().activeAccount();
        Assert.assertTrue(this.getPlayer().getActive());
        Assert.assertEquals(Integer.valueOf(0), this.getPlayer().getWrongLogingAttemps());
    }

    @Test
    public void addANewFriend() {
        Player friend = new Player("Mercedes", "Espineira", "me", "12345", "Argentina", "asd@fd.com");
        friend.setId(Long.valueOf(2));
        Assert.assertEquals(0, this.getPlayer().getFriends().size());
        Friendship friendship = this.getPlayer().addANewFriend(friend);
        Assert.assertEquals(1, this.getPlayer().getFriends().size());
        Iterator<Player> iterator = this.getPlayer().getFriends().iterator();
        iterator.hasNext();
        Assert.assertEquals(friend, iterator.next());
        Assert.assertEquals(this.getPlayer(), friendship.getStartNode());
        Assert.assertEquals(friend, friendship.getEndNode());

    }

    /**
     * @return the player
     */
    protected Player getPlayer() {
        return player;
    }

    /**
     * @param player
     *            the player to set
     */
    protected void setPlayer(Player player) {
        this.player = player;
    }

}
