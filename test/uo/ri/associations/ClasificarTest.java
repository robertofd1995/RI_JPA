package uo.ri.associations;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import uo.ri.amp.model.Association;
import uo.ri.amp.model.TipoVehiculo;
import uo.ri.amp.model.Vehiculo;
import uo.ri.model.exception.BusinessException;


public class ClasificarTest {
	private Vehiculo vehiculo;
	private TipoVehiculo tipoVehiculo;

	@Before
	public void setUp() throws BusinessException {
		vehiculo = new Vehiculo("1234 GJI", "seat", "ibiza");
		tipoVehiculo = new TipoVehiculo("coche", 50.0,40);
		Association.Clasificar.link(tipoVehiculo, vehiculo);
	}
	
	@Test
	public void testClasificarAdd() throws BusinessException {
		assertTrue( tipoVehiculo.getVehiculos().contains( vehiculo ));
		assertTrue( vehiculo.getTipo() == tipoVehiculo );
	}

	@Test
	public void testClasificarRemove() throws BusinessException {
		Association.Clasificar.unlink(tipoVehiculo, vehiculo);

		assertTrue( ! tipoVehiculo.getVehiculos().contains( vehiculo ));
		assertTrue( vehiculo.getTipo() == null );
	}

}
