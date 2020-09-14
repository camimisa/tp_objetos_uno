package almacenGranate;

public abstract class Actor {

	protected int id;
	protected Contacto contacto;

	public Actor(int id, Contacto contacto) {
		this.id = id;
		this.contacto = contacto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Contacto getContacto() {
		return contacto;
	}

	public void setContacto(Contacto contacto) {
		this.contacto = contacto;
	}

	@Override
	public String toString() {
		return "Actor [id=" + id + ", contacto=" + contacto + "]";
	}
	
	public Ubicacion traerUbicacion() {
		return contacto.getUbicacion();
	}


	protected abstract boolean validarIdentificadorUnico() throws Exception;




}
