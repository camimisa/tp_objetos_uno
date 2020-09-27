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
		
		Ubicacion ubicacion = new Ubicacion(168.5,850.21);
		
		try {
			Contacto contacto = new Contacto("Seba@seba.com", "1134274702", ubicacion);
			Cliente cliente = new Cliente(1, contacto, "Godirio", "Sebastian Leonel", 42472667L,'M');
			
			/* ###Pruebas de excepciones###
			 			
			Contacto contactoMailErroneo = new Contacto("seba.com", "1134274702", ubicacion);
			Cliente clienteMailErroneo = new Cliente(2, contactoMailErroneo, "Godirio", "Sebastian", 42472667);
			
			Contacto contactoCelularErroneo = new Contacto("seba@seba.com", "11342", ubicacion);
			Cliente clienteCelularErroneo = new Cliente(3, contactoCelularErroneo, "Godirio", "Sebastian", 42472667);
			
			Contacto contactoDniErroneo = new Contacto("seba@seba.com", "1134274702", ubicacion);
			Cliente clienteDniErroneo = new Cliente(4, contactoDniErroneo, "Godirio", "Sebastian", 424667);
			
			Contacto contactoNombreInvalido = new Contacto("Seba@seba.com", "1134274702", ubicacion);
			Cliente clienteNombreInvalido = new Cliente(5, contactoNombreInvalido, "Godirio", "--Sebastian", 42472667);
			
			Contacto contactoSexoInvalido = new Contacto("Seba@seba.com", "1134274702", ubicacion);
			Cliente clienteSexoInvalido = new Cliente(5, contactoSexoInvalido, "Godirio", "Sebastian", 42472667,'A');
			
			System.out.println(clienteMailErroneo);	
			System.out.println(clienteCelularErroneo);
			System.out.println(clienteDniErroneo);
			System.out.println(contactoNombreInvalido);
			System.out.println(clienteSexoInvalido);
			*/
			
			System.out.println(cliente);
			System.out.println(cliente.traerUbicacion());
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
				
		// 											Creacion del comercio.
		
		Comercio comercio = null;
		
		try {
			Ubicacion ubicacionComercio = new Ubicacion(-34.815658, -58.457143);
			Contacto contactoComercio = new Contacto("almacen_granate@gmail.com","1134274702",ubicacionComercio);
			comercio = new Comercio(0, contactoComercio, "almacen granate", 30111111118L, 150.0, 15.0, 3, 15, 20);
			
			//Cuit erroneo - cantidad menor a 11 -
			//comercio.setCuit(30256347L);
			//Cuit erroneo - ultimo digito incorrecto -
			//comercio.setCuit(30111111119L);
			
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		
		// Agregando los dias de retiro
		try {
			comercio.agregarDiaRetiro(1, LocalTime.parse("08:00"), LocalTime.parse("18:00"), 30);
			comercio.agregarDiaRetiro(2, LocalTime.parse("08:00"), LocalTime.parse("18:00"), 10);
			
			// agregando dia que ya existe:
			//comercio.agregarDiaRetiro(2, LocalTime.parse("08:00"), LocalTime.parse("20:00"), 5);
			// agregando dia con un numero de dia incorrecto:
			//comercio.agregarDiaRetiro(0, LocalTime.parse("08:00"), LocalTime.parse("18:00"), 90);

			comercio.agregarDiaRetiro(3, LocalTime.parse("08:00"), LocalTime.parse("18:00"), 90);
			comercio.agregarDiaRetiro(4, LocalTime.parse("08:00"), LocalTime.parse("18:00"), 60);
			comercio.agregarDiaRetiro(5, LocalTime.parse("08:00"), LocalTime.parse("18:00"), 100);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println(comercio.getLstDiaRetiro());
				
		comercio.agregarArticulo("Cafe", "7613035068391", 20.0);
		comercio.agregarArticulo("Pescado", "8456789012345", 80.0);
		comercio.agregarArticulo("Arroz", "7613088888397", 30.0);
		comercio.agregarArticulo("Pan lactal", "7613123468393", 100.0);
		comercio.agregarArticulo("Manteca", "7613025036393", 90.0);
		comercio.agregarArticulo("Te", "7790150250327", 50.0);
		comercio.agregarArticulo("Te Limon", "7790150240274", 70.0);
		comercio.agregarArticulo("Mermelada durazno", "7795184119756", 100.0);
		comercio.agregarArticulo("Polenta", "7790580660000", 40.0);
		comercio.agregarArticulo("Nesquik", "7613034453600", 100.0);
		comercio.agregarArticulo("Edulcorante", "7794940000826", 150.0);
		comercio.agregarArticulo("Leche en polvo", "7796613019784", 200.0);
		comercio.agregarArticulo("Galletas arroz", "7798142710019", 40.0);
		comercio.agregarArticulo("Levite pera", "7798062548655", 60.0);
		
		//Pruebas CRUD de lista de articulo
		
		//ARTICULO CON CODIGO DE BARRAS ERRONEO: comercio.agregarArticulo("Cafe", "76125036393", 20);
		//ARTICULO CON PRECIO NEGATIVO: comercio.agregarArticulo("Manteca", "7613025036393", -45);
		
		//System.out.println(comercio.traerArticulo(1));
		//comercio.eliminarArticulo(1);
		//System.out.println(comercio.traerArticulo(1));
		
		//System.out.println(comercio.getLstArticulo());
				
		Cliente clienteUno = new Cliente(comercio.getIdCliente(),new Contacto("cliente_uno@gmail.com","1134274702",
								new Ubicacion(-34.803349, -58.448702)),"Garcia Misa","Camila",43182591L,'f');

		
		//listaClientes.add(clienteUno);
		
		// TODO: PRUEBAS DE ENVIO Y RETIRO -- las fechas deben ser el mismo dia o dias posteriores a la compra del carrito
		
		try {
			
			Entrega entregaEnvioPrueba = new Envio(0,LocalDate.parse("0000-09-20"),true,LocalTime.parse("18:00"),
					LocalTime.parse("20:00"), clienteUno.getContacto().getUbicacion());
			
			Entrega entregaRetiroLocalPrueba = new RetiroLocal(0,LocalDate.parse("2020-09-09"),true);
			
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		// Probando entrega de tipo envio
		Entrega entregaEnvio = new Envio(0,LocalDate.parse("2020-09-20"),true,LocalTime.parse("18:00"),
				LocalTime.parse("20:00"), clienteUno.getContacto().getUbicacion());
		
		// Probando entrega de tipo retiro local
		Entrega entregaRetiroLocal = new RetiroLocal(0,LocalDate.parse("2020-09-09"),true);
		
		


		comercio.agregarCarrito(LocalDate.parse("2020-09-09"), LocalTime.parse("18:09"), clienteUno);

		
		Carrito carritoUno = comercio.traerCarrito(comercio.getLstCarrito().get(0).getId());
		carritoUno.setEntregaRetiroLocal(LocalDate.parse("2020-09-17"), false);
		
		carritoUno.agregar(comercio.traerArticulo(1), 2);
		carritoUno.agregar(comercio.traerArticulo(2), 3);
		carritoUno.agregar(comercio.traerArticulo(3), 5);
		carritoUno.agregar(comercio.traerArticulo(6), 1);
		carritoUno.agregar(comercio.traerArticulo(6), 4);
		carritoUno.eliminarItemCarrito(comercio.traerArticulo(1));
		carritoUno.modificarItemCarrito(2, 7);
		
		///*ERROR. cantidad no valida:*/ carritoUno.agregar(comercio.traerArticulo(3), -4);
		// /*Eliminar itemCarrito con un articulo inexistente. */ carritoUno.eliminarItemCarrito(comercio.traerArticulo(17)); 
		
		// al imprimir todo el contenido del carrito uno se cierra el pedido.
		System.out.println(carritoUno);
		 

		 // prueba de modificar cosas despues de que se cerro el pedido:
		 /*
		 carritoUno.agregar(comercio.traerArticulo(2), 3);
		 carritoUno.agregar(comercio.traerArticulo(4), 3);		 	 
		 carritoUno.eliminarItemCarrito(comercio.traerArticulo(4));		 
		 carritoUno.eliminarItemCarrito(comercio.traerArticulo(0));
		 */
		
		
		Cliente clienteDos = new Cliente(comercio.getIdCliente(),new Contacto("cliente_dos@gmail.com","1134274555",
					new Ubicacion(-34.814627, -58.469636)),"Granda","Damian",43182591L,'m');
		
		 try {
			 // fecha invalida (fecha anterior al inicio del carrito)
			 //Entrega entregaEnvio2 = new Envio(clienteDos.getId(), LocalDate.parse("2020-09-15"), true, LocalTime.parse("18:09"), LocalTime.parse("00:00"), ubicacion);
			 Entrega entregaEnvio2 = new Envio(clienteDos.getId(), LocalDate.parse("2020-09-20"), true, LocalTime.parse("18:09"), LocalTime.parse("00:00"), clienteDos.getContacto().getUbicacion());
			 //Entrega entregaRetiroLocal2 = new RetiroLocal(1,LocalDate.parse("2020-09-17"),true); 
			 comercio.agregarCarrito(LocalDate.parse("2020-09-16"), LocalTime.parse("19:00"), clienteDos, entregaEnvio2);
				
			Carrito carritoDos = comercio.traerCarrito(comercio.getLstCarrito().get(1).getId());
			
			carritoDos.agregar(comercio.traerArticulo(1), 2);
			carritoDos.agregar(comercio.traerArticulo(2), 3);
			carritoDos.agregar(comercio.traerArticulo(3), 5);
			carritoDos.agregar(comercio.traerArticulo(13), 5);
			carritoDos.agregar(comercio.traerArticulo(4), 1);
			carritoDos.agregar(comercio.traerArticulo(5), 1);
			carritoDos.agregar(comercio.traerArticulo(6), 1);
			carritoDos.agregar(comercio.traerArticulo(7), 1);
			carritoDos.agregar(comercio.traerArticulo(8), 1);
			carritoDos.agregar(comercio.traerArticulo(9), 1);
			System.out.println(carritoDos);
			
		 } catch(Exception e) {
			 System.out.println(e);
		 }
		 
		 System.out.println("\nAgenda de turnos:\n"+ comercio.generarAgenda(carritoUno.getEntrega().getFecha()));
	}

}
