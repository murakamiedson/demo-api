package demo.aluno.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import demo.aluno.model.Aluno;
import demo.aluno.model.AlunoRepository;
import lombok.extern.log4j.Log4j2;

@Log4j2
@ExtendWith(MockitoExtension.class)
class AlunoServiceTest {

	public static final Aluno ALUNO_VALIDO = new Aluno("Murakami", "murakami@ifsp.edu.br");
	public static final Aluno ALUNO_INVALIDO = new Aluno("", "murakami.ifsp.edu.br");

	@InjectMocks
	private AlunoService alunoService;

	@Mock
	private AlunoRepository alunoRepository;

	@Test
	void criarAluno_ComDadosValidos_RetornaAluno() {

		when(alunoRepository.save(ALUNO_VALIDO)).thenReturn(ALUNO_VALIDO);

		Aluno a = alunoService.save(ALUNO_VALIDO);

		assertSame(a, ALUNO_VALIDO);
		assertThat(a).isEqualTo(ALUNO_VALIDO);
	}

	@Test
	void criarAluno_ComDadosInvalidos_ThrowsException() {

		when(alunoRepository.save(ALUNO_INVALIDO)).thenThrow(RuntimeException.class);

		assertThatThrownBy(() -> alunoService.save(ALUNO_INVALIDO)).isInstanceOf(RuntimeException.class);
	}

	@Test
	void excluirAluno_ComIdExistente() {

		assertThatCode(() -> alunoService.deleteById(1));
	}

	@Test
	void excluirAluno_ComIdInexistente_ThrowException() {

		doThrow(new RuntimeException()).when(alunoRepository).deleteById(99);

		assertThatThrownBy(() -> alunoService.deleteById(99)).isInstanceOf(RuntimeException.class);
	}

	@Test
	void chamar_getAll() {

		List<Aluno> alunos = new ArrayList<>();
		alunos.add(ALUNO_INVALIDO);
		alunos.add(ALUNO_VALIDO);
		alunos.add(ALUNO_VALIDO);

		when(alunoRepository.findAll()).thenReturn(alunos);

		List<Aluno> result = (List<Aluno>) alunoService.getAll();
		log.info(result.size());

		assertThat(result).isNotEmpty();
		assertThat(result.size()).isEqualTo(3);
		assertSame(result.size(), 3);
		assertThat(result).hasSize(3);
		assertThat(result.get(0).equals(ALUNO_INVALIDO));

	}

}
