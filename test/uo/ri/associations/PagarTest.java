package uo.ri.associations;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import alb.util.date.DateUtil;
import uo.ri.amp.model.Association;
import uo.ri.amp.model.Averia;
import uo.ri.amp.model.Cargo;
import uo.ri.amp.model.Cliente;
import uo.ri.amp.model.Factura;
import uo.ri.amp.model.Intervencion;
import uo.ri.amp.model.Mecanico;
import uo.ri.amp.model.Metalico;
import uo.ri.amp.model.Repuesto;
import uo.ri.amp.model.Sustitucion;
import uo.ri.amp.model.TipoVehiculo;
import uo.ri.amp.model.Vehiculo;
import uo.ri.model.exception.BusinessException;
import uo.ri.model.types.AveriaStatus;


public class PagarTest {
	private Mecanico mecanico;
	private Averia averia;
	private Intervencion intervencion;
	private Repuesto repuesto;
	private Sustitucion sustitucion;
	private Vehiculo vehiculo;
	private TipoVehiculo tipoVehiculo;
	private Cliente cliente;
	private Factura factura;
	private Metalico metalico;
	private Cargo cargo;

	@Before
	public void setUp() throws BusinessException {
		cliente = new Cliente("dni-cliente", "nombre", "apellidos");
		vehiculo = new Vehiculo("1234 GJI", "seat", "ibiza");
		Association.Poseer.link(cliente, vehiculo );

		tipoVehiculo = new TipoVehiculo("coche", 50.0,40);
		Association.Clasificar.link(tipoVehiculo, vehiculo);
		
		averia = new Averia(vehiculo, "falla la junta la trocla",200d);

		mecanico = new Mecanico("dni-mecanico", "nombre", "apellidos");
	
		intervencion = new Intervencion(mecanico, averia);
		intervencion.setMinutos(60);
		
		repuesto = new Repuesto("R1001", "junta la trocla", 100.0);
		sustitucion = new Sustitucion(repuesto, intervencion);
		sustitucion.setCantidad(2);
		
		factura = new Factura(0L, DateUtil.today());
		averia.setStatus(AveriaStatus.TERMINADA);
		factura.addAveria( averia );
		
		metalico = new Metalico( cliente );
		
		cargo = new Cargo(factura, metalico, 100.0);
	}
	
	@Test
	public void testPagarAdd() throws BusinessException {
		assertTrue( cliente.getMediosPago().contains( metalico ));
		assertTrue( metalico.getCliente() == cliente );
	}

	@Test
	public void testPagarRemove() throws BusinessException {
		Association.Pagar.unlink(cliente, metalico);
		
		assertTrue( ! cliente.getMediosPago().contains( metalico ));
		assertTrue( cliente.getMediosPago().size() == 0 );
		assertTrue( metalico.getCliente() == null );
	}

	@Test
	public void testCargarAdd() throws BusinessException {
		assertTrue( metalico.getCargos().contains( cargo ));
		assertTrue( factura.getCargos().contains( cargo ));
		
		assertTrue( cargo.getFactura() == factura );
		assertTrue( cargo.getMedioPago() == metalico );
		
		assertTrue( metalico.getAcumulado() == 100.0 );
	}

	@Test
	public void testCargarRemove() throws BusinessException {
		Association.Cargar.unlink( cargo );
		
		assertTrue( ! metalico.getCargos().contains( cargo ));
		assertTrue( metalico.getCargos().size() == 0 );

		assertTrue( ! factura.getCargos().contains( cargo ));
		assertTrue( metalico.getCargos().size() == 0 );
		
		assertTrue( cargo.getFactura() == null );
		assertTrue( cargo.getMedioPago() == null );
		
		assertTrue( metalico.getAcumulado() == 0.0 );
	}

}
