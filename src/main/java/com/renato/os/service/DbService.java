package com.renato.os.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.renato.os.domain.Cliente;
import com.renato.os.domain.OrdemServico;
import com.renato.os.domain.Tecnico;
import com.renato.os.domain.enums.Prioridade;
import com.renato.os.domain.enums.Status;
import com.renato.os.repository.ClienteRepository;
import com.renato.os.repository.OrdemServicoRepository;
import com.renato.os.repository.TecnicoRepository;

@Service
public class DbService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private OrdemServicoRepository ordemServicoRepository;

	public void instanciaDb() {
		Tecnico t1 = new Tecnico(null, "Renato Guedes", "381.195.040-11", "(71) 46229-4511");
		Cliente c1 = new Cliente(null, "Tomás Silvino", "157.651.540-00", "(16) 66842-3980");

		OrdemServico os1 = new OrdemServico(null, Prioridade.ALTA, "Resolva logo", Status.EM_ANDAMENTO, t1, c1);

		t1.getOrdensServicoList().add(os1);
		c1.getOrdensServicoList().add(os1);

		tecnicoRepository.saveAll(Arrays.asList(t1));
		clienteRepository.saveAll(Arrays.asList(c1));
		ordemServicoRepository.saveAll(Arrays.asList(os1));
	}
}
