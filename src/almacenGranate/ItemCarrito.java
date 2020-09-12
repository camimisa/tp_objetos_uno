package almacenGranate;

public class ItemCarrito {

	private Articulo articulo;
	private int cantidad;

	public ItemCarrito(Articulo articulo, int cantidad) {
		this.articulo = articulo;
		this.cantidad = cantidad;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "ItemCarrito [articulo=" + articulo + ", cantidad=" + cantidad + "]";
	}

	
	// se calcula el sub total (precio * cantidad)
public double calcularSubTotal() {
		double resultado=0;
		resultado=articulo.getPrecio()*cantidad;
		return resultado;
		
	}
	
	
	
}
