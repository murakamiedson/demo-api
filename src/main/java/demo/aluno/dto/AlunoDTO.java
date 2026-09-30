package demo.aluno.dto;

import demo.aluno.model.Aluno;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AlunoDTO {

	private Integer id;
	@NotBlank(message = "O nome do produto é obrigatório!")
	private String nome;
	@Email(message = "Email inválido!")
    @NotBlank(message = "O email é obrigatório!")
	private String email;


	public AlunoDTO(Integer id, String nome, String email) {
		this.id = id;
		this.nome = nome;
		this.email = email;
	}

	public static AlunoDTO from(Aluno aluno) {
		return new AlunoDTO(aluno.getId(), aluno.getNome(), aluno.getEmail());
	}
}
