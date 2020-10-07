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
	private Comercio comercio;

	public Carrito(int id, LocalDate fecha, LocalTime hora, Cliente cliente, Entrega entrega) {
		this.id = id;
		this.fecha = fecha;
		this.hora = hora;
		this.cerrado = false;
		this.descuento = 0;
		this.cliente = cliente;
		this.lstItemCarrito = new ArrayList<ItemCarrito>();
		this.entrega = entrega;
	}

	public Carrito(int id, LocalDate fecha, LocalTime hora, Cliente cliente) {
		this.id = id;
		this.fecha = fecha;
		this.hora = hora;
		this.cerrado = false;
		this.descuento = 0;
		this.cliente = cliente;
		this.lstItemCarrito = new ArrayList<ItemCarrito>();
		this.entrega = null;
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
		
		this.descuento = 0;
		
		if(descuento >= 0) 
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
	
	public void setEntregaEnvio(LocalDate fecha, boolean efectivo, LocalTime horaDesde, LocalTime horaHasta, Ubicacion ubicacion) {
		this.entrega = new Envio(this.id, fecha, efectivo, horaDesde, horaHasta, ubicacion);
	}

	public void setEntregaRetiroLocal(LocalDate fecha, boolean efectivo) {
		if(comercio.traerDiaRetiro(fecha) != null)
			this.entrega = new RetiroLocal(this.id, fecha, efectivo);
		else		
			// Si selecciona como fecha de retiro un sabado o un domingo se le va a sumar dos dias al retiro.
			this.entrega = new RetiroLocal(this.id, fecha.plusDays(2), efectivo);

	}
	
	public Comercio getComercio() {
		return comercio;
	}

	public void setComercio(Comercio comercio) {
		this.comercio = comercio;
	}
	
	@Override
	public String toString() {
		
		String separacion = "\n-------------------------------------------------------------------------------------\n";
		
		double totalAPagar = this.totalAPagarCarrito();
		
		String texto = separacion + "\nCarrito n� " + id + " fecha: " + fecha + " " + hora + "\nCliente: " + cliente +
				"\nPRODUCTOS:\nID\tNOMBRE\t\tPRECIO  CODBARRAS\t CANTIDAD\tSUBTOTAL\n" + lstItemCarrito.toString().replace("[","").replace("]","").replace(",", "").replace(" ", "")+ 
				"\nTotal: " + this.calcularTotal() + "\nDescuento: " + this.descuento + "\nTotal a pagar: " + totalAPagar; 
		
		if(this.verificarEntregaVacia()) {
			if(entrega instanceof Envio) {
				texto += "\n" + ((Envio)entrega).toString();
			}
			else {
				texto += "\n\n\tFecha de retiro: " + entrega.getFecha()  + " " + ((RetiroLocal) entrega).getHoraEntrega();
			}
		}
		texto += separacion;
		
		return texto;
	}
	
	public boolean equals(Carrito c) {
		return ((id==c.getId())&&(fecha==c.getFecha())&&(hora==c.getHora())&&(cerrado==c.getCerrado()) &&(descuento==c.getDescuento())
				&& (cliente==c.getCliente())&& (lstItemCarrito==c.getLstItemCarrito())&& (entrega==c.getEntrega()));
	}

	private boolean verificarCarritoAbierto() throws Exception {
		if (!this.cerrado) {
			return true;
		}
		else {
			throw new Exception ("ERROR. Este pedido esta cerrado.");
		}
	}
	
	private int traerPosicionArticulo(Articulo articulo) {
		int posicion = -1;
		int i = 0;
		
		while( (i<lstItemCarrito.size()) && (posicion == -1)) {
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
		
		this.verificarCarritoAbierto();
		
		if (cantidad < 1) throw new Exception("ERROR. Cantidad NO valida.");
			
		int posicion = this.traerPosicionArticulo(articulo);
		int posicionListaComercio = this.comercio.articuloExiste(articulo);
		
		ItemCarrito itemCarrito = new ItemCarrito(articulo,cantidad);
		int cantidadAux = 0;
		
		if(posicionListaComercio == -1) throw new Exception("ERROR. El articulo que quiere agregar no existe en el comercio.");
		
		if(posicion == -1 ) {
			lstItemCarrito.add(itemCarrito);
		}
		else {
			cantidadAux = lstItemCarrito.get(posicion).getCantidad();
			lstItemCarrito.get(posicion).setCantidad(cantidadAux + cantidad);
		}
		
		
		return true;
	}
	
	public boolean eliminarItemCarrito(Articulo articulo) throws Exception{
		int posicion = this.traerPosicionArticulo(articulo);
		
		this.verificarCarritoAbierto();
		
		if(posicion == -1) {
			throw new Exception("ERROR. Articulo no existe.");
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
		
		this.verificarCarritoAbierto();
		
		if (cantidad < 1) throw new Exception("ERROR. Cantidad NO valida.");
			
		int posicion = this.traerPosicionArticulo(id);
		
		if(posicion == -1) throw new Exception("ERROR. Articulo incorrecto.");
						
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

		this.setDescuento(this.calcularDescuentoCarrito(comercio.getDiaDescuento(), 
				comercio.getPorcentajeDescuentoDia(), comercio.getPorcentajeDescuentoEfectivo()));
		
		double total = this.calcularTotal() - this.descuento;
		
		total += this.operacionesConEntrega();
		
		this.setCerrado(true);
		
		return total;
	}
	
	private boolean verificarEntregaVacia() {
		
		boolean flag = false;
		
		if(entrega != null) {
			flag = true;
		}
		
		return flag;
	}
	
	private double operacionesConEntrega() {
		boolean entregaVacia = false;
		entregaVacia = this.verificarEntregaVacia();
		double gastosEnvio = 0;
		if(!entregaVacia) {
			System.out.println("\nCarrito n� " + this.id + " No podra recibir su pedido porque NO ingreso un tipo de entrega.\n"
					+ "Para solucionar este problema comuniquese con servicio de atencion al cliente.");
		}
		else {
			if(entrega instanceof Envio) {
				try {
					gastosEnvio = this.setCostoEntrega();
				} catch (Exception e) {
					((Envio)entrega).setHoraDesde(null);
					((Envio)entrega).setHoraHasta(null);
					((Envio)entrega).setFecha(null);
					System.out.println("\nCarrito n� " + this.id + " No podra recibir su pedido porque ingreso una fecha de envio invalida.\n"
							+ "Para solucionar este problema comuniquese con servicio de atencion al cliente.");
					((Envio)entrega).setCosto(0);
				}
			}
			else {
				try {
					this.setHoraEntrega(this.entrega.getFecha());
				}
				catch(Exception e){
					((RetiroLocal) entrega).setHoraEntrega(null);
					System.out.println("\nCarrito n� " + this.id + " No podra retirar su pedido porque ingreso una fecha de retiro invalida.\n"
							+ "Para solucionar este problema comuniquese con servicio de atencion al cliente.");	
				}	
			}
		}
		return gastosEnvio;
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
		if(entrega != null) {
			if(this.entrega.getEfectivo())
				descuento = this.calcularTotal()*porcentajeDescuentoEfectivo/100;
		}
		
	
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

		return descuentoMayor;
	}
	
	
	private void setHoraEntrega(LocalDate fecha) throws Exception {	
		
		// Se verifica que la fecha de entrega sea el mismo dia del carrito o despues.
		if( !(entrega.getFecha().isAfter(fecha) || entrega.getFecha().equals(fecha)) ) throw new Exception("ERROR. La fecha de entrega no puede ser anterior a la fecha de compra del carrito.");
			
		List <LocalTime> horariosDisponibles = this.comercio.traerHoraRetiro(entrega.getFecha());
		
		//Generamos un numero random de posicion de la lista para asignarlo como hora de retiro
		
		int numeroPosRandom = (int)(Math.random()*( horariosDisponibles.size() - 1) );
		
		if(((RetiroLocal) entrega).getHoraEntrega() == null)
			((RetiroLocal) entrega).setHoraEntrega(horariosDisponibles.get(numeroPosRandom));
		
		
	}
	
	private double setCostoEntrega() throws Exception {
		double costo = 0;
		// Se verifica que la fecha de entrega sea el mismo dia del carrito o despues.
		if( !(entrega.getFecha().isAfter(fecha) || entrega.getFecha().equals(fecha) )) throw new Exception("ERROR. La fecha de entrega no puede ser anterior a la fecha de compra del carrito.");
		
		((Envio) entrega).setCosto(comercio.getContacto().getUbicacion(),comercio.getCostoFijo(),comercio.getCostoPorKm());
		costo = ((Envio) entrega).getCosto();
	
		return costo;
	}
}
