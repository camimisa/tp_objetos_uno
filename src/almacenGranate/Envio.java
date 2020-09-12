package almacenGranate;
import java.time.LocalDate;
import java.time.LocalTime;

public class Envio extends Entrega{
	
	private LocalTime horasHasta;
	private LocalTime horaDesde;
	private double costo;
	private Ubicacion ubicacion;
	
	public Envio(int id, LocalDate fecha, boolean efectivo, LocalTime horasHasta, LocalTime horaDesde, double costo,
			Ubicacion ubicacion) {
		super(id, fecha, efectivo);
		this.horasHasta = horasHasta;
		this.horaDesde = horaDesde;
		this.costo = costo;
		this.ubicacion = ubicacion;
	}

	public LocalTime getHorasHasta() {
		return horasHasta;
	}

	public void setHorasHasta(LocalTime horasHasta) {
		this.horasHasta = horasHasta;
	}

	public LocalTime getHoraDesde() {
		return horaDesde;
	}

	public void setHoraDesde(LocalTime horaDesde) {
		this.horaDesde = horaDesde;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo=costo;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	@Override
	public String toString() {
		return "Envio [horasHasta=" + horasHasta + ", horaDesde=" + horaDesde + ", costo=" + costo + ", ubicacion="
				+ ubicacion + "]";
	}
	
	
	// Calcular distancia
	public double distanciaCoord(double lat1, double lng1, double lat2, double lng2) {
		double radioTierra = 6371; //en kilómetros
		double dLat = Math.toRadians(lat2 - lat1);
		double dLng = Math.toRadians(lng2 - lng1);
		double sindLat = Math.sin(dLat / 2);
		double sindLng = Math.sin(dLng / 2);
		double va1 =Math.pow(sindLat, 2)+Math.pow(sindLng, 2)*Math.cos(Math.toRadians(lat1))*
		Math.cos(Math.toRadians(lat2));
		double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));
		return radioTierra * va2;
		}
	
	public void setCosto(Ubicacion ubicacion, double costoFijo, double costoPorKm) {
		Cliente cliente;
		Ubicacion ubicacion2=new Ubicacion(cliente.getContacto().getUbicacion().getLatitud(),cliente.getContacto().getUbicacion().getLongitud());
		// uso la ubicacion de Cliente pero tengo que inicializarla para poder sacar el calculo de distancia
		
		this.ubicacion=ubicacion;
		
	this.costo=(distanciaCoord(ubicacion.getLatitud(), ubicacion.getLongitud(), ubicacion2.getLatitud(), ubicacion2.getLongitud())*costoPorKm)+costoFijo;
	// costo lo igualo el costo total del Costo fijo mas el costo de envio
	
	}
	

}
