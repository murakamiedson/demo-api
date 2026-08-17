package demo.aluno.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.aluno.model.Aluno;
import demo.aluno.model.AlunoRepository;

@Service
public class AlunoService {
	
	@Autowired
	private AlunoRepository alunoRepository;

	public Iterable<Aluno> getAll() {
		
		return alunoRepository.findAll();
	}
	
	
	

}
