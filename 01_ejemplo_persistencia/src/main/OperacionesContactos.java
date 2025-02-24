package main;

import service.ContactoService;

public class OperacionesContactos {

	public static void main(String[] args) {
		ContactoService service = new ContactoService();

//		recoger contacto
//		Contacto contacto=service.buscarContacto(5);
//		System.out.println("Nombre: "+contacto.getNombre());
//		System.out.println("Email: "+contacto.getEmail());
//		System.out.println("Edad: "+contacto.getEdad());

		// guardar contacto
//		Contacto contacto=new Contacto(0,"contacto2 JPA","jpa2@gmail.com",44);
//		service.altaContacto(contacto);

//		List <Contacto> contactos=service.recuperarTodos();
//		contactos.forEach(c->System.out.println(c.getNombre()));
//		
//
//		Optional<Contacto> contacto = service.buscarContactoEmail("jpamal@gmail.com");
//		contacto.ifPresentOrElse(c -> System.out.println(c.getNombre()),
//				() -> System.out.println("contacto no existente"));

		// ELIMINAR POR NOMBRE

		service.eliminarContacto("contacto jpa");

	}

}
