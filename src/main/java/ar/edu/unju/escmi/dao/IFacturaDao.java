package ar.edu.unju.escmi.dao;

import java.util.List;

import ar.edu.unju.escmi.entities.Factura;

public interface IFacturaDao {
	public void guardarFactura(Factura factura);

	public Factura obtenerFacturaPorNumero(Long numeroFactura);

	public void eliminarFactura(Factura factura);

	public List<Factura> obtenerFacturas();
}
