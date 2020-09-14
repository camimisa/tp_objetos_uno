package almacenGranate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Carrito {

	private int id;
	private LocalDate fecha;
	private LocalTime hora;
	private boolean cerrado;
	private double descuento;
	private Cliente cliente;
	private List<ItemCarrito> lstItemCarrito;
	private Entrega entrega;

	public Carrito(int id, LocalDate fecha, LocalTime hora, boolean cerrado, double descuento, Cliente cliente, Entrega entrega) {
		this.id = id;
		this.fecha = fecha;
		this.hora = hora;
		this.cerrado = cerrado;
		this.descuento = 0;
		this.cliente = cliente;
		this.lstItemCarrito = new ArrayList<ItemCarrito>();
		this.entrega = entrega;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public boolean getCerrado() {
		return cerrado;
	}

	public void setCerrado(boolean cerrado) {
		this.cerrado = cerrado;
	}

	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		if(descuento >= 0) 
			this.descuento = descuento;
		else 
			descuento = 0;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<ItemCarrito> getLstItemCarrito() {
		return lstItemCarrito;
	}

	public void setLstItemCarrito(List<ItemCarrito> lstItemCarrito) {
		this.lstItemCarrito = lstItemCarrito;
	}

	public Entrega getEntrega() {
		return entrega;
	}

	public void setEntrega(Entrega entrega) {
		this.entrega = entrega;
	}

	@Override
	public String toString() {
		return "Carrito [id=" + id + ", fecha=" + fecha + ", hora=" + hora + ", cerrado=" + cerrado + ", descuento="

				+ descuento + ", cliente=" + cliente + ", itemCarrito=" + lstItemCarrito + ", entrega=" + entrega + "]";
	}
	
	public boolean equals(Carrito c) {
		return ((id==c.getId())&&(fecha==c.getFecha())&&(hora==c.getHora())&&(cerrado==c.getCerrado()) &&(descuento==c.getDescuento())
				&& (cliente==c.getCliente())&& (lstItemCarrito==c.getLstItemCarrito())&& (entrega==c.getEntrega()));
	}

	public boolean agregarItemCarrito(Articulo articulo, int cantidad) throws Exception {
		int id;
		
		if(lstItemCarrito.isEmpty()) {
			id = 0;
	
			}else {
				id = lstItemCarrito.get(lstItemCarrito.size() -1).getArticulo().getId();
				id++;
			}
		
		for (ItemCarrito i : lstItemCarrito) {
			if(i.getArticulo().getNombre().equalsIgnoreCase(articulo.getNombre())) {
				throw new Exception("Error. Articulo repetido");
			}
			
		}
		
		return lstItemCarrito.add(new ItemCarrito(articulo, cantidad));
	}
	
	/* TODO: si encuentra el archivo lo elimina directamente o disminuye una cantidad?
	 * Cambiar bucle for por while
	 * */
	public boolean eliminarItemCarrito(Articulo articulo) throws Exception{
		boolean bandera = true;
		
		for (ItemCarrito i : lstItemCarrito) {
			if(i.getArticulo().getNombre().contentEquals(articulo.getNombre())) {
				lstItemCarrito.remove(i);
				bandera = false;
			}
		}
		
		if(bandera) {
			throw new Exception("Error nombre del Articulo incorrecto");
		}
		
		return true;
	}
	
	public boolean modificaritemCarrito(int id, int cantidad) throws Exception{
		boolean bandera = true;
		
		for (ItemCarrito i : lstItemCarrito) {
			if(i.getArticulo().getId() == id) {
				if(i.getCantidad() == cantidad) {
					lstItemCarrito.remove(i);
				}
				if(i.getCantidad() > cantidad) {
					int nuevaCantidad = i.getCantidad() - cantidad;
					i.setCantidad(nuevaCantidad);
				}
				bandera = false;
			}
		}
		if(bandera) throw new Exception("Error id no encontrada");
		
		
		return true;
	}
	
	// TODO: cambiar bucle for por bucle while
	public boolean agregar(Articulo articulo, int cantidad) {
		Articulo articulo2 = articulo;

		for(ItemCarrito auxiliar:lstItemCarrito) { 			// recorro la listaa
			if(auxiliar.getArticulo().equals(articulo2)) {   // si el articulo ya existe en la lista le sumo 1 a la cantidad
				auxiliar.setCantidad(auxiliar.getCantidad()+1);
			}else {
		ItemCarrito agregar= new ItemCarrito(articulo2,cantidad);    // si no, lo  creo y agrego a la lista
		lstItemCarrito.add(agregar);
			}
		}
		return true;

	}
	
	public double calcularTotal() {
		double total=0;
		for (ItemCarrito item : lstItemCarrito) {
			total += item.calcularSubTotal();
		}
		
		return total;
	}
	
	public double calcularDescuentoDia(int diaDescuento, double porcentajeDescuento) {
		
		int productosAplicarDesc; //Variable para almacenar la cantidad de productos de cada item
		double descuento = 0;
		int diaCarrito = this.fecha.getDayOfWeek().getValue(); //Obtengo el dia de la semana donde se creo el carrito
		if(diaDescuento == diaCarrito) {		
			for(ItemCarrito ca: lstItemCarrito) {
				if(ca.getCantidad() > 1) {
					//por cada item del carrito, divido por 2 para saber a cuanto productos
					//tengo que aplicarle descuento. La division toma la parte entera y redondea para abajo
					productosAplicarDesc = ca.getCantidad()/2;
					descuento += productosAplicarDesc * ca.getArticulo().getPrecio() * porcentajeDescuento/100;
				}
			}
		}
		return descuento;
	}

	public double calcularDescuentoEfectivo(double porcentajeDescuentoEfectivo) {
		//Calculo el total del carrito para luego aplicarle el descuento
		return this.calcularTotal()*porcentajeDescuentoEfectivo/100;
	}
	
	public double calcularDescuentoCarrito(int diaDescuento,double porcentajeDescuento,double porcentajeDescuentoEfectivo) {
		//Me fijo cual de los dos descuento es mayor y lo retorno
		double descuentoEfectivo = calcularDescuentoEfectivo(porcentajeDescuentoEfectivo);
		double descuentoDia = calcularDescuentoDia(diaDescuento,porcentajeDescuento);	
		double descuentoMayor = descuentoDia;
		if(descuentoEfectivo > descuentoDia) {
			descuentoMayor = descuentoEfectivo;
		}
		
		return descuentoMayor;
	}
	
}
