package ar.edu.unju.escmi.dao;

import java.util.List;

import ar.edu.unju.escmi.entities.Producto;

public interface IProductoDao {
	public void guardarProducto(Producto producto);

	public void eliminarProducto(Producto producto);  	

	public void modificarPrecioProducto(Long idProducto, double nuevoPrecio);

	public List<Producto> obtenerProductos();

	public Producto obtenerProductoPorId(Long idProducto);

}
