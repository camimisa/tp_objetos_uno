package almacenGranate;

import java.time.LocalDate;

public abstract class Entrega {

	protected int id;
	protected LocalDate fecha;
	protected boolean efectivo;

	public Entrega(int id, LocalDate fecha, boolean efectivo) {
		this.id = id;
		this.setFecha(fecha);
		this.efectivo = efectivo;
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
		// La fecha maxima de retiro o de envio puede ser de 2 semanas.
		LocalDate fechaMaxima = LocalDate.now().plusDays(14);
		
		if( fecha.isBefore(fechaMaxima) || fecha.equals(fechaMaxima) )
			this.fecha = fecha;
		else
			this.fecha = fechaMaxima;
	}

	public boolean getEfectivo() {
		return efectivo;
	}

	public void setEfectivo(boolean efectivo) {
		this.efectivo = efectivo;
	}

	@Override
	public String toString() {
		return "Entrega [id=" + id + ", fecha=" + fecha + ", efectivo=" + efectivo + "]";
	}

}
