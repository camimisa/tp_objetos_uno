package test;

import almacenGranate.Ubicacion;
import almacenGranate.Cliente;
import almacenGranate.Contacto;
import almacenGranate.Entrega;
import almacenGranate.Envio;
import almacenGranate.RetiroLocal;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import almacenGranate.Articulo;
import almacenGranate.Carrito;
import almacenGranate.Comercio;

public class Test {


	public static void main(String[] args) throws Exception {
		
		// Test de usuarios
		
		String separacionPrueba = "\n-------------------------------------\n";
		
		Ubicacion ubicacion = new Ubicacion(168.5,850.21);
		
		try {
			Contacto contacto = new Contacto("Seba@seba.com", "1134274702", ubicacion);
			Cliente cliente = new Cliente(1, contacto, "Godirio", "Sebastian", 42472667L);
			
			/* ###Pruebas de excepciones###
			 			
			Contacto contactoMailErroneo = new Contacto("seba.com", "1134274702", ubicacion);
			Cliente clienteMailErroneo = new Cliente(2, contactoMailErroneo, "Godirio", "Sebastian", 42472667);
			
			Contacto contactoCelularErroneo = new Contacto("seba@seba.com", "11342", ubicacion);
			Cliente clienteCelularErroneo = new Cliente(3, contactoCelularErroneo, "Godirio", "Sebastian", 42472667);
			
			Contacto contactoDniErroneo = new Contacto("seba@seba.com", "1134274702", ubicacion);
			Cliente clienteDniErroneo = new Cliente(4, contactoDniErroneo, "Godirio", "Sebastian", 424667);
			
			Contacto contactoNombreInvalido = new Contacto("Seba@seba.com", "1134274702", ubicacion);
			Cliente clienteNombreInvalido = new Cliente(5, contactoNombreInvalido, "Godirio", "--Sebastian", 42472667);
			
			System.out.println(clienteMailErroneo);	
			System.out.println(clienteCelularErroneo);
			System.out.println(clienteDniErroneo);
			System.out.println(contactoNombreInvalido);
			*/
			
			System.out.println(cliente);
			System.out.println(cliente.traerUbicacion());
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
					
		// Creacion del comercio.
		Ubicacion ubicacionComercio = new Ubicacion(-34.815658, -58.457143);
		Contacto contactoComercio = new Contacto("almacen_granate@gmail.com","1134274702",ubicacionComercio);
		Comercio comercio = new Comercio(0, contactoComercio, "almacen granate", 30111111118L, 150.0, 15.0, 3, 15, 20);
		
		// Agregando los dias de retiro
		
		comercio.agregarDiaRetiro(1, LocalTime.parse("08:00"), LocalTime.parse("18:00"), 30);
		comercio.agregarDiaRetiro(2, LocalTime.parse("08:00"), LocalTime.parse("18:00"), 10);
		comercio.agregarDiaRetiro(3, LocalTime.parse("08:00"), LocalTime.parse("18:00"), 90);
		comercio.agregarDiaRetiro(4, LocalTime.parse("08:00"), LocalTime.parse("18:00"), 60);
		comercio.agregarDiaRetiro(5, LocalTime.parse("08:00"), LocalTime.parse("18:00"), 100);
		
		// Lista para almacenar los clientes que van comprando en el comercio.
		List <Cliente> listaClientes = new ArrayList<Cliente>();
				
		comercio.agregarArticulo("Cafe", "7613035068391", 20.0);
		comercio.agregarArticulo("Pescado", "8456789012345", 80.0);
		comercio.agregarArticulo("Arroz", "7613088888397", 30.0);
		comercio.agregarArticulo("Pan lactal", "7613123468393", 100.0);
		comercio.agregarArticulo("Manteca", "7613025036393", 90.0);
				
		Cliente clienteUno = new Cliente(listaClientes.size(),new Contacto("cliente_uno@gmail.com","1134274702",
							new Ubicacion(-34.803349, -58.448702)),"Garcia","Camila",43182591L);
		
		listaClientes.add(clienteUno);
		
		// Probando entrega de tipo envio
		Entrega entregaEnvio = new Envio(0,LocalDate.parse("2020-09-20"),true,LocalTime.parse("18:00"),
				LocalTime.parse("20:00"), clienteUno.getContacto().getUbicacion());
		
		// Probando entrega de tipo envio
		Entrega entregaRetiroLocal = new RetiroLocal(0,LocalDate.parse("2020-09-09"),true);
		
		comercio.agregarCarrito(LocalDate.parse("2020-09-09"), LocalTime.parse("18:09"), 0, clienteUno, entregaEnvio);
				
		Carrito carritoUno = comercio.traerCarrito(comercio.getLstCarrito().get(0).getId());
				
		 carritoUno.agregar(comercio.traerArticulo(1), 2);
		 carritoUno.agregar(comercio.traerArticulo(2), 3);
		 carritoUno.agregar(comercio.traerArticulo(3), 5);
		 ///*ERROR. cantidad no valida:*/ carritoUno.agregar(comercio.traerArticulo(3), -4);
		 
		 // al imprimir todo el contenido del carrito uno se cierra el pedido.
		 System.out.println(carritoUno);
		 

		 // prueba de modificar cosas despues de que se cerro el pedido:
		 /*
		 carritoUno.agregar(listaArticulos.get(2), 3);
		 carritoUno.agregar(comercio.traerArticulo(4), 3);		 
		 System.out.println(separacionPrueba);		 
		 carritoUno.eliminarItemCarrito(comercio.traerArticulo(4));		 
		 carritoUno.modificarItemCarrito(2, 7);
		 carritoUno.eliminarItemCarrito(listaArticulos.get(0));
		 */

		 //carritoUno.eliminarItemCarrito(listaArticulos.get(0));	 		 
		//comercio.eliminarArticulo(2);
		//System.out.println(comercio.traerArticulo(2));
		 
		Entrega entregaRetiroLocal2 = new RetiroLocal(1,LocalDate.parse("2020-09-17"),false); 
		comercio.agregarCarrito(LocalDate.parse("2020-09-16"), LocalTime.parse("19:00"), 0, clienteUno, entregaRetiroLocal2);
			
		Carrito carritoDos = comercio.traerCarrito(comercio.getLstCarrito().get(1).getId());
		
		carritoDos.agregar(comercio.traerArticulo(1), 2);
		carritoDos.agregar(comercio.traerArticulo(2), 3);
		carritoDos.agregar(comercio.traerArticulo(3), 5);
		
		System.out.println(carritoDos);
	}

}
