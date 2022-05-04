package com.itesm.komorebi.repositories;

import com.itesm.komorebi.models.Configuration;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
@EnableScan
public interface ConfigurationRepository extends CrudRepository<Configuration, Integer> {
    Optional<Configuration> findById(Integer integer);
    Configuration save(Configuration configuration);
}
