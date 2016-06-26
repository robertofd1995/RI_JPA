package uo.ri.domain;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import uo.ri.amp.model.Association;
import uo.ri.amp.model.Averia;
import uo.ri.amp.model.Cliente;
import uo.ri.amp.model.Factura;
import uo.ri.amp.model.Intervencion;
import uo.ri.amp.model.Mecanico;
import uo.ri.amp.model.Repuesto;
import uo.ri.amp.model.Sustitucion;
import uo.ri.amp.model.TipoVehiculo;
import uo.ri.amp.model.Vehiculo;
import uo.ri.model.exception.BusinessException;
import uo.ri.model.types.AveriaStatus;
import uo.ri.model.types.FacturaStatus;


public class AveriaTest {
	
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
	public void testImporteAveria() {
		averia.calcularImporte();
		assertTrue( averia.getImporte() == 250.0 );
	}

	@Test
	public void testImporteAveriaConDosIntervenciones() {
		Intervencion i = new Intervencion(new Mecanico("1", "a", "n"), averia);
		i.setMinutos(30);
		
		averia.calcularImporte();
		assertTrue( averia.getImporte() == 275.0 );
	}

	@Test( expected = BusinessException.class )
	public void testAveriaNoTerminadaException() throws BusinessException {
		List<Averia> averias = new ArrayList<Averia>();
		averias.add( averia );
		new Factura( 0L,  averias ); // salta excepcion: la averia no esta terminada
	}

	@Test
	public void testFacturaCreadaSinAbonar() throws BusinessException {
		List<Averia> averias = new ArrayList<Averia>();
		averia.setStatus(AveriaStatus.TERMINADA);
		averias.add( averia );
		Factura factura = new Factura( 0L, averias );
		
		assertTrue( factura.getStatus() ==  FacturaStatus.SIN_ABONAR );
	}

}
