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
			Cliente cliente = new Cliente(contacto, "Godirio", "Sebastian Leonel", 42472667L,'M');
			
			// ###Pruebas de excepciones###
		try	{ 			
			Contacto contactoMailErroneo = new Contacto("seba.com", "1134274702", ubicacion);
			Cliente clienteMailErroneo = new Cliente(contactoMailErroneo, "Godirio", "Sebastian", 42472667,'m');
			System.out.println(clienteMailErroneo);	
		}
		catch(Exception e) {
				System.out.println(e.getMessage());
			}
		try {
			Contacto contactoCelularErroneo = new Contacto("seba@seba.com", "11342", ubicacion);
			Cliente clienteCelularErroneo = new Cliente(contactoCelularErroneo, "Godirio", "Sebastian", 42472667,'m');
			System.out.println(clienteCelularErroneo);
		}
		catch(Exception e) {
				System.out.println(e.getMessage());
			}
		
		try {
			Contacto contactoDniErroneo = new Contacto("seba@seba.com", "1134274702", ubicacion);
			Cliente clienteDniErroneo = new Cliente(contactoDniErroneo, "Godirio", "Sebastian", 424667,'m');
			System.out.println(clienteDniErroneo);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			Contacto contactoNombreInvalido = new Contacto("Seba@seba.com", "1134274702", ubicacion);
			Cliente clienteNombreInvalido = new Cliente(contactoNombreInvalido, "Godirio", "--Sebastian", 42472667,'m');
			System.out.println(contactoNombreInvalido);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		try {
			Contacto contactoSexoInvalido = new Contacto("Seba@seba.com", "1134274702", ubicacion);
			Cliente clienteSexoInvalido = new Cliente(contactoSexoInvalido, "Godirio", "Sebastian", 42472667,'A');
			System.out.println(clienteSexoInvalido);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
			
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
				
		// 											Creacion del comercio.
		
		Comercio comercio = null;
		
		try {
			Ubicacion ubicacionComercio = new Ubicacion(-34.815658, -58.457143);
			Contacto contactoComercio = new Contacto("almacen_granate@gmail.com","1134274702",ubicacionComercio);
			comercio = new Comercio(0, contactoComercio, "almacen granate", 30111111118L, 150.0, 15.0, 3, 5, 10);
			
			try {
			//Cuit erroneo - cantidad menor a 11 -
			comercio.setCuit(30256347L);
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
			try {
			//Cuit erroneo - ultimo digito incorrecto -
			comercio.setCuit(30111111119L);
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
			
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println(comercio);
		// Agregando los dias de retiro
		try {
			comercio.agregarDiaRetiro(1, LocalTime.parse("08:00"), LocalTime.parse("18:00"), 30);
			comercio.agregarDiaRetiro(2, LocalTime.parse("08:00"), LocalTime.parse("18:00"), 10);
			
			try {
			// agregando dia que ya existe:
			comercio.agregarDiaRetiro(2, LocalTime.parse("08:00"), LocalTime.parse("20:00"), 5);
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
			try {
			// agregando dia con un numero de dia incorrecto:
			comercio.agregarDiaRetiro(0, LocalTime.parse("08:00"), LocalTime.parse("18:00"), 90);
			}
			catch(Exception e) {
			System.out.println(e.getMessage());
			}
		
		
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
		comercio.agregarArticulo("Diversion", "1614035068394", 20.0);
		
		//Pruebas CRUD de lista de articulo
		
		try {
		//ARTICULO CON CODIGO DE BARRAS ERRONEO
		comercio.agregarArticulo("Cafe", "76125036393", 20);
		System.out.println(comercio.traerArticulo(1));
		} 	
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		try {
		//ARTICULO CON PRECIO NEGATIVO 
		comercio.agregarArticulo("Manteca", "7613025036393", -45);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		try {
		comercio.eliminarArticulo(14);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		System.out.println(comercio.getLstArticulo());
				
		Cliente clienteUno = new Cliente(new Contacto("cliente_uno@gmail.com","1134274702",
								new Ubicacion(-34.803349, -58.448702)),"Garcia Misa","Camila",43182591L,'f');
		
		comercio.agregarCarrito(LocalDate.now(), LocalTime.parse("18:09"), clienteUno);
		
		Carrito carritoUno = comercio.traerCarrito(comercio.getLstCarrito().get(0).getId());
		carritoUno.setEntregaRetiroLocal(LocalDate.now().plusDays(3), false);
		
		carritoUno.agregar(comercio.traerArticulo(1), 2);
		carritoUno.agregar(comercio.traerArticulo(2), 3);
		carritoUno.agregar(comercio.traerArticulo(3), 5);
		carritoUno.agregar(comercio.traerArticulo(6), 1);
		carritoUno.agregar(comercio.traerArticulo(6), 4);
		carritoUno.eliminarItemCarrito(comercio.traerArticulo(1));
		carritoUno.modificarItemCarrito(2, 7);
		
	
		try{
			///*ERROR. cantidad no valida:*/
			carritoUno.agregar(comercio.traerArticulo(3), -4);
		} 
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		try {
		// /*Eliminar itemCarrito con un articulo inexistente. */ 
		carritoUno.eliminarItemCarrito(comercio.traerArticulo(17)); 
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("\n\nTEST CARRITO 1 (entrega: retiro local) :\nDescuento: ninguno.");
		// al imprimir todo el contenido del carrito uno se cierra el pedido.
		System.out.println(carritoUno);
		 

		 // prueba de modificar cosas despues de que se cerro el pedido:
		 try {
		 carritoUno.agregar(comercio.traerArticulo(2), 3);
		 }
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		 try {
		 carritoUno.agregar(comercio.traerArticulo(4), 3);
		 }
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		 try {
		 carritoUno.eliminarItemCarrito(comercio.traerArticulo(4));
		 }	catch(Exception e) {
				System.out.println(e.getMessage());
			}
		 try {
		 carritoUno.eliminarItemCarrito(comercio.traerArticulo(0));
		 }	catch(Exception e) {
				System.out.println(e.getMessage());
			}
		
		
		Cliente clienteDos = new Cliente(new Contacto("cliente_dos@gmail.com","1134274555",
					new Ubicacion(-34.814627, -58.469636)),"Granda","Damian",43182591L,'m');
		
		 try {
		
			 try {
				// fecha invalida (fecha anterior al inicio del carrito)
			 Entrega entrega2 = new Envio(clienteDos.getId(), LocalDate.parse("2020-09-15"), true, LocalTime.parse("18:09"), LocalTime.parse("00:00"), ubicacion);
			 }
			catch(Exception e) {
					System.out.println(e.getMessage());
				}
			 Entrega entrega2 = new Envio(clienteDos.getId(), LocalDate.now().plusDays(2), true, LocalTime.parse("08:00"), LocalTime.parse("18:00"), clienteDos.getContacto().getUbicacion());
			
			 try {
			 Entrega entrega3 = new RetiroLocal(1,LocalDate.parse("2020-09-17"),true); 
			 }
			catch(Exception e) {
					System.out.println(e.getMessage());
				}
			
			 
			comercio.agregarCarrito(LocalDate.now(), LocalTime.now(), clienteDos, entrega2);
				
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
			System.out.println("TEST CARRITO 2 (entrega: envio) :\nDescuento: efectivo.");
			System.out.println(carritoDos);
			
		 } catch(Exception e) {
			 System.out.println(e);
		 }
		
		Cliente clienteTres = new Cliente(new Contacto("Seba@seba.com", "1134274702",
					new Ubicacion(-34.760117, -58.397096)),"Godirio", "Sebastian Leonel", 42472667L,'M');

		Carrito carritoTres = null;
		try {
			 // Miercoles -> Dia del descuento del comercio.
			comercio.agregarCarrito(LocalDate.parse("2020-09-23"), LocalTime.now(), clienteTres);
				
			carritoTres = comercio.traerCarrito(comercio.getLstCarrito().get(2).getId());
			carritoTres.setEntregaRetiroLocal(carritoTres.getFecha().plusDays(3), false);
			
			carritoTres.agregar(comercio.traerArticulo(10), 2);
			carritoTres.agregar(comercio.traerArticulo(2), 3);
			carritoTres.agregar(comercio.traerArticulo(3), 5);
			carritoTres.agregar(comercio.traerArticulo(13), 5);
			carritoTres.agregar(comercio.traerArticulo(4), 1);
			carritoTres.agregar(comercio.traerArticulo(5), 6);
			carritoTres.agregar(comercio.traerArticulo(11), 4);

			System.out.println("TEST CARRITO 3 (entrega: retirolocal) :\nDescuento: dia descuento comercio.");
			System.out.println(carritoTres);
			
		 } catch(Exception e) {
			 System.out.println(e);
		 }
		
		
		Cliente clienteCuatro = new Cliente(new Contacto("juan@gmail.com", "1155554702",
				new Ubicacion(-34.726595, -58.394265)),"Gonzales Canosa", "Juan Manuel", 41111111L,'M');
		Carrito carritoCuatro = null;
		try {
			 // Miercoles -> Dia del descuento del comercio.
			comercio.agregarCarrito(LocalDate.parse("2020-09-23"), LocalTime.now(), clienteCuatro);
				
			carritoCuatro = comercio.traerCarrito(comercio.getLstCarrito().get(3).getId());
			carritoCuatro.setEntregaRetiroLocal(carritoCuatro.getFecha().plusDays(3), true);
			
			carritoCuatro.agregar(comercio.traerArticulo(13), 2);
			carritoCuatro.agregar(comercio.traerArticulo(1), 3);
			carritoCuatro.agregar(comercio.traerArticulo(3), 5);
			carritoCuatro.agregar(comercio.traerArticulo(7), 5);
			carritoCuatro.agregar(comercio.traerArticulo(4), 1);
			carritoCuatro.agregar(comercio.traerArticulo(0), 6);
			carritoCuatro.agregar(comercio.traerArticulo(11), 4);

			System.out.println("TEST CARRITO 4 (entrega: retirolocal) :\nDescuento: dia descuento comercio Y efectivo.");
			System.out.println(carritoCuatro);
			
		 } catch(Exception e) {
			 System.out.println(e);
		 }
		
		Carrito carritoCinco = null;
		try {
			comercio.agregarCarrito(LocalDate.now(), LocalTime.now(), clienteTres);
				
			carritoCinco = comercio.traerCarrito(comercio.getLstCarrito().get(4).getId());
			carritoCinco.setEntregaEnvio(carritoCinco.getFecha().plusDays(3), false, LocalTime.parse("09:00"), 
						LocalTime.parse("19:00"), clienteTres.getContacto().getUbicacion());
			
			carritoCinco.agregar(comercio.traerArticulo(10), 2);
			carritoCinco.agregar(comercio.traerArticulo(2), 3);
			carritoCinco.agregar(comercio.traerArticulo(3), 5);
			carritoCinco.agregar(comercio.traerArticulo(13), 5);
			carritoCinco.agregar(comercio.traerArticulo(4), 1);
			carritoCinco.agregar(comercio.traerArticulo(5), 6);
			carritoCinco.agregar(comercio.traerArticulo(11), 4);

			System.out.println("TEST CARRITO 5 (entrega: envio) :\nDescuento: ninguno.");
			System.out.println(carritoCinco);
			
		 } catch(Exception e) {
			 System.out.println(e);
		 }
		 
		 System.out.println("\nAgenda de turnos " + LocalDate.now() +" :\n"+ comercio.generarAgenda(carritoUno.getEntrega().getFecha()));
		 System.out.println("\nAgenda de turnos " + carritoTres.getEntrega().getFecha() + ":\n"+ comercio.generarAgenda(carritoTres.getEntrega().getFecha()));
	}

}