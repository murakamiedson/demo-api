package demo.aluno.dto;

import demo.aluno.model.Aluno;

public record AlunoDTO(Integer id, String nome, String email) {

	/* Metodos para mapeamento de objetos */
	
	public static AlunoDTO from(Aluno aluno) {
		return new AlunoDTO(aluno.getId(), aluno.getNome(), aluno.getEmail());
	}	
}
