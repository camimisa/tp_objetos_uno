package almacenGranate;
import java.util.regex.Pattern;

public class Cliente extends Actor {

	private String apellido;
	private String nombre;
	private long dni;

	public Cliente(int id, Contacto contacto, String apellido, String nombre, long dni) throws Exception {
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

	public long getDni() {
		return dni;
	}

	public void setDni(long dni) throws Exception{
		//Validar dni espera un string para hacer la validacion, por eso lo tengo que castear.
		if(!validarIdentificadorUnico(dni)) throw new Exception("ERROR. Dni invalido");
		else this.dni = dni;

	}

	@Override
	public String toString() {
		return "" + apellido + " " + nombre + " dni: " + dni + "\n";
	}
	
	public boolean validarNombreApellido(String nombre) throws Exception {
		//Retorna true si cumple con el siguiente patron: Solo letras mayusculas o minusculas de la a la z
		return Pattern.matches("^[a-zA-Z\s]+$", nombre);
	}


	@Override
	protected boolean validarIdentificadorUnico(long identificador) {
		String auxDni = String.valueOf(identificador);
		//Retorna true si cumple con el siguiente patron: 7 u 8 numeros del 0 al 9
				return Pattern.matches("[0-9]{7,8}", auxDni);
	}

}
