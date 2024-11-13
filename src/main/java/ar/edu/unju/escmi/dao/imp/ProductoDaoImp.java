package ar.edu.unju.escmi.dao.imp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import ar.edu.unju.escmi.config.EmfSingleton;
import ar.edu.unju.escmi.dao.IProductoDao;
import ar.edu.unju.escmi.entities.Producto;

public class ProductoDaoImp implements IProductoDao {
	private static EntityManager manager = EmfSingleton.getInstance().getEmf().createEntityManager();

	@Override
	public void guardarProducto(Producto producto) {
		try {
			manager.getTransaction().begin();
			manager.persist(producto);
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
	public void eliminarProducto(Producto producto) {
		try {
			manager.getTransaction().begin();
			producto.setEstado(false); 
			manager.merge(producto);
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
	public void modificarPrecioProducto(Long idProducto, double nuevoPrecio) {
		try {
			manager.getTransaction().begin();
			Producto producto = manager.find(Producto.class, idProducto);
			if (producto != null) {
				producto.setPrecioUnitario(nuevoPrecio);
				manager.merge(producto);
			}
			manager.getTransaction().commit();
		} catch (Exception e) {
			if (manager.getTransaction().isActive()) {
				manager.getTransaction().rollback();
			}
			System.out.println("No se pudo modificar el precio del producto.");
		} finally {
			//manager.close();
		}

	}

	@Override
	public List<Producto> obtenerProductos() {
		TypedQuery<Producto> query = manager.createQuery("Select l from Producto l", Producto.class);
		List<Producto> Productos = query.getResultList();
		return Productos;
	}

	@Override
	public Producto obtenerProductoPorId(Long idProducto) {
		return manager.find(Producto.class, idProducto);
	}

}
