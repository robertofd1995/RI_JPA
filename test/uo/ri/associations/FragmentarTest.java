package uo.ri.associations;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uo.ri.amp.model.Association;
import uo.ri.amp.model.Curso;
import uo.ri.amp.model.Fragmento;
import uo.ri.amp.model.TipoVehiculo;
import uo.ri.model.exception.BusinessException;

public class FragmentarTest {

	
	private Curso curso;
	private Fragmento fragmento1;
	private Fragmento fragmento2;
	private TipoVehiculo coche;
	private TipoVehiculo moto;
	

	@Before
	public void setUp() throws BusinessException {
		curso=new Curso("RI", 11111l, "Repositorio de informacion", 60.0);
		
		coche = new TipoVehiculo("coche", 50.0,40);
		moto = new TipoVehiculo("moto", 30.0,40);
		
		fragmento1=new Fragmento(coche, 40d);
		fragmento2=new Fragmento(curso,moto, 20d);
		
		Association.Fragmentar.link(curso, fragmento1);
		
		
	}
	
	@Test
	public void testFragmentarAdd() throws BusinessException {
		assertTrue( curso.getFragmentos().contains( fragmento1 ));
		assertTrue( fragmento1.getCurso() == curso );
		
		assertTrue( curso.getFragmentos().contains( fragmento2 ));
		assertTrue( fragmento2.getCurso() == curso );
		
		
	}

	@Test
	public void testArreglarRemove() throws BusinessException {
		Association.Fragmentar.unlink(curso,fragmento1);
		
		assertTrue( ! curso.getFragmentos().contains( fragmento1 ));
		assertTrue( curso.getFragmentos().size() == 1 );
		assertTrue( fragmento1.getCurso() == null );
		
		
		Association.Fragmentar.unlink(curso,fragmento2);
		
		assertTrue( ! curso.getFragmentos().contains( fragmento2 ));
		assertTrue( curso.getFragmentos().size() == 0 );
		assertTrue( fragmento2.getCurso() == null );
	}


}
