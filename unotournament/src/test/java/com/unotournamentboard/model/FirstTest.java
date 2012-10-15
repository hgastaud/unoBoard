package com.unotournamentboard.model;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.support.Neo4jTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/applicationContext-test.xml"})
@Transactional
public class FirstTest {

    @Autowired
    Neo4jTemplate template;
    
    @Test
    @Transactional
    public void persistPlayer(){
        Player player = new Player();
        player.setCountry("Argentina");
        player.setName("Hernan");
        player.setLastName("Gastaud");
        player.setPassword("12345");
        player.setUsername("hgastaud");
        player=  template.save(player);
        
        Player retrievedPlayer = template.findOne(player.getId(), Player.class);
        Assert.assertEquals("Hernan", retrievedPlayer.getName());
    }
    
}
