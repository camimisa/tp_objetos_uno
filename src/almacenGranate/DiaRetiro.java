package almacenGranate;

import java.time.LocalTime;

public class DiaRetiro {

	private int id;
	private int diaSemana;
	private LocalTime horaDesde;
	private LocalTime horaHasta;
	private int intervalo;

	public DiaRetiro(int id, int diaSemana, LocalTime horaDesde, LocalTime horaHasta, int intervalo) throws Exception {
		this.id = id;
		this.setDiaSemana(diaSemana);
		this.horaDesde = horaDesde;
		this.horaHasta = horaHasta;
		this.setIntervalo(intervalo);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(int diaSemana) throws Exception {
		if( 0 < diaSemana && diaSemana < 8)
			this.diaSemana = diaSemana;
		else {
			this.diaSemana = 1;
			throw new Exception ("ERROR. Dia se semana invalido");
		}
	}

	public LocalTime getHoraDesde() {
		return horaDesde;
	}

	public void setHoraDesde(LocalTime horaDesde) {
		this.horaDesde = horaDesde;
	}

	public LocalTime getHoraHasta() {
		return horaHasta;
	}

	public void setHoraHasta(LocalTime horaHasta) {
		this.horaHasta = horaHasta;
	}

	public int getIntervalo() {
		return intervalo;
	}

	public void setIntervalo(int intervalo) {
		if (intervalo > 0)
			this.intervalo = intervalo;
		else 
			this.intervalo = 15;
	}

	@Override
	public String toString() {

		return "DiaRetiro [id=" + id + ", diaSemana=" + diaSemana + ", horaDesde=" + horaDesde + ", horaHasta="
				+ horaHasta + ", intervalo=" + intervalo + "]";
	}

	public boolean equals(DiaRetiro diaRetiro) {
		if(this.diaSemana == diaRetiro.getDiaSemana())
				return true;
		return false;
	}
}
