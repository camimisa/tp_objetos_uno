package almacenGranate;

import java.util.regex.Pattern;

public class Contacto {

	private String email;
	private String celular;
	private Ubicacion ubicacion;

	public Contacto(String email, String celular, Ubicacion ubicacion)throws Exception {
		this.setEmail(email);
		this.setCelular(celular);
		this.ubicacion = ubicacion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email)throws Exception {
		if(!validarEmail(email)) throw new Exception("ERROR. Email invalido");
		this.email = email;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular)throws Exception {
		if(!validarCelular(celular)) throw new Exception("ERROR. Celular invalido");
		this.celular = celular;
	}

	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Ubicacion ubicacion) {
		this.ubicacion = ubicacion;
	}

	@Override
	public String toString() {
		return "Contacto [email=" + email + ", celular=" + celular + ", ubicacion=" + ubicacion + "]";
	}
	
	private boolean validarEmail(String email) {
		//retorna true si cumple el siguiente patron: Cualquier caracter que no sea @.
		//Le sigue un solo arroba. Le siguen cualquiera de los caracteres que no sea @
		//Le sigue un unico. Le sigue por lo menos dos numero de la a a la z
		return Pattern.matches("^[^@]+@[^@]+\\.[a-zA-Z]{2,}$", email);
	}
	
	private boolean validarCelular(String celular) {
		//Returna true si cumple el patron: Minimo 10 numeros. Solos numero del 0 al 9
		return Pattern.matches("[0-9]{10,}", celular);
	}


}

