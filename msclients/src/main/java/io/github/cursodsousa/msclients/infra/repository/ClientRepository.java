package io.github.cursodsousa.msclients.infra.repository;

import io.github.cursodsousa.msclients.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
