package com.unotournamentboard.model;

import java.util.Iterator;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

/**
 * @author hgastaud
 * 
 */
public class CommunityTest {

    private Community community;

    @Before
    public void setUp() {
        this.setCommunity(new Community("My Community"));
    }

    @Test
    public void createNewMemberShip() {
        Player player = new Player("Hernan", "Gastaud", "hgastaud", "12345", "Argentina", "jksad1@ksad.com");
        player.setId(Long.valueOf(1));

        Assert.assertEquals(0, this.getCommunity().getMembers().size());
        Member membership = this.getCommunity().createNewMemberShip(player);
        Assert.assertEquals(1, this.getCommunity().getMembers().size());
        Iterator<Player> iterator = this.getCommunity().getMembers().iterator();
        iterator.hasNext();
        Assert.assertEquals(player, iterator.next());
        Assert.assertEquals(this.getCommunity(), membership.getCommunity());
        Assert.assertEquals(player, membership.getPlayer());

    }

    /**
     * @return the community
     */
    protected Community getCommunity() {
        return community;
    }

    /**
     * @param community
     *            the community to set
     */
    protected void setCommunity(Community community) {
        this.community = community;
    }

}
