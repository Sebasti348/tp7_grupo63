package ar.edu.unju.escmi.entities;

public class Producto {

	private long id;
	private String descripcion;
	private double precioUnitario;
	private boolean estado;
	
	public Producto() {
		// TODO Auto-generated constructor stub
	}
	
	public Producto(String descripcion, double precioUnitario, boolean estado) {
		super();
		this.descripcion = descripcion;
		this.precioUnitario = precioUnitario;
		this.estado = estado;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", descripcion=" + descripcion + ", precioUnitario=" + precioUnitario
				+ ", estado=" + estado + "]";
	}
	
}
