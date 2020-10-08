package almacenGranate;

public class Ubicacion {

	private double latitud;
	private double longitud;

	public Ubicacion(double latitud, double longitud) {
		this.latitud = latitud;
		this.longitud = longitud;
	}

	public double getLatitud() {
		return latitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

	public double getLongitud() {
		return longitud;
	}

	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	@Override
	public String toString() {
		return "latitud: " + latitud + ", longitud: " + longitud;
	}
	
	public boolean equals(Ubicacion ubicacion) {
		if ((ubicacion.getLatitud() == this.latitud) && (ubicacion.getLongitud() == this.longitud))
			return true;
		return false;
	}
}
