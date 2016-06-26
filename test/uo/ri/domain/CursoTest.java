package uo.ri.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uo.ri.amp.model.Curso;
import uo.ri.amp.model.Fragmento;
import uo.ri.amp.model.TipoVehiculo;
import uo.ri.model.exception.BusinessException;

public class CursoTest {
	
	private Curso curso;
	private Fragmento fragmento1;
	private Fragmento fragmento2;
	private TipoVehiculo coche;
	private TipoVehiculo moto;
	
	@Before
	public void setUp() {
		
		curso=new Curso("RI", 11111l, "Repositorio de informacion", 60.0);
		
		coche = new TipoVehiculo("coche", 50.0,40);
		moto = new TipoVehiculo("moto", 30.0,40);
		
		fragmento1=new Fragmento(coche, 40d);
		fragmento2=new Fragmento(curso,moto, 20d);
		
		
		
	}

	@Test
	public void anadirFragmentos() throws BusinessException {
		assertTrue(curso.getFragmentos().size()==1);
		
		curso.anadirFragmento(fragmento1);
		
		assertTrue(curso.getFragmentos().size()==2);
		
	}
	
	@Test( expected = BusinessException.class )
	public void testAnadirFragmentoNoPosible() throws BusinessException {
		assertTrue(curso.getFragmentos().size()==1);
		
		curso.anadirFragmento(fragmento1);
		
		assertTrue(curso.getFragmentos().size()==2);
		
		curso.anadirFragmento(new Fragmento(coche, 20d));
		
	}
	

	@Test
	public void eliminarFragmentos() throws BusinessException {
		assertTrue(curso.getFragmentos().size()==1);
		
		curso.anadirFragmento(fragmento1);
		
		assertTrue(curso.getFragmentos().size()==2);
		
		curso.eliminarFragmento(fragmento2);
		
		assertTrue(curso.getFragmentos().size()==1);
		
		curso.eliminarFragmento(fragmento1);
		
		assertTrue(curso.getFragmentos().size()==0);
		
		curso.anadirFragmento(fragmento1);
		
		assertTrue(curso.getFragmentos().size()==1);
		
		
		
	}
	
	@Test( expected = BusinessException.class )
	public void testEliminarFragmentoNoPosible() throws BusinessException {
		assertTrue(curso.getFragmentos().size()==1);
		
		curso.anadirFragmento(fragmento1);
		
		assertTrue(curso.getFragmentos().size()==2);
		
		curso.eliminarFragmento(new Fragmento(coche, 100d));
		
	}

}
