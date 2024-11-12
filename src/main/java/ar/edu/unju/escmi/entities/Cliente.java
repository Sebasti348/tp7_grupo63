package ar.edu.unju.escmi.entities;

public class Cliente {

	private long id;
	private String nombre;
	private String apellido;
	private String domicilio;
	private int dni;
	private boolean estado;

	// Constructor sin parámetros
	public Cliente() {
	}

	// Constructor con parámetros
	public Cliente(String nombre, String apellido, String domicilio, int dni, boolean estado) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.domicilio = domicilio;
		this.dni = dni;
		this.estado = estado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", domicilio=" + domicilio
				+ ", dni=" + dni + ", estado=" + estado + "]";
	}

}
