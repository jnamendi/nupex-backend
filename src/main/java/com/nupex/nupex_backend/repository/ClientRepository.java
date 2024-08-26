package com.nupex.nupex_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nupex.nupex_backend.model.Client;

public interface ClientRepository extends JpaRepository <Client, Long>{
}
