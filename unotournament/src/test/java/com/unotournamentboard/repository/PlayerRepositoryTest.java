package com.unotournamentboard.repository;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.unotournamentboard.model.Player;

/**
 * @author hgastaud
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "/applicationContext-test.xml" })
@Transactional
public class PlayerRepositoryTest {

    @Autowired
    private PlayerRepository playerRepo;

    @Test
    @Transactional
    public void persistPlayer() {
        Player player = this.getPlayerRepo().save(new Player("Hernan", "Gastaud", "hgastaud", "12345", "Argentina", "asdsad@gks,.com"));
        Assert.assertNotNull(player.getId());
        Player mer = this.playerRepo.save(new Player("Mercedes", "Espineira", "mespineira", "12345", "Argentina", "sadsq@sad.com"));
        player.addANewFriend(mer);
        this.getPlayerRepo().save(player);
        mer.addANewFriend(player);
        this.getPlayerRepo().save(mer);
        System.err.println("Hernan ID: " + player.getId());
        System.err.println("Mercedes ID: " + mer.getId());
    }

    /**
     * @return the playerRepo
     */
    public PlayerRepository getPlayerRepo() {
        return playerRepo;
    }

    /**
     * @param playerRepo
     *            the playerRepo to set
     */
    public void setPlayerRepo(PlayerRepository playerRepo) {
        this.playerRepo = playerRepo;
    }

}
