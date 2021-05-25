package com.renato.os.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.renato.os.domain.OrdemServico;
@Repository
public interface OrdemServicoRepository extends JpaRepository<OrdemServico, Integer>{

}
