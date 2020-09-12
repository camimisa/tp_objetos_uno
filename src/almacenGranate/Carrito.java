package almacenGranate;


import java.time.LocalDate;
import java.time.LocalTime;
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
			List<ItemCarrito> lstItemCarrito, Entrega entrega) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.hora = hora;
		this.cerrado = cerrado;
		this.descuento = descuento;
		this.cliente = cliente;
		this.lstItemCarrito = lstItemCarrito;
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
				+ descuento + ", cliente=" + cliente + ", lstItemCarrito=" + lstItemCarrito + ", entrega=" + entrega
				+ "]";
	}



	public boolean equals(Carrito c) {
		return ((id==c.getId())&&(fecha==c.getFecha())&&(hora==c.getHora())&&(cerrado==c.isCerrado()) &&(descuento==c.getDescuento())
				&& (cliente==c.getCliente())&& (lstItemCarrito==c.getLstItemCarrito())&& (entrega==c.getEntrega()));
	}


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
	
	
}
