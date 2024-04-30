package io.github.berdnaski.barbearia.domain.repository;

import io.github.berdnaski.barbearia.domain.entity.Barber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarberRepository extends JpaRepository<Barber, Long> {
}
