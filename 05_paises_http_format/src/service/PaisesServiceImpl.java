package service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import locator.StreamLocator;
import model.Pais;

public class PaisesServiceImpl implements PaisesService {

	@Override
	public List<String> continentes() {
		return StreamLocator.streamPaises().map(Pais::getContinente).distinct().sorted().toList();
	}

	@Override
	public List<Pais> paisesPorContinente(String continente) {
		return StreamLocator.streamPaises().filter(p -> p.getContinente().equals(continente))
				.sorted(Comparator.comparing(Pais::getName)).toList();
	}

	@Override
	public Optional<Pais> paisPorNombre(String pais) {
		return StreamLocator.streamPaises().filter(p -> p.getName().equals(pais)).findFirst();
	}

}
