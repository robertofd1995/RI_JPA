package uo.ri.associations;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import uo.ri.amp.model.Association;
import uo.ri.amp.model.Averia;
import uo.ri.amp.model.Cliente;
import uo.ri.amp.model.Mecanico;
import uo.ri.amp.model.TipoVehiculo;
import uo.ri.amp.model.Vehiculo;
import uo.ri.model.exception.BusinessException;


public class AsignarTest {
	private Mecanico mecanico;
	private Averia averia;
	private Vehiculo vehiculo;
	private TipoVehiculo tipoVehiculo;
	private Cliente cliente;

	@Before
	public void setUp() throws BusinessException {
		cliente = new Cliente("dni-cliente", "nombre", "apellidos");
		vehiculo = new Vehiculo("1234 GJI", "seat", "ibiza");
		Association.Poseer.link(cliente, vehiculo );

		tipoVehiculo = new TipoVehiculo("coche", 50.0,40);
		Association.Clasificar.link(tipoVehiculo, vehiculo);
		
		averia = new Averia(vehiculo, "falla la junta la trocla",200d);

		mecanico = new Mecanico("dni-mecanico", "nombre", "apellidos");
	}
	
	@Test
	public void testAsignarAdd() throws BusinessException {
		Association.Asignar.link(mecanico, averia);
		
		assertTrue( mecanico.getAsignadas().contains( averia ));
		assertTrue( averia.getMecanico() == mecanico );
	}

	@Test
	public void testAsignarRemove() throws BusinessException {
		Association.Asignar.unlink(mecanico, averia );
		
		assertTrue( ! mecanico.getAsignadas().contains( averia ));
		assertTrue( mecanico.getAsignadas().size() == 0 );
		assertTrue( averia.getMecanico() == null );
	}

}
