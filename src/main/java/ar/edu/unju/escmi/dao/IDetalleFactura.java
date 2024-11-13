package ar.edu.unju.escmi.dao;

import java.util.List;

import ar.edu.unju.escmi.entities.DetalleFactura;

public interface IDetalleFactura {
	public void guardarDetalle(DetalleFactura detalleFactura);

	public DetalleFactura obtenerDetallePorId(Long id);
	
	public List<DetalleFactura> obtenerFacturasDetalles();
}
