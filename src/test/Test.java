package test;


import almacenGranate.Ubicacion;
import almacenGranate.Cliente;
import almacenGranate.Contacto;

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
			Cliente cliente = new Cliente(1, contacto, "Godirio", "Sebastian", 42472667);
			
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
		Ubicacion ubicacionComercio = new Ubicacion(20.1542,30.5468);
		Contacto contactoComercio = new Contacto("almacen_granate@gmail.com","1134274702",ubicacionComercio);
		Comercio comercio = new Comercio(0, contactoComercio, "almacen granate", 30111111118L, 150.0, 50.0, 3, 15, 20);
				
		// Lista para almacenar todos los articulos del comercio
		List <Articulo> listaArticulos = new ArrayList<Articulo>();
				
		// Lista para almacenar los clientes que van comprando en el comercio.
		List <Cliente> listaClientes = new ArrayList<Cliente>();
				
		listaArticulos.add(new Articulo(listaArticulos.size(),"Huevos","11111",20.0));
		listaArticulos.add(new Articulo(listaArticulos.size(),"Pescado","21111",80.0));
		listaArticulos.add(new Articulo(listaArticulos.size(),"Arroz","13111",30.0));
		listaArticulos.add(new Articulo(listaArticulos.size(),"Pan lactal","11311",100.0));
		listaArticulos.add(new Articulo(listaArticulos.size(),"Manteca","11211",90.0));
				
		Cliente clienteUno = new Cliente(listaClientes.size(),new Contacto("cliente_uno@gmail.com","1134274702",
							new Ubicacion(20.0,45.0)),"Garcia","Camila",43182591);
		
		listaClientes.add(clienteUno);
			
		comercio.agregarCarrito(LocalDate.parse("2020-09-09"), LocalTime.parse("18:09"), false, 0, clienteUno, null);
				
		Carrito carritoUno = comercio.traerCarrito(comercio.getLstCarrito().get(0).getId());
				
		System.out.println(carritoUno);
				
		comercio.agregarDiaRetiro(3,LocalTime.parse("16:00"), LocalTime.parse("20:00"),30);
				
		System.out.println(comercio.generarAgenda(LocalDate.parse("2020-09-02")));
				
		// carrito -> agregar items. 
	
		
	}

}
