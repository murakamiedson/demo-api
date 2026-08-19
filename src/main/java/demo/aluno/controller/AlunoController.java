package demo.aluno.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import demo.aluno.dto.AlunoDTO;
import demo.aluno.model.Aluno;
import demo.aluno.model.AlunoRepository;
import demo.aluno.service.AlunoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.log4j.Log4j2;

@Tag(name = "demo-api", description = "API para manter alunos.")
@Log4j2
@RequestMapping(path = "/demo-api") // This means URL's start with /teste-api
@RestController
public class AlunoController {

	@Autowired 
	private AlunoService alunoService;
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	@Operation(summary = "Criar aluno com parametros.", description = "Retorna uma mensagem.")
	@PostMapping(path = "/alunos/param")
	public ResponseEntity<String> createString(@RequestParam String nome, @RequestParam String email) {
		
		log.info("createString( " + nome + ", " + email + " )");
		
		try {
			Aluno a = new Aluno(nome, email);
	
			alunoService.save(a);

			return new ResponseEntity<>("Aluno criado com sucesso!", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}


	@Operation(summary = "Criar aluno com objeto.", description = "Retorna o objeto criado.")
	@PostMapping(path = "/alunos")
	public ResponseEntity<AlunoDTO> create(@RequestBody AlunoDTO alunoDTO) {
		
		log.info("create( " + alunoDTO + " )");

		try {
			Aluno a = alunoService.save(new Aluno(alunoDTO.nome(), alunoDTO.email()));
			
			return new ResponseEntity<>(AlunoDTO.from(a), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	
	

	@Operation(summary = "Atualizar aluno com objeto.", description = "Retorna uma mensagem.")
	@PutMapping("/alunos/{id}")
	public ResponseEntity<String> update(@RequestBody AlunoDTO alunoDTO, @PathVariable Integer id) {
		
		log.info("update( " + alunoDTO + ", Id " + id + " )");

		Optional<Aluno> alunoData = alunoService.findById(id);
		
		if (alunoData.isPresent()) {
			Aluno a = alunoData.get();
			a.setNome(alunoDTO.nome());
			a.setEmail(alunoDTO.email());
			
			alunoService.save(a);

			return new ResponseEntity<>("Aluno alterado com sucesso!", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Não foi possível encontrar o Aluno.", HttpStatus.NOT_FOUND);
		}
	}
	

	@Operation(summary = "Exclui um aluno por Id.", description = "Retorna uma mensagem.")
	@DeleteMapping("/alunos/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id) {
		
		log.info("delete( Id " + id + " )");

		try {
			alunoService.deleteById(id);				
						
			return new ResponseEntity<>("Aluno excluído com sucesso!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Não foi possível excluir o Aluno.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

	@Operation(summary = "Recuperar alunos.", description = "Retorna uma coleção de alunos.")
	@GetMapping("/alunos")
	public @ResponseBody Iterable<Aluno> getAll() {
		
		log.info("getAll()");
		
		return alunoRepository.findAll();
	}

}
