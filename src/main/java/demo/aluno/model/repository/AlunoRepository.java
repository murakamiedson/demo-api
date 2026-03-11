package demo.aluno.model.repository;


import org.springframework.data.repository.CrudRepository;

import demo.aluno.model.Aluno;

// This will be AUTO IMPLEMENTED by Spring into a Bean called alunoRepository
// CRUD refers Create, Read, Update, Delete

public interface AlunoRepository extends CrudRepository<Aluno, Integer> {

}
