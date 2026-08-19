package demo.aluno.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity // This tells Hibernate to make a table out of this class
public class Aluno {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private @Nullable Integer id;

	private String nome;
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
