package br.com.franca.frangao_assado.repository;

import br.com.franca.frangao_assado.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, UUID> {

    List<Cliente> findAll();


    Optional<Cliente> findByEmail(String email);
}