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

	// TODO: entrega no deberia mandarse por parametro.
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
		
		String texto = "Carrino nº " + id + " fecha: " + fecha + " " + hora + "\nCliente: " + cliente +
				"\nPRODUCTOS:\nID\tNOMBRE\t\tPRECIO  CODBARRAS  CANTIDAD  SUBTOTAL\n" + lstItemCarrito.toString().replace("[","").replace("]","").replace(",", "").replace(" ", "")+ 
				"\nTotal: " + this.calcularTotal() + "\nDecuento: " + this.descuento + "\nTotal a pagar: " + this.totalAPagarCarrito(); 
		
		return texto;
	}
	
	public boolean equals(Carrito c) {
		return ((id==c.getId())&&(fecha==c.getFecha())&&(hora==c.getHora())&&(cerrado==c.getCerrado()) &&(descuento==c.getDescuento())
				&& (cliente==c.getCliente())&& (lstItemCarrito==c.getLstItemCarrito())&& (entrega==c.getEntrega()));
	}

	private int traerPosicionArticulo(Articulo articulo) {
		int posicion = -1;
		int i = 0;
		
		while( (i<lstItemCarrito.size()) && (posicion == -1) ) {
			if(lstItemCarrito.get(i).getArticulo().equals(articulo)){
				posicion = i;
			}
			i++;
		}
		
		return posicion;
	}
	
	private int traerPosicionArticulo(int id) {
		int posicion = -1;
		int i = 0;
		
		while( (i<lstItemCarrito.size()) && (posicion == -1) ) {
			if(lstItemCarrito.get(i).getArticulo().getId() == id){
				posicion = i;
			}
			i++;
		}
		
		return posicion;
	}
	
	public boolean agregar(Articulo articulo, int cantidad) throws Exception {
		
		if (cantidad < 1)
			throw new Exception("ERROR. Cantidad NO valida.");
		
		int posicion = this.traerPosicionArticulo(articulo);
		ItemCarrito itemCarrito = new ItemCarrito(articulo,cantidad);
		int cantidadAux = 0;
		
		if( posicion == -1 ) {
			lstItemCarrito.add(itemCarrito);
		}
		else {
			cantidadAux = lstItemCarrito.get(posicion).getCantidad();
			lstItemCarrito.get(posicion).setCantidad(cantidadAux + cantidad);
		}
		
		return true;
	}
	
	
	// TODO: si encuentra el archivo lo elimina directamente o disminuye una cantidad?
	public boolean eliminarItemCarrito(Articulo articulo) throws Exception{
		int posicion = this.traerPosicionArticulo(articulo);
		
		if(posicion == -1) {
			throw new Exception("ERROR. Nombre del articulo incorrecto.");
		}
		
		if( lstItemCarrito.get(posicion).getCantidad() == 1 ) {
			lstItemCarrito.remove(posicion);
		}
		else {
			lstItemCarrito.get(posicion).setCantidad(lstItemCarrito.get(posicion).getCantidad() - 1 );
		}

		return true;
	}
	
	public boolean modificarItemCarrito(int id, int cantidad) throws Exception{
		
		if (cantidad < 1)
			throw new Exception("ERROR. Cantidad NO valida.");
		
		int posicion = this.traerPosicionArticulo(id);
		
		if(posicion == -1) {
			throw new Exception("ERROR. Articulo incorrecto.");
		}
		
		if( lstItemCarrito.get(posicion).getCantidad() == 1 ) {
			lstItemCarrito.remove(posicion);
		}
		else {
			lstItemCarrito.get(posicion).setCantidad(cantidad);
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
	
	public double totalAPagarCarrito() {
		double total = this.calcularTotal() - this.descuento;
		
		if(entrega instanceof Envio) {
			total += ((Envio) entrega).getCosto();
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
		
		double descuento = 0;
		//Calculo el total del carrito para luego aplicarle el descuento si el cliente paga con efectivo.
		if(this.entrega.getEfectivo())
			descuento = this.calcularTotal()*porcentajeDescuentoEfectivo/100;
	
		return descuento;
	}
	
	public double calcularDescuentoCarrito(int diaDescuento,double porcentajeDescuento,double porcentajeDescuentoEfectivo) {
		//Me fijo cual de los dos descuento es mayor y lo retorno
		double descuentoEfectivo = calcularDescuentoEfectivo(porcentajeDescuentoEfectivo);
		double descuentoDia = calcularDescuentoDia(diaDescuento,porcentajeDescuento);	
		double descuentoMayor = descuentoDia;
		if(descuentoEfectivo > descuentoDia) {
			descuentoMayor = descuentoEfectivo;
		}
		
		//TODO: no se si esto va aca
		this.setDescuento(descuentoMayor);
		
		return descuentoMayor;
	}
	
}
