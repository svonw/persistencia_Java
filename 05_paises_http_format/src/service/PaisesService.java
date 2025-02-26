package service;

import java.util.List;
import java.util.Optional;

import model.Pais;

public interface PaisesService {
	List<String> continentes();

	List<Pais> paisesPorContinente(String continente);

	Optional<Pais> paisPorNombre(String pais);
}
