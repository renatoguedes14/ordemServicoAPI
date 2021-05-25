package com.renato.os.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.renato.os.domain.Pessoa;
@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{

	@Query("SELECT obj FROM Pessoa obj WHERE obj.cpf =:cpf")
	Pessoa findByCpf(@Param("cpf") String cpf);

}
