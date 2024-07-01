package org.example.appchekingefficiency.repo;

import org.example.appchekingefficiency.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    Client findByLink(String link);
}