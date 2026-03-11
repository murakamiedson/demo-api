package deso.teste.dto;

import deso.teste.model.Aluno;

public record AlunoDTO(Integer id, String nome, String email) {

	public static AlunoDTO from(Aluno aluno) {
		return new AlunoDTO(aluno.getId(), aluno.getNome(), aluno.getEmail());
	}
}
