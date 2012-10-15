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
    }

}
