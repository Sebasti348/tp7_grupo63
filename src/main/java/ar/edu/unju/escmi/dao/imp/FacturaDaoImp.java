package ar.edu.unju.escmi.dao.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import ar.edu.unju.escmi.config.EmfSingleton;
import ar.edu.unju.escmi.dao.IFacturaDao;
import ar.edu.unju.escmi.entities.Factura;

public class FacturaDaoImp implements IFacturaDao {
	private static EntityManager manager = EmfSingleton.getInstance().getEmf().createEntityManager();

	@Override
	public void guardarFactura(Factura factura) {
		try {
			manager.getTransaction().begin();
			manager.persist(factura);
			manager.getTransaction().commit();
		} catch (Exception e) {
			if (manager.getTransaction() != null) {
				manager.getTransaction().rollback();
			}
			System.out.println("No se pudo guardar el objeto producto");
		} finally {
			manager.close();
		}
	}

	@Override
	public Factura obtenerFacturaPorNumero(Long numeroFactura) {
		return manager.find(Factura.class, numeroFactura);
	}

	@Override
	public void eliminarFactura(Factura factura) {
		try {
			manager.getTransaction().begin();
			factura.setEstado(false);
			manager.merge(factura);
			manager.getTransaction().commit();
		} catch (Exception e) {
			if (manager.getTransaction().isActive()) {
				manager.getTransaction().rollback();
			}
			System.out.println("No se pudo eliminar el producto.");
		} finally {
			manager.close();
		}

	}

	@Override
	public List<Factura> obtenerFacturas() {
		TypedQuery<Factura> query = manager.createQuery("Select l from Producto l", Factura.class);
		List<Factura> Facturas = query.getResultList();
		return Facturas;
	}

}
