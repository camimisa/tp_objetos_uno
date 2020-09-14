package almacenGranate;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class RetiroLocal extends Entrega {

	private LocalTime horaEntrega;

	public RetiroLocal(int id, LocalDate fecha, boolean efectivo, LocalTime horaEntrega) {
		super(id, fecha, efectivo);
		this.horaEntrega = horaEntrega;
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
