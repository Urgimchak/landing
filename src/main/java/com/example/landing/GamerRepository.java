package com.example.landing;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface GamerRepository extends CrudRepository<Gamer, Long>{
    List<Gamer> findByName(long name);
}