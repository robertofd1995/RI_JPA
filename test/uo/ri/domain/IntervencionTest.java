package uo.ri.domain;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import uo.ri.amp.model.Association;
import uo.ri.amp.model.Averia;
import uo.ri.amp.model.Cliente;
import uo.ri.amp.model.Intervencion;
import uo.ri.amp.model.Mecanico;
import uo.ri.amp.model.Repuesto;
import uo.ri.amp.model.Sustitucion;
import uo.ri.amp.model.TipoVehiculo;
import uo.ri.amp.model.Vehiculo;


public class IntervencionTest {
	
	private Mecanico mecanico;
	private Averia averia;
	private Intervencion intervencion;
	private Repuesto repuesto;
	private Sustitucion sustitucion;
	private Vehiculo vehiculo;
	private TipoVehiculo tipoVehiculo;
	private Cliente cliente;

	@Before
	public void setUp() {
		cliente = new Cliente("dni-cliente", "nombre", "apellidos");
		vehiculo = new Vehiculo("1234 GJI", "ibiza", "seat");
		Association.Poseer.link(cliente, vehiculo);

		tipoVehiculo = new TipoVehiculo("coche", 50.0,40);
		Association.Clasificar.link(tipoVehiculo, vehiculo);

		averia = new Averia(vehiculo, "falla la junta la trocla",200d);
		mecanico = new Mecanico("dni-mecanico", "nombre", "apellidos");
	
		intervencion = new Intervencion(mecanico, averia);
		intervencion.setMinutos(60);
		
		repuesto = new Repuesto("R1001", "junta la trocla", 100.0);
		sustitucion = new Sustitucion(repuesto, intervencion);
		sustitucion.setCantidad(2);
	}
	
	@Test
	public void testImporteIntervencion() {
		assertTrue( intervencion.getImporte() == 250.0 );
	}

}
