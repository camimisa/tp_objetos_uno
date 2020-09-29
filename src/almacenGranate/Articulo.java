package almacenGranate;

import java.util.regex.Pattern;

public class Articulo {

	private int id;
	private String nombre;
	private String codBarras;
	private double precio;

	public Articulo(int id, String nombre, String codBarras, double precio) throws Exception {
		this.id = id;
		this.nombre = nombre;
		this.setCodBarras(codBarras);
		this.setPrecio(precio);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodBarras() {
		return codBarras;
	}

	public void setCodBarras(String codBarras) throws Exception {
		if(!this.validarCodBarras(codBarras)) throw new Exception("ERROR. Codigo de barras incorrecto.");
			
		this.codBarras = codBarras;
					
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		
		this.precio = 0;
		
		if(precio >= 0) {
			this.precio = precio;
		}
	}

	@Override
	public String toString() {
		
		String espacios = "\t\t";
		
		if(nombre.length() > 9) {
			espacios = "\t";
		}
		
		if (nombre.length() > 13) {
			nombre = nombre.substring(0, 13);
		}
		
		return id + "\t" + nombre + "" + espacios + "" + precio + "\t" + codBarras;
	}

	public boolean equals(Articulo articulo) {
		
		boolean flag = false;
		
		if(this.codBarras == articulo.getCodBarras())
			flag = true;
		
		return flag;
	}
	
	private boolean validarCodBarras(String codBarras) throws Exception {
        int sumaCodBarras = 0;
        int multiploDeDiez = 0;
        int finalSumaCogBarras = 0;
        int digitoVerificador;
        
        
        
        if (!Pattern.matches("[0-9]{13}", codBarras)) throw new Exception("Error: el codigo de barras tiene que tener 13 digitos"); // SI EL COD TIENE 13 DIGITOS ENTRE EN EL IF
        
        for (int i = 0 ; i < codBarras.length() - 1; i++) {// HAGO UN FOR RECORRIENDO 12
            if (i % 2 != 0) {// SI ES IMPAR
                sumaCodBarras += (Character.getNumericValue(codBarras.charAt(i)) * 3);
            }
            else { // SI ES PAR
            	sumaCodBarras += (Character.getNumericValue(codBarras.charAt(i)));
            }
        }
        
        if( sumaCodBarras%10 == 0 ) {
        	finalSumaCogBarras = sumaCodBarras;
        }
        else {
        	multiploDeDiez = sumaCodBarras / 10;
            multiploDeDiez++;
            finalSumaCogBarras = multiploDeDiez * 10;
        }
        
        digitoVerificador = finalSumaCogBarras - sumaCodBarras;
        if (digitoVerificador != Character.getNumericValue(codBarras.charAt(codBarras.length() - 1))) {// VERIFICA EL ULTIMO DIGITO
        	throw new Exception("Error: el ultimo digito es invalido");
        } 
        
        return true;
	}

}
