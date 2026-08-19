package demo.aluno.service;

import java.util.Optional;

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

	public Aluno save(Aluno a) {
		return alunoRepository.save(a);
	}

	public Optional<Aluno> findById(Integer id) {
		return alunoRepository.findById(id);
	}

	public void deleteById(Integer id) throws Exception {
		try {
			if (alunoRepository.findById(id).isEmpty()) {
				throw new Exception("Aluno inexistente.");
			}
			alunoRepository.deleteById(id);		
		}
		catch (Exception e) {
			throw e;
		}
	}
	
	
	

}
