package almacenGranate;
import java.util.regex.Pattern;

public class Cliente extends Actor {

	private String apellido;
	private String nombre;
	private int dni;

	public Cliente(int id, Contacto contacto, String apellido, String nombre, int dni) throws Exception {
		super(id, contacto);
		this.setApellido(apellido);
		this.setNombre(nombre);
		this.setDni(dni);
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido)throws Exception {
		if(!validarNombreApellido(apellido)) throw new Exception("ERROR. Apellido incorrecto");
		this.apellido = apellido;
	}
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) throws Exception {
		if(!validarNombreApellido(nombre)) throw new Exception("ERROR. Nombre incorrecto");
		this.nombre = nombre;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) throws Exception{
		this.dni = dni;
		//Validar dni espera un string para hacer la validacion, por eso lo tengo que castear.
		if(!validarIdentificadorUnico()) throw new Exception("ERROR. Dni invalido");

	}

	@Override
	public String toString() {
		return "Cliente [apellido=" + apellido + ", nombre=" + nombre + ", dni=" + dni + "]";
	}
	
	public boolean validarNombreApellido(String nombre) throws Exception {
		//Retorna true si cumple con el siguiente patron: Solo letras mayusculas o minusculas de la a la z
		return Pattern.matches("^[a-zA-Z_]+$", nombre);
	}


	@Override
	protected boolean validarIdentificadorUnico() {
		String auxDni = String.valueOf(dni);
		//Retorna true si cumple con el siguiente patron: 7 u 8 numeros del 0 al 9
				return Pattern.matches("[0-9]{7,8}", auxDni);
	}

}
