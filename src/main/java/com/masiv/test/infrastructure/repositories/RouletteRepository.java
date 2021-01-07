package com.masiv.test.infrastructure.repositories;

import com.masiv.test.domain.documents.Roulette;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouletteRepository extends MongoRepository<Roulette, String> {}
