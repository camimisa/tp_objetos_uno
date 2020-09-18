package almacenGranate;

import java.time.LocalDate;
import java.time.LocalTime;

public class RetiroLocal extends Entrega {

	private LocalTime horaEntrega;

	// Elimine el hora entrega del constuctor porque eso se asigna cuando se cierra el carrito.
	public RetiroLocal(int id, LocalDate fecha, boolean efectivo) {
		super(id, fecha, efectivo);
		this.horaEntrega = null;
	}

	public LocalTime getHoraEntrega() {
		return horaEntrega;
	}

	public void setHoraEntrega(LocalTime horaEntrega) {
		this.horaEntrega = horaEntrega;
	}

	@Override
	public String toString() {
		return "RetiroLocal [horaEntrega=" + horaEntrega + "]";
	}

	
}
