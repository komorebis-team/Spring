package com.itesm.komorebi.repositories;

import com.itesm.komorebi.models.User;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
@EnableScan
public interface UserRepository extends CrudRepository<User, String> {
    List<User> findAll();
    List<User> findAllByRole(String role);

    @Override
    Optional<User> findById(String s);
}
