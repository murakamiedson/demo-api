package deso.teste.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import deso.teste.dto.AlunoDTO;
import deso.teste.model.Aluno;
import deso.teste.model.repository.AlunoRepository;

@RequestMapping(path = "/aluno") // This means URL's start with /teste (after Application path)
@RestController
public class AlunoController {

	@Autowired // This means to get the bean called userRepository
	private AlunoRepository alunoRepository;

	@PostMapping(path = "/add") // Map ONLY POST Requests
	public @ResponseBody String addNewUser(@RequestParam String nome, @RequestParam String email) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request

		Aluno a = new Aluno();
		a.setNome(nome);
		a.setEmail(email);
		alunoRepository.save(a);
		
		return "Saved";
	}

	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable(name = "id") Integer id) {

		Optional<Aluno> aluno = alunoRepository.findById(id);
		alunoRepository.delete(aluno.get());

		return "Deleted";
	}

	@PutMapping("/update/{id}")
	public String update(@RequestBody AlunoDTO dto, @PathVariable(value = "id") Integer id) {

		Aluno aluno = AlunoDTO.alunoFromDTO(dto);
		alunoRepository.save(aluno);
		
		return "Updated";
	}

	@GetMapping(path = "/all")
	public @ResponseBody Iterable<Aluno> getAllAlunos() {
		// This returns a JSON or XML with the users
		return alunoRepository.findAll();
	}

}
