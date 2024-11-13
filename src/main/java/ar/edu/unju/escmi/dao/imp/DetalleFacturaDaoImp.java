package ar.edu.unju.escmi.dao.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import ar.edu.unju.escmi.config.EmfSingleton;
import ar.edu.unju.escmi.dao.IDetalleFactura;
import ar.edu.unju.escmi.entities.DetalleFactura;

public class DetalleFacturaDaoImp implements IDetalleFactura {
	private static EntityManager manager = EmfSingleton.getInstance().getEmf().createEntityManager();


	@Override
	public void guardarDetalle(DetalleFactura detalleFactura) {
        try {
            manager.getTransaction().begin();
            manager.persist(detalleFactura);
            manager.getTransaction().commit();
        } catch (Exception e) {
            if (manager.getTransaction().isActive()) {
                manager.getTransaction().rollback();
            }
            System.out.println("No se pudo guardar el detalle de factura.");
        }

	}

	@Override
	public DetalleFactura obtenerDetallePorId(Long id) {
		return manager.find(DetalleFactura.class, id);
	}

	@Override
	public List<DetalleFactura> obtenerFacturasDetalles() {
		TypedQuery<DetalleFactura> query = manager.createQuery("Select l from DetalleFactura l", DetalleFactura.class);
		List<DetalleFactura> DetalleFacturas = query.getResultList();
		return DetalleFacturas;
	}

}
