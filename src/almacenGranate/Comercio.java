package almacenGranate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Comercio extends Actor {

	private String nombreComercio;
	private long cuit;
	private double costoFijo;
	private double costoPorKm;
	private int diaDescuento;
	private int porcentajeDescuentoDia;
	private int porcentajeDescuentoEfectivo;
	private List<DiaRetiro> lstDiaRetiro;
	private List<Carrito> lstCarrito;

	public Comercio(int id, Contacto contacto, String nombreComercio, long cuit, double costoFijo, double costoPorKm,
			int diaDescuento, int porcentajeDescuentoDia, int porcentajeDescuentoEfectivo) throws Exception {
		super(id, contacto);
		this.nombreComercio = nombreComercio;
		this.setCuit(cuit);
		this.costoFijo = costoFijo;
		this.costoPorKm = costoPorKm;
		this.diaDescuento = diaDescuento;
		this.porcentajeDescuentoDia = porcentajeDescuentoDia;
		this.porcentajeDescuentoEfectivo = porcentajeDescuentoEfectivo;
		this.lstDiaRetiro = new ArrayList<DiaRetiro>();
		this.lstCarrito = new ArrayList<Carrito>();
	}

	public String getNombreComercio() {
		return nombreComercio;
	}

	public void setNombreComercio(String nombreComercio) {
		this.nombreComercio = nombreComercio;
	}

	public long getCuit() {
		return cuit;
	}

	public void setCuit(long cuit) throws Exception {
		this.cuit = cuit;
		if(!this.validarIdentificadorUnico())
			cuit = 0;
	}

	public double getCostoFijo() {
		return costoFijo;
	}

	public void setCostoFijo(double costoFijo) {
		this.costoFijo = costoFijo;
	}

	public double getCostoPorKm() {
		return costoPorKm;
	}

	public void setCostoPorKm(double costoPorKm) {
		this.costoPorKm = costoPorKm;
	}

	public int getDiaDescuento() {
		return diaDescuento;
	}

	public void setDiaDescuento(int diaDescuento) {
		this.diaDescuento = diaDescuento;
	}

	public int getPorcentajeDescuentoDia() {
		return porcentajeDescuentoDia;
	}

	public void setPorcentajeDescuentoDia(int porcentajeDescuentoDia) {
		this.porcentajeDescuentoDia = porcentajeDescuentoDia;
	}

	public int getPorcentajeDescuentoEfectivo() {
		return porcentajeDescuentoEfectivo;
	}

	public void setPorcentajeDescuentoEfectivo(int porcentajeDescuentoEfectivo) {
		this.porcentajeDescuentoEfectivo = porcentajeDescuentoEfectivo;
	}

	public List<DiaRetiro> getLstDiaRetiro() {
		return lstDiaRetiro;
	}

	public void setLstDiaRetiro(List<DiaRetiro> lstDiaRetiro) {
		this.lstDiaRetiro = lstDiaRetiro;
	}

	public List<Carrito> getLstCarrito() {
		return lstCarrito;
	}

	public void setLstCarrito(List<Carrito> lstCarrito) {
		this.lstCarrito = lstCarrito;
	}

	@Override
	public String toString() {
		return "Comercio [nombreComercio=" + nombreComercio + ", cuit=" + cuit + ", costoFijo=" + costoFijo
				+ ", costoPorKm=" + costoPorKm + ", diaDescuento=" + diaDescuento + ", porcentajeDescuentoDia="
				+ porcentajeDescuentoDia + ", porcentajeDescuentoEfectivo=" + porcentajeDescuentoEfectivo
				+ ", lstDiaRetiro=" + lstDiaRetiro + ", lstCarrito=" + lstCarrito + "]";
	}

	// --------------------------------------TP---------------------------------------
	
	// TODO: revisar esto
	
	private int getIdCarrito() {
		if (lstCarrito.size() == 0) {
			return 0;
		}
		else {
			// Le suma 1 al ultimo id guardado en la lista.
			return lstCarrito.get(lstCarrito.size()-1).getId() + 1;
		}
	}
	
	private int getIdDiaRetiro() {
		if (lstDiaRetiro.size() == 0) {
			return 0;
		}
		else {
			// Le suma 1 al ultimo id guardado en la lista.
			return lstDiaRetiro.get(lstDiaRetiro.size()-1).getId() + 1;
		}
	}
	
	@Override
	protected boolean validarIdentificadorUnico() throws Exception {
		
		String cuitString = "" + this.cuit;
		
		if(cuitString.length()>11) {
			throw new Exception ("ERROR. Supera los 11 caracteres.");
		}
		
		int suma,ultimoNumero=0;
		String primerParte = cuitString.substring(0,2);
		String segundaParte = cuitString.substring(2, 10);
		char tercerParte = cuitString.charAt(cuitString.length()-1);
		
		suma = Character.getNumericValue(primerParte.charAt(0)) * 5 + Character.getNumericValue(primerParte.charAt(1)) * 4 + Character.getNumericValue(segundaParte.charAt(0)) * 3 + Character.getNumericValue(segundaParte.charAt(1)) * 2 +
				Character.getNumericValue(segundaParte.charAt(2)) * 7 + Character.getNumericValue(segundaParte.charAt(3)) * 6 + Character.getNumericValue(segundaParte.charAt(4)) * 5 + Character.getNumericValue(segundaParte.charAt(5)) * 4 +
				Character.getNumericValue(segundaParte.charAt(6)) * 3 + Character.getNumericValue(segundaParte.charAt(7)) * 2 ;
		
		suma = suma%11;
		
		switch(suma) {
		case 0: ultimoNumero = 0;
		break;
		case 1: 
			//TODO: no se q poner en este caso si es empresa
		break;
		default:
			ultimoNumero = 11 - suma;
		break;
		}
		
		if(Character.getNumericValue(tercerParte) != ultimoNumero) {
			
			throw new Exception ("ERROR. El ultimo digito de tu cuil esta mal ingresado.");
		}
		
		return true;
	}
	
	public List<Turno> generarTurnosLibres(LocalDate fecha) {
		// Genera una lista con T.ODOS los turnos de ese dia. (Turnos libres)
		
		DiaRetiro diaRetiro = this.traerDiaRetiro(fecha);
		/* Calcula la cantidad de minutos que hay entre la primer hora de retiro hasta la ultima para despues poder
		   dividirlo entre el intervalo de minutos de cada turno y asi da la cantidad de turnos que va a haber ese dia.
		*/
		int cantidadMinutos = (int) ChronoUnit.MINUTES.between(diaRetiro.getHoraDesde(), diaRetiro.getHoraHasta());
		
		int cantidadDeTurnos = cantidadMinutos / diaRetiro.getIntervalo();
		
		List<Turno> agenda = new ArrayList<Turno>();
		
		LocalTime horaTurno = diaRetiro.getHoraDesde();
		
		long minutosEntreTurnos = (long)diaRetiro.getIntervalo();
		
		for(int i=0; i<cantidadDeTurnos ; i++) {
			agenda.add(new Turno(fecha,horaTurno,false));
			horaTurno = horaTurno.plusMinutes(minutosEntreTurnos);
		}
		
		return agenda;
	}
	
	public List<Turno> generarAgenda(LocalDate fecha){
		// Devuelve todos los turnos de esa fecha. Los libres y los ocupados.
		List <Turno> turnos = this.generarTurnosLibres(fecha);
		Entrega entregaCarrito = null;
		int i = 0;
		List <Carrito> carritosFecha = this.traerCarritos(fecha);
		// Trae los carritos que fueron comprados ese dia.
		for(Carrito carrito: carritosFecha) {
			entregaCarrito = carrito.getEntrega();
			// Solo tienen turno los que se retiran en el local.
			if(entregaCarrito instanceof RetiroLocal) {
				// Se chequea esto para que no haya una excepcion.
				if (i < turnos.size()) {
					// Si la hora del retiro en el local es igual a uno de los horarios disponibles para retirar
					// entonces ese turno esta ocupado.
					if(((RetiroLocal) entregaCarrito).getHoraEntrega() == turnos.get(i).getHora()) {
						turnos.get(i).setOcupado(true);
					}
					i++;
				}
			}
		}
		return turnos;
	}
	
	public List<Turno> traerTurnosLibres(LocalDate fecha){
		
		List <Turno> turnos = this.generarAgenda(fecha);
		List <Turno> turnosLibres = new ArrayList<Turno>();
		
		for(Turno turno : turnos) {
			if(!turno.getOcupado())
				turnosLibres.add(turno);
		}
		
		return turnosLibres;
	}
	
	public List<Turno> traerTurnosOcupados(LocalDate fecha){
		
		List <Turno> turnos = this.generarAgenda(fecha);
		List <Turno> turnosOcupados = new ArrayList<Turno>();
		
		for(Turno turno : turnos) {
			if(turno.getOcupado())
				turnosOcupados.add(turno);
		}
		
		return turnosOcupados;
	}
	
	
	public boolean agregarDiaRetiro(int diaSemana, LocalTime horaDesde, LocalTime horaHasta, int intervalo) throws Exception {
		DiaRetiro nuevoRetiro = new DiaRetiro(getIdDiaRetiro(),diaSemana,horaDesde,horaHasta,intervalo);
		
		if(this.diaRetiroExiste(nuevoRetiro) == -1) {
			this.lstDiaRetiro.add(nuevoRetiro);
			return true;
		}
		else {
			throw new Exception ("ERROR. El dia de retiro ya existe");
		}
	}
	
	private DiaRetiro traerDiaRetiro(LocalDate fecha) {
		boolean existe = false;
		int i = 0;
		DiaRetiro diaRetiro = null;
		
		int diaSemana = fecha.getDayOfWeek().getValue();
		
		while(!existe && i<lstDiaRetiro.size()) {
			if(lstDiaRetiro.get(i).getDiaSemana() == diaSemana) {
				diaRetiro = lstDiaRetiro.get(i);
				existe = true;
			}
			i++;
		}
		return diaRetiro;
	}
	
	private DiaRetiro traerDiaRetiro(int id) {
		boolean existe = false;
		int i = 0;
		DiaRetiro diaRetiro = null;
		
		while(!existe && i<lstDiaRetiro.size()) {
			if(lstDiaRetiro.get(i).getId() == id) {
				diaRetiro = lstDiaRetiro.get(i);
				existe = true;
			}
			i++;
		}
		return diaRetiro;
	}
	
	private int diaRetiroExiste(DiaRetiro diaRetiro) {
		// Si no existe retorna -1. Si existe retorna la posicion donde se encuentra
		boolean existe = false;
		int pos = -1, i = 0;
		
		while(!existe && i<lstDiaRetiro.size()) {
			if(lstDiaRetiro.get(i).equals(diaRetiro)) {
				pos = i;
				existe = true;
			}
			i++;
		}
		return pos;
	}
	
	public boolean eliminarDiaRetiro(int id) {
		
		DiaRetiro diaRetiroAEliminar = this.traerDiaRetiro(id);
		
		int pos = this.diaRetiroExiste(diaRetiroAEliminar);
		
		if ( pos != -1 ) {
			lstDiaRetiro.remove(pos);
			return true;
		}
		
		return false;
	}
	
	public boolean modificarDiaRetiro(int id, int diaSemana, LocalTime horaDesde, LocalTime horaHasta, int intervalo) throws Exception {
		DiaRetiro diaRetiroModificado = this.traerDiaRetiro(id);
		
		if ( diaRetiroModificado != null ) {
			diaRetiroModificado.setDiaSemana(diaSemana);
			diaRetiroModificado.setHoraDesde(horaDesde);
			diaRetiroModificado.setHoraHasta(horaHasta);
			diaRetiroModificado.setIntervalo(intervalo);
			return true;
		}
		else {
			throw new Exception ("ERROR. El dia de retiro seleccionado no existe.");
		}
		
	}
	
	public boolean agregarCarrito(LocalDate fecha, LocalTime hora, boolean cerrado, double descuento, Cliente cliente,
			Entrega entrega) throws Exception {
		Carrito nuevoCarrito = new Carrito(getIdCarrito(), fecha, hora,cerrado,descuento,cliente,entrega);
		
		if(this.carritoExiste(nuevoCarrito) == -1) {
			this.lstCarrito.add(nuevoCarrito);
		}
		else {
			throw new Exception ("ERROR. El carrito ya existe");
		}
		
		return false;
	}
	
	public Carrito traerCarrito(int id) {
		boolean existe = false;
		int i = 0;
		Carrito carrito = null;
		
		while(!existe && i<lstCarrito.size()) {
			if(lstCarrito.get(i).getId() == id) {
				carrito = lstCarrito.get(i);
				existe = true;
			}
			i++;
		}
		return carrito;
	}
	
	public List<Carrito> traerCarritos(LocalDate fecha) {
		List <Carrito> carritosFecha = new ArrayList<Carrito>();
		
		for(Carrito carrito : lstCarrito) {
			if(carrito.getFecha().equals(fecha)) {
				carritosFecha.add(carrito);
			}
		}
		return carritosFecha;
	}
	
	private int carritoExiste(Carrito Carrito) {
		// Si no existe retorna -1. Si existe retorna la posicion donde se encuentra
		boolean existe = false;
		int pos = -1, i = 0;
		
		while(!existe && i<lstCarrito.size()) {
			if(lstCarrito.get(i).equals(Carrito)) {
				pos = i;
				existe = true;
			}
			i++;
		}
		return pos;
	}
	
	public boolean eliminarCarrito(int id) throws Exception {
		
		Carrito carritoAEliminar = this.traerCarrito(id);
		
		int pos = this.carritoExiste(carritoAEliminar);
		
		if ( pos != -1 ) {
			lstCarrito.remove(pos);
			return true;
		}
		else {
			throw new Exception ("ERROR. No se pudo eliminar el carrito");
		}
		
	}
	
	public boolean modificarCarrito(int id, LocalDate fecha, LocalTime hora, boolean cerrado, double descuento, Cliente cliente,
			Entrega entrega) throws Exception {
		Carrito CarritoModificado = this.traerCarrito(id);
		
		if ( CarritoModificado != null ) {
			CarritoModificado.setFecha(fecha);
			CarritoModificado.setHora(hora);
			CarritoModificado.setCerrado(cerrado);
			CarritoModificado.setDescuento(descuento);
			CarritoModificado.setCliente(cliente);
			CarritoModificado.setEntrega(entrega);
			return true;
		}
		else {
			throw new Exception ("ERROR. El carrito seleccionado no existe.");
		}
		
	}
		
}
