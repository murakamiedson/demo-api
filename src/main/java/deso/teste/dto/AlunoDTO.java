package deso.teste.dto;

import deso.teste.model.Aluno;

public record AlunoDTO(Integer id, String nome, String email) {

	public static AlunoDTO DTOfromAluno(Aluno aluno) {
		return new AlunoDTO(aluno.getId(), aluno.getNome(), aluno.getEmail());
	}
	
	public static Aluno alunoFromDTO(AlunoDTO dto) {
		return new Aluno(dto.id(), dto.nome(), dto.email());
	}
}
