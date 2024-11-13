package ar.edu.unju.escmi.dao.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import ar.edu.unju.escmi.config.EmfSingleton;
import ar.edu.unju.escmi.dao.IClienteDao;
import ar.edu.unju.escmi.entities.Cliente;


public class ClienteDaoImp implements IClienteDao {
	private static EntityManager manager = EmfSingleton.getInstance().getEmf().createEntityManager();

	@Override
	public void guardarCliente(Cliente cliente) {
		try {
			manager.getTransaction().begin();
			manager.persist(cliente);
			manager.getTransaction().commit();
		}catch(Exception e) {
			if(manager.getTransaction() != null) {
				manager.getTransaction().rollback();
			}
			System.out.println("No se pudo guardar el objeto autor");
		}finally {
			manager.close();
		}

	}

	@Override
	public void modificarCliente(Cliente cliente) {
		try {
			manager.getTransaction().begin();
			manager.merge(cliente);
			manager.getTransaction().commit();
		}catch(Exception e) {
			if(manager.getTransaction() != null) {
				manager.getTransaction().rollback();
			}
			System.out.println("No se pudo modificar el autor");
		}finally {
			manager.close();
		}
		
	}

	@Override
	public Cliente obtenerClienteId(Long idcliente) {
		return manager.find(Cliente.class, idcliente);
	}

	@Override
	public List<Cliente> mostrarClientes() {
		TypedQuery<Cliente> query = manager.createQuery("Select l from Cliente l",Cliente.class);
		List<Cliente> Clientes = query.getResultList();
		return Clientes;	
	}


}
