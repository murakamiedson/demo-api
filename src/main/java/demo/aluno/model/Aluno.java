package demo.aluno.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Aluno {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private @Nullable Integer id;

	@NotBlank(message = "O nome do aluno é obrigatório!")
	private String nome;
	@NotBlank @Email
	private String email;
	
	public Aluno() {}
	
	public Aluno(String nome, String email) {
		super();
		this.nome = nome;
		this.email = email;
	}

	public Aluno(Integer id, String nome, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
	}
	
	
}
