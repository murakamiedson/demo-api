package demo.aluno.dto;

import demo.aluno.model.Aluno;

public record AlunoDTO(Integer id, String nome, String email) {

	public static AlunoDTO from(Aluno aluno) {
		return new AlunoDTO(aluno.getId(), aluno.getNome(), aluno.getEmail());
	}
}
