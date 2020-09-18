package almacenGranate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class RetiroLocal extends Entrega {

	private LocalTime horaEntrega;

	// Elimine el hora entrega del constuctor porque eso se asigna cuando se cierra el carrito.
	public RetiroLocal(int id, LocalDate fecha, boolean efectivo) {
		super(id, fecha, efectivo);
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

	public void setHoraEntrega(Comercio comercio, LocalDate fecha) {
		List <Turno> listaTurnosLibres = comercio.traerTurnosLibres(fecha);
		
		this.horaEntrega = listaTurnosLibres.get(0).getHora();
		listaTurnosLibres.get(0).setOcupado(true);
		
	}
	
}
