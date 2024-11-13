package ar.edu.unju.escmi.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Productos")
public class Producto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false)
	private String descripcion;
	@Column(name = "PU")
	private double precioUnitario;
	@Column(nullable = false)
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