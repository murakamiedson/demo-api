package deso.teste.controller;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import deso.teste.dto.AlunoDTO;

@RestController
public class SaudacoesController {

	private static final String template = "Hello, %s!";
	private final AtomicInteger counter = new AtomicInteger();

	@GetMapping("/saudacoes")
	public AlunoDTO greeting(@RequestParam(defaultValue = "World") String nome) {
		return new AlunoDTO(counter.incrementAndGet(), template.formatted(nome), null);
	}
}
