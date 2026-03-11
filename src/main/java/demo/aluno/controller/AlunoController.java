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
import demo.aluno.model.repository.AlunoRepository;

@RequestMapping(path = "/demo-api") // This means URL's start with /teste-api
@RestController
public class AlunoController {

	@Autowired // This means to get the bean called userRepository
	private AlunoRepository alunoRepository;

	/* Metodo que usa strings como parametros */
	@PostMapping("/alunoString") // Map ONLY POST Requests
	public @ResponseBody String add(@RequestParam String nome, @RequestParam String email) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		Aluno a = new Aluno();
		a.setNome(nome);
		a.setEmail(email);
		alunoRepository.save(a);

		return "Saved";
	}

	/* Metodo que usa a dto para esconder a entidade */
	@PostMapping("/alunoDTO")
	public ResponseEntity<AlunoDTO> createTutorial(@RequestBody AlunoDTO alunoDTO) {
		try {
			Aluno a = alunoRepository.save(new Aluno(alunoDTO.nome(), alunoDTO.email()));
			return new ResponseEntity<>(AlunoDTO.from(a), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	/* Metodo que usa a entidade */
	@PostMapping("/aluno")
	public ResponseEntity<Aluno> createTutorial(@RequestBody Aluno aluno) {
		try {
			Aluno a = alunoRepository.save(new Aluno(aluno.getNome(), aluno.getEmail()));
			return new ResponseEntity<>(a, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("/aluno/{id}")
	public ResponseEntity<Aluno> update(@RequestBody Aluno aluno, @PathVariable Integer id) {

		Optional<Aluno> alunoData = alunoRepository.findById(id);
		if (alunoData.isPresent()) {
			Aluno a = alunoData.get();
			a.setNome(aluno.getNome());
			a.setEmail(aluno.getEmail());

			return new ResponseEntity<>(alunoRepository.save(a), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/aluno/{id}")
	public ResponseEntity<HttpStatus> delete(@PathVariable Integer id) {

		try {
			alunoRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	

	@GetMapping("/aluno/all")
	public @ResponseBody Iterable<Aluno> getAll() {
		// This returns a JSON or XML with the users
		return alunoRepository.findAll();
	}

}
