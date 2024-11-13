package ar.edu.unju.escmi.dao;

import java.util.List;

import ar.edu.unju.escmi.entities.Cliente;

public interface IClienteDao {
	public void guardarCliente(Cliente cliente);

	public void modificarCliente(Cliente cliente);

	public Cliente obtenerClienteId(Long idcliente);

	public List<Cliente> mostrarClientes();
}
