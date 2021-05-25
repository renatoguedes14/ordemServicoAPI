package com.renato.os.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.renato.os.domain.Cliente;
@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
