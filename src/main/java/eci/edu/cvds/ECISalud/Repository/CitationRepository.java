package eci.edu.cvds.ECISalud.Repository;


import eci.edu.cvds.ECISalud.Entitity.Citation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CitationRepository extends MongoRepository<Citation,String>
{
    Optional<Citation> findByEmail(String email);
    Optional<Citation> findByEmailAndState(String email, String state);
}
