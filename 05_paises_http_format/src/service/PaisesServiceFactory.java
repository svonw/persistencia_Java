package service;

public class PaisesServiceFactory {
	public static PaisesService getPaisesService() {
		return new PaisesServiceImpl();
	}
}
