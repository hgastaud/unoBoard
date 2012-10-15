package com.unotournamentboard.repository;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.unotournamentboard.model.Tournament;

/**
 * @author hgastaud
 * 
 */
public interface TournamentRepositoy extends GraphRepository<Tournament> {

}
