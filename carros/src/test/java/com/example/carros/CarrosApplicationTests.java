package com.example.carros;

import com.example.carros.domain.Carro;
import com.example.carros.domain.CarroService;
import com.example.carros.domain.dto.CarroDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class CarrosApplicationTests {

	@Autowired
	private CarroService service;

	@Test
	void test1() {

		Carro carro = new Carro();
		carro.setTipo("Esportivo");
		carro.setNome("Ferrari Ventura");

		CarroDTO c = service.insert(carro);
		assertNotNull(c);

		Long id = c.getId();
		assertNotNull(id);

		Optional<CarroDTO> op =  service.getCarroById(id);
		assertTrue(op.isPresent());

		c = op.get();
		assertEquals("Ferrari Ventura",c.getNome());
		assertEquals("Esportivo",c.getTipo());

		service.delete(id);

		assertFalse(service.getCarroById(id).isPresent());

	}

	@Test
	void test2() {
	}

}
