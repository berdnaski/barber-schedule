package io.github.berdnaski.barbearia.domain.repository;

import io.github.berdnaski.barbearia.domain.entity.ServiceOffered;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceOfferedRepository extends JpaRepository<ServiceOffered, Long> {
}
