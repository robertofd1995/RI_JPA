package uo.ri.associations;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import uo.ri.amp.model.Association;
import uo.ri.amp.model.Cliente;
import uo.ri.amp.model.Vehiculo;
import uo.ri.model.exception.BusinessException;


public class PoseerTest {
	private Vehiculo vehiculo;
	private Cliente cliente;

	@Before
	public void setUp() throws BusinessException {
		cliente = new Cliente("dni-cliente", "nombre", "apellidos");
		vehiculo = new Vehiculo("1234 GJI", "seat", "ibiza");
		Association.Poseer.link(cliente, vehiculo );
	}
	
	@Test
	public void testPoseerAdd() throws BusinessException {
		assertTrue( cliente.getVehiculos().contains( vehiculo ));
		assertTrue( vehiculo.getCliente() == cliente );
	}

	@Test
	public void testPoseerRemove() throws BusinessException {
		Association.Poseer.unlink(cliente, vehiculo);

		assertTrue( ! cliente.getVehiculos().contains( vehiculo ));
		assertTrue( vehiculo.getCliente() == null );
	}

}
