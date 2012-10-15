package com.unotournamentboard.repository;

import org.springframework.data.neo4j.repository.GraphRepository;

import com.unotournamentboard.model.Community;

/**
 * @author hgastaud
 * 
 */
public interface CommunityRepository extends GraphRepository<Community> {

}
