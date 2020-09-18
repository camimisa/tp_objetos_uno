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
	private List<Articulo> lstArticulo;

	public Comercio(int id, Contacto contacto, String nombreComercio, long cuit, double costoFijo, double costoPorKm,
			int diaDescuento, int porcentajeDescuentoDia, int porcentajeDescuentoEfectivo) throws Exception {
		super(id, contacto);
		this.setNombreComercio(nombreComercio);
		this.setCuit(cuit);
		this.setCostoFijo(costoFijo);
		this.setCostoPorKm(costoPorKm);
		this.setPorcentajeDescuentoDia(porcentajeDescuentoDia);
		this.setPorcentajeDescuentoEfectivo(porcentajeDescuentoEfectivo);
		this.lstDiaRetiro = new ArrayList<DiaRetiro>();
		this.lstCarrito = new ArrayList<Carrito>();
		this.lstArticulo = new ArrayList<Articulo>();
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
		if(this.validarIdentificadorUnico(cuit)) {
			this.cuit = cuit;
		}
		else {
			throw new Exception("ERROR. Cuit invalido");
		}
	}

	public double getCostoFijo() {
		return costoFijo;
	}

	public void setCostoFijo(double costoFijo) {
		if(costoFijo > 0) {
			this.costoFijo = costoFijo;
		}
		else {
			this.costoFijo = 1;
		}
	}

	public double getCostoPorKm() {
		return costoPorKm;
	}

	public void setCostoPorKm(double costoPorKm) {
		if(costoPorKm >= 0) {
			this.costoPorKm = costoPorKm;
		}
		else {
			this.costoPorKm = 0;
		}
	}

	public int getDiaDescuento() {
		return diaDescuento;
	}

	public void setDiaDescuento(int diaDescuento) {
		if(diaDescuento > 0) {
			this.diaDescuento = diaDescuento;
		}
		else {
			this.diaDescuento = 1;
		}
	}

	public int getPorcentajeDescuentoDia() {
		return porcentajeDescuentoDia;
	}

	public void setPorcentajeDescuentoDia(int porcentajeDescuentoDia) {
		if( porcentajeDescuentoDia >= 0 ) {
			this.porcentajeDescuentoDia = porcentajeDescuentoDia;
		}
		else {
			this.porcentajeDescuentoDia = 0;
		}
		
	}

	public int getPorcentajeDescuentoEfectivo() {
		return porcentajeDescuentoEfectivo;
	}

	public void setPorcentajeDescuentoEfectivo(int porcentajeDescuentoEfectivo) {
		if( porcentajeDescuentoEfectivo >= 0 ) {
			this.porcentajeDescuentoEfectivo = porcentajeDescuentoEfectivo;
		}
		else {
			this.porcentajeDescuentoEfectivo = 0;
		}
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
	

	public List<Articulo> getLstArticulo() {
		return lstArticulo;
	}

	public void setLstArticulo(List<Articulo> lstArticulo) {
		this.lstArticulo = lstArticulo;
	}

	@Override
	public String toString() {
		return "Comercio [nombreComercio=" + nombreComercio + ", cuit=" + cuit + ", costoFijo=" + costoFijo
				+ ", costoPorKm=" + costoPorKm + ", diaDescuento=" + diaDescuento + ", porcentajeDescuentoDia="
				+ porcentajeDescuentoDia + ", porcentajeDescuentoEfectivo=" + porcentajeDescuentoEfectivo
				+ ", lstDiaRetiro=" + lstDiaRetiro + ", lstCarrito=" + lstCarrito + ",lstArticulo="+ lstArticulo +"]";
	}

	// --------------------------------------TP---------------------------------------
	
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
	protected boolean validarIdentificadorUnico(long identificador){
		
		String cuitString = String.valueOf(identificador);
		
		if(cuitString.length()>11) {
			return false;
		}
		
		int suma,ultimoNumero=0;
		String primerParte = cuitString.substring(0,2);
		String segundaParte = cuitString.substring(2, 10);
		char tercerParte = cuitString.charAt(cuitString.length()-1);
		
		suma = Character.getNumericValue(primerParte.charAt(0)) * 5 + Character.getNumericValue(primerParte.charAt(1)) * 4 + Character.getNumericValue(segundaParte.charAt(0)) * 3 + Character.getNumericValue(segundaParte.charAt(1)) * 2 +
				Character.getNumericValue(segundaParte.charAt(2)) * 7 + Character.getNumericValue(segundaParte.charAt(3)) * 6 + Character.getNumericValue(segundaParte.charAt(4)) * 5 + Character.getNumericValue(segundaParte.charAt(5)) * 4 +
				Character.getNumericValue(segundaParte.charAt(6)) * 3 + Character.getNumericValue(segundaParte.charAt(7)) * 2 ;
		
		ultimoNumero = 11 - suma%11;
		
		if(Character.getNumericValue(tercerParte) != ultimoNumero) {
			
			return false;
		}
		
		return true;
	}
	
	public List<Turno> generarTurnosLibres(LocalDate fecha) {
		// Genera una lista con T.ODOS los turnos de ese dia. (Turnos libres)
		
		if(lstDiaRetiro.size() == 0) {
			return null;
		}
		
		DiaRetiro diaRetiro = this.traerDiaRetiro(fecha);
		/* Calcula la cantidad de minutos que hay entre la primer hora de retiro hasta la ultima para despues poder
		   dividirlo entre el intervalo de minutos de cada turno y asi da la cantidad de turnos que va a haber ese dia.
		*/
		int cantidadMinutos = (int) ChronoUnit.MINUTES.between(diaRetiro.getHoraDesde(), diaRetiro.getHoraHasta());
		
		int cantidadDeTurnos = cantidadMinutos / diaRetiro.getIntervalo();
		
		List<Turno> agenda = new ArrayList<Turno>();
		
		//Guarda el turno de la primer hora del dia
		LocalTime horaTurno = diaRetiro.getHoraDesde();

		//Uso long porque plusMinutes recibe un long
		long minutosEntreTurnos = (long)diaRetiro.getIntervalo();
		
		for(int i=0; i<cantidadDeTurnos ; i++) {
			Turno nuevoTurno = new Turno(fecha,horaTurno,false);
			agenda.add(nuevoTurno);
			horaTurno = horaTurno.plusMinutes(minutosEntreTurnos);
		}
		
		return agenda;
	}
	
	public List<Turno> generarAgenda(LocalDate fecha){
		// Devuelve todos los turnos de esa fecha. Los libres y los ocupados.
		List <Turno> turnos = this.generarTurnosLibres(fecha);
		RetiroLocal entregaCarrito = null;
		int i = 0;
		boolean horaEncontrada = false;
		// Trae los carritos que fueron comprados ese dia.
		List <Carrito> carritosFecha = this.traerCarritos(fecha);
		for(Carrito carrito: carritosFecha) {
			horaEncontrada = false;
			i = 0;
			// Solo tienen turno los que se retiran en el local.
			
			if(carrito.getEntrega() instanceof RetiroLocal) {
				entregaCarrito = (RetiroLocal) carrito.getEntrega();
				
				while( !horaEncontrada && turnos.size() > i ) {
					if( (entregaCarrito.getHoraEntrega() != null) 
							&& (entregaCarrito.getHoraEntrega().equals(turnos.get(i).getHora())) ) {
						turnos.get(i).setOcupado(true);
						horaEncontrada = true;
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
	
	public List<LocalTime> traerHoraRetiro (LocalDate fecha){
		
		List <LocalTime> horasDisponibles = new ArrayList<LocalTime>();
		List <Turno> turnosLibres = this.traerTurnosLibres(fecha);
		
		for(Turno turno : turnosLibres) {
			horasDisponibles.add(turno.getHora());
		}
		
		return horasDisponibles;
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
	
	public boolean agregarCarrito(LocalDate fecha, LocalTime hora, double descuento, Cliente cliente,
			Entrega entrega) throws Exception {
		Carrito nuevoCarrito = new Carrito(getIdCarrito(), fecha, hora,descuento,cliente,entrega);
		
		if(this.carritoExiste(nuevoCarrito) == -1) {
			nuevoCarrito.setComercio(this);
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
	
	
	
	/*CRUD ARTICULOS*/
	
	private int getIdArticulo() {
		if (lstArticulo.size() == 0) {
			return 0;
		}
		else {
			// Le suma 1 al ultimo id guardado en la lista.
			return lstArticulo.get(lstArticulo.size()-1).getId() + 1;
		}
	}
	
	private int articuloExiste(Articulo articulo) {
		// Si no existe retorna -1. Si existe retorna la posicion donde se encuentra
		boolean existe = false;
		int pos = -1, i = 0;
		
		while(!existe && i<lstArticulo.size()) {
			if(lstArticulo.get(i).equals(articulo)) {
				pos = i;
				existe = true;
			}
			i++;
		}
		return pos;
	}
	
	
	public boolean agregarArticulo(String nombre, String codBarras, double precio) throws Exception {
		if(precio < 1) throw new Exception("ERROR. El precio no puede ser 0 o negativo");
		
		Articulo nuevoArticulo = new Articulo(getIdArticulo(), nombre,codBarras,precio);
		
		if(this.articuloExiste(nuevoArticulo) == -1) {
			this.lstArticulo.add(nuevoArticulo);
		}
		else {
			throw new Exception ("ERROR. El articulo ya existe");
		}
		
		return false;
	}
	
	public Articulo traerArticulo(int id) {
		boolean existe = false;
		int i = 0;
		Articulo articulo = null;
		
		while(!existe && i<lstArticulo.size()) {
			if(lstArticulo.get(i).getId() == id) {
				articulo = lstArticulo.get(i);
				existe = true;
			}
			i++;
		}
		return articulo;
	}
	
	
	public boolean eliminarArticulo(int id) throws Exception {
		
		Articulo articuloAEliminar = this.traerArticulo(id);
		
		int pos = this.articuloExiste(articuloAEliminar);
		
		if ( pos != -1 ) {
			lstArticulo.remove(pos);
			return true;
		}
		else {
			throw new Exception ("ERROR. No se pudo eliminar el articulo");
		}
		
	}
		
}