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

	public Carrito(int id, LocalDate fecha, LocalTime hora, boolean cerrado, double descuento, Cliente cliente,
			ItemCarrito itemCarrito, Entrega entrega) {
		this.id = id;
		this.fecha = fecha;
		this.hora = hora;
		this.cerrado = cerrado;
		this.descuento = descuento;
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

	public boolean isCerrado() {
		return cerrado;
	}

	public void setCerrado(boolean cerrado) {
		this.cerrado = cerrado;
	}

	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
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
					descuento += productosAplicarDesc*ca.getArticulo().getPrecio() * porcentajeDescuento/100;
				}
			}
			
		}
		return descuento;
	}

	public double calcularDescuentoEfectivo(double porcentajeDescuentoEfectivo) {
		double totalCarrito = 0;
		//Calculo el total del carrito para luego aplicarle el descuento
		for(ItemCarrito ic: lstItemCarrito) {
			totalCarrito += ic.getCantidad() * ic.getArticulo().getPrecio();
		}		
		
		return totalCarrito*porcentajeDescuentoEfectivo/100;
	}
	
	public double calcuclarDescuentoCarrito(int diaDescuento,double porcentajeDescuento,double porcentajeDescuentoEfectivo) {
		
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
