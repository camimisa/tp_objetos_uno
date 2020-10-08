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
		this.setDiaDescuento(diaDescuento);
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
		return "Comercio " + nombreComercio + "\n\nContacto: " + contacto + "\nInformacion:\nCosto Fijo de envio: " + costoFijo +
					"\nCosto por km: " + costoPorKm + "\nDia de descuento: " + diaDescuento + " Porcentaje de descuento: " + porcentajeDescuentoDia +
						"%\nPorcentaje descuento efectivo: " + porcentajeDescuentoEfectivo + "%\n";
	}

	// --------------------------------------TP---------------------------------------
	
	private int getIdCarrito() {
		int id = 1;
		if (lstCarrito.size() != 0) {
			id = lstCarrito.get(lstCarrito.size()-1).getId() + 1;
		}
		return id;
	}
	
	private int getIdDiaRetiro() {
		int id = 1;
		if (lstDiaRetiro.size() != 0) {
			id = lstDiaRetiro.get(lstDiaRetiro.size()-1).getId() + 1;
		}
		return id;
	}
	
	private int getIdCliente() {
		// Busca el id de cliente mas grande de la lista de carrito y se le suma uno para asignarselo al proximo cliente.
		int idCliente = 0;
		if (lstCarrito.size() != 0) {
			for(Carrito carrito : lstCarrito) {
				if (idCliente < carrito.getCliente().getId()) {
					idCliente = carrito.getCliente().getId();
				}
			}
		}
		return (idCliente + 1);
	}
	
	@Override
	protected boolean validarIdentificadorUnico(long identificador) throws Exception{
		
		String cuitString = String.valueOf(identificador);
		
		if(cuitString.length() != 11) throw new Exception ("ERROR. Cuit invalido : la cantidad de digitos debe ser 11.");
		
		int suma,ultimoNumero=0;
		String primerParte = cuitString.substring(0,2);
		String segundaParte = cuitString.substring(2, 10);
		char tercerParte = cuitString.charAt(cuitString.length()-1);
		
		suma = Character.getNumericValue(primerParte.charAt(0)) * 5 + Character.getNumericValue(primerParte.charAt(1)) * 4 + Character.getNumericValue(segundaParte.charAt(0)) * 3 + Character.getNumericValue(segundaParte.charAt(1)) * 2 +
				Character.getNumericValue(segundaParte.charAt(2)) * 7 + Character.getNumericValue(segundaParte.charAt(3)) * 6 + Character.getNumericValue(segundaParte.charAt(4)) * 5 + Character.getNumericValue(segundaParte.charAt(5)) * 4 +
				Character.getNumericValue(segundaParte.charAt(6)) * 3 + Character.getNumericValue(segundaParte.charAt(7)) * 2 ;
		
		ultimoNumero = 11 - suma%11;
		
		if(Character.getNumericValue(tercerParte) != ultimoNumero) throw new Exception ("ERROR. Cuit invalido : ultimo digito incorrecto.");

		return true;
	}
	
	public List<Turno> generarTurnosLibres(LocalDate fecha) {
		// Genera una lista con T.ODOS los turnos de ese dia. (Turnos libres)
		
		if(lstDiaRetiro.size() == 0) {
			return null;
		}
		
		DiaRetiro diaRetiro = this.traerDiaRetiro(fecha);
		if (diaRetiro == null) return null;
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
		// Trae los carritos que su fecha de retiro es igual a la fecha pasada por parametro.
		List <Carrito> carritosFechaRetiro = this.traerCarritosRetiroLocal(fecha);
		for(Carrito carrito: carritosFechaRetiro) {
			horaEncontrada = false;
			i = 0;
			entregaCarrito = (RetiroLocal) carrito.getEntrega();
			
			// Recorre toda la lista de turnos fijandose si algun carrito tiene turno, si es asi: marca
			// la hora de su turno como ocupado.
			while( !horaEncontrada && turnos.size() > i ) {
				if( (entregaCarrito.getHoraEntrega() != null) 
						&& (entregaCarrito.getHoraEntrega().equals(turnos.get(i).getHora())) ) {
					turnos.get(i).setOcupado(true);
					horaEncontrada = true;
				}
				i++;
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
	
	public List<LocalTime> traerHoraRetiro (LocalDate fecha) {
		
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
		
		if(this.diaRetiroExiste(nuevoRetiro) != -1) throw new Exception ("ERROR. El dia de retiro ya existe");
		
		return this.lstDiaRetiro.add(nuevoRetiro);
	}
	
	public DiaRetiro traerDiaRetiro(LocalDate fecha) {
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
	
	public DiaRetiro traerDiaRetiro(int id) {
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
	
	public LocalDate verificarFechaDiaRetiro(LocalDate fecha) {
		// Este metodo se usara cuando el usuario ingrese una fecha de retiro donde no exista un dia de retiro. 
		// Se generara una nueva fecha proxima al dia que se elegio.
		boolean fechaValida = false;
		while(!fechaValida) {
			if(this.traerDiaRetiro(fecha) == null) 
				fecha = fecha.plusDays(1);
			else 
				fechaValida = true;
		}
		
		return fecha;
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
	
	public boolean eliminarDiaRetiro(int id) throws Exception {
		
		DiaRetiro diaRetiroAEliminar = this.traerDiaRetiro(id);
		
		int pos = this.diaRetiroExiste(diaRetiroAEliminar);
		
		if ( pos == -1 ) throw new Exception ("ERROR. No existe el dia retiro que se quiere eliminar.");
		
		lstDiaRetiro.remove(pos);
		return true;
	}
	
	public boolean modificarDiaRetiro(int diaSemana, LocalTime horaDesde, LocalTime horaHasta, int intervalo) throws Exception {
		DiaRetiro diaRetiroModificado = this.traerDiaRetiro(diaSemana);
		
		if ( diaRetiroModificado == null ) throw new Exception ("ERROR. El dia de retiro seleccionado no existe.");
		
		diaRetiroModificado.setHoraDesde(horaDesde);
		diaRetiroModificado.setHoraHasta(horaHasta);
		diaRetiroModificado.setIntervalo(intervalo);
		return true;
	}
	
	public boolean agregarCarrito(LocalDate fecha, LocalTime hora, Cliente cliente, Entrega entrega) throws Exception {	
		Carrito nuevoCarrito = new Carrito(getIdCarrito(), fecha, hora,cliente,entrega);
		
		if(this.carritoExiste(nuevoCarrito) != -1) throw new Exception ("Error. El carrito que se quiere agregar ya existe.");
		
		if(cliente.getId() == 0 ) {
			cliente.setId(this.getIdCliente());
		}
		if(entrega instanceof RetiroLocal) {
			// (Si el dia de retiro seleccionado el comercio no hace entregas entonces se le asigna el proximo dia que si haga entregas.)
			entrega.setFecha(this.verificarFechaDiaRetiro(entrega.getFecha()));
		}
		nuevoCarrito.setComercio(this);
		return this.lstCarrito.add(nuevoCarrito);
	}
	
	public boolean agregarCarrito(LocalDate fecha, LocalTime hora, Cliente cliente) throws Exception {
		Carrito nuevoCarrito = new Carrito(getIdCarrito(), fecha, hora,cliente);
		
		if(this.carritoExiste(nuevoCarrito) != -1) throw new Exception ("ERROR. El carrito ya existe");
		// Aca no agrego el if del otro metodo sobrecargado porque se usa el set de retiro y ahi se verifica lo
		// mismo que en el anterior. (Si el dia de retiro seleccionado el comercio no hace entregas entonces se le asigna el proximo dia que si haga entregas.)
		if(cliente.getId() == 0 ) {
			cliente.setId(this.getIdCliente());
		}
		nuevoCarrito.setComercio(this);
		return this.lstCarrito.add(nuevoCarrito);
	}
	
	public boolean agregarCarrito(Cliente cliente) throws Exception {
		Carrito nuevoCarrito = new Carrito(getIdCarrito(), LocalDate.now(), LocalTime.now(),cliente);
		
		if(this.carritoExiste(nuevoCarrito) != -1) throw new Exception ("ERROR. El carrito ya existe");
		// Aca no agrego el if del otro metodo sobrecargado porque se usa el set de retiro y ahi se verifica lo
		// mismo que en el anterior. (Si el dia de retiro seleccionado el comercio no hace entregas entonces se le asigna el proximo dia que si haga entregas.)
		if(cliente.getId() == 0 ) {
			cliente.setId(this.getIdCliente());
		}
		nuevoCarrito.setComercio(this);
		return this.lstCarrito.add(nuevoCarrito);
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
	
	public List<Carrito> traerCarritosRetiroLocal(LocalDate fecha) {
		//Devuelve una lista con los carritos que tienen la misma fecha de retiro por local que la fecha
		// pasada por parametro.
		List <Carrito> carritosFecha = new ArrayList<Carrito>();
		Entrega carritoEntrega = null;
		
		for(Carrito carrito : lstCarrito) {			
			carritoEntrega = carrito.getEntrega();
			if(carritoEntrega instanceof RetiroLocal) {
				if(carritoEntrega.getFecha().equals(fecha)) {
					carritosFecha.add(carrito);
				}
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
		
		if ( pos == -1 ) throw new Exception ("ERROR. No se pudo eliminar el carrito");
		
		lstCarrito.remove(pos);
		return true;
	}

	
	/*CRUD ARTICULOS*/
	
	private int getIdArticulo() {
		int id = 0;
		if (lstArticulo.size() != 0) {
			// Le suma 1 al ultimo id guardado en la lista.
			id = lstArticulo.get(lstArticulo.size()-1).getId() + 1;
		}
		return id;
	}
	
	public int articuloExiste(Articulo articulo) {
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
		if(precio < 0) throw new Exception("ERROR. El precio no puede ser 0 o negativo");
		
		Articulo nuevoArticulo = new Articulo(getIdArticulo(), nombre,codBarras,precio);
		
		if(this.articuloExiste(nuevoArticulo) != -1) throw new Exception ("ERROR. El articulo ya existe");
		
		return this.lstArticulo.add(nuevoArticulo);

	}
	
	public Articulo traerArticulo(int id) throws Exception {
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
		if(!existe) throw new Exception("ERROR. El articulo no existe en el supermercado.");
		return articulo;
	}
	
	
	public boolean eliminarArticulo(int id) throws Exception {
		
		Articulo articuloAEliminar = this.traerArticulo(id);

		int pos = this.articuloExiste(articuloAEliminar);
		
		if ( pos == -1 ) throw new Exception ("ERROR. No se pudo eliminar el articulo");
		lstArticulo.remove(pos);
		
		return true;
	}
		
}