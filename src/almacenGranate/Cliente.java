package almacenGranate;
import java.util.regex.Pattern;

public class Cliente extends Actor {

	private String apellido;
	private String nombre;
	private long dni;
	private char sexo;

	public Cliente(Contacto contacto, String apellido, String nombre, long dni , char sexo) throws Exception {
		super(0, contacto);
		this.setApellido(apellido);
		this.setNombre(nombre);
		this.setDni(dni);
		this.setSexo(sexo);
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
		
		this.dni = dni;
	}
	

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) throws Exception {
		if(!validarSexo(sexo)) throw new Exception("ERROR. Sexo invalido. Ingrese 'm' o 'f'"); 
		this.sexo = sexo;
	}

	@Override
	public String toString() {
		return id +" " + apellido + " " + nombre + " dni: " + dni + "\n";
	}
	
	private boolean validarNombreApellido(String nombre) throws Exception {
		//Retorna true si cumple con el siguiente patron: Solo letras mayusculas o minusculas de la a la z
		return Pattern.matches("^[a-zA-Z\s]+$", nombre);
	}
	
	private boolean validarSexo(char sexo){
		String auxSexo = String.valueOf(sexo);
		//Retorna true si cumple con el siguiente patron: m o f mayuscula o minuscula
		return Pattern.matches("[mMfF]{1}", auxSexo);
	}


	@Override
	protected boolean validarIdentificadorUnico(long identificador) {
		String auxDni = String.valueOf(identificador);
		//Retorna true si cumple con el siguiente patron: 7 u 8 numeros del 0 al 9
				return Pattern.matches("[0-9]{7,8}", auxDni);
	}

}
