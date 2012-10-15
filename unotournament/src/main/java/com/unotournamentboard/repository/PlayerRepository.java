package com.unotournamentboard.repository;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.unotournamentboard.model.Player;

/**
 * @author hgastaud
 *
 */
public interface PlayerRepository extends GraphRepository<Player> {

}
