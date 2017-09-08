package me.anna.demo.repositories;

import me.anna.demo.models.Userz;
import org.springframework.data.repository.CrudRepository;

public interface UserzRepo extends CrudRepository<Userz, Long> {
    Userz findByUsername(String username);

    Userz findByEmail(String email);

    Long countByEmail(String email);

    Long countByUsername(String username);

}
