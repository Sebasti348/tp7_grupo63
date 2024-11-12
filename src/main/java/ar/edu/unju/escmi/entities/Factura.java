package ar.edu.unju.escmi.entities;

import java.time.LocalDate;

public class Factura {

	private long id;
	private LocalDate fecha;
	private String domicilio;
	private double total;
	private boolean estado;
	private Cliente cliente; 
	

	public Factura() {
		// TODO Auto-generated constructor stub
	}

	public Factura(LocalDate fecha, Cliente cliente, String domicilio, double total, boolean estado) {
		super();
		this.fecha = fecha;
		this.cliente = cliente;
		this.domicilio = domicilio;
		this.total = total;
		this.estado = estado;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = true;
	}

	@Override
	public String toString() {
		return "Factura [id=" + id + ", fecha=" + fecha + ", cliente=" + cliente + ", domicilio=" + domicilio
				+ ", total=" + total + ", estado=" + estado + "]";
	}

}
