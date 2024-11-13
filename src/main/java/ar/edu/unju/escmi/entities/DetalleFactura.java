package ar.edu.unju.escmi.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DetalleFactura")
public class DetalleFactura {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	@JoinColumn(name = "producto_id")
	private Producto producto;

	@Column(nullable = false)
	private int cantidad;
	@Column(nullable = false)
	private double subtotal;

	@ManyToOne
	@JoinColumn(name = "factura_id")
	private Factura factura;
	
	public DetalleFactura() {
		// TODO Auto-generated constructor stub
	}
	
	
	public DetalleFactura(Producto producto, int cantidad, double subtotal) {
		super();
		this.producto = producto;
		this.cantidad = cantidad;
		this.subtotal = subtotal;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	@Override
	public String toString() {
		return "DetalleFactura [id=" + id + ", cantidad=" + cantidad + ", subtotal=" + subtotal + "]";
	}
	
}
