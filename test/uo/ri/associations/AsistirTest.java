package uo.ri.associations;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import alb.util.date.DateUtil;
import uo.ri.amp.model.Asistencia;
import uo.ri.amp.model.Association;
import uo.ri.amp.model.Curso;
import uo.ri.amp.model.Fragmento;
import uo.ri.amp.model.Mecanico;
import uo.ri.amp.model.TipoVehiculo;
import uo.ri.amp.model.types.AsistenciaStatus;
import uo.ri.model.exception.BusinessException;

@SuppressWarnings("unused")
public class AsistirTest {

	private Curso curso;
	private Fragmento fragmento1;
	private Fragmento fragmento2;
	private TipoVehiculo coche;
	private TipoVehiculo moto;
	
	private Mecanico mecanico;
	private Mecanico mecanico2;
	private Curso curso2;
	private Fragmento fragmento3;
	private Fragmento fragmento4;
	private Fragmento fragmento5;
	private TipoVehiculo quad;
	
	private Asistencia asistencia;
	private Asistencia asistencia2;
	private Asistencia asistencia3;
	private Asistencia asistencia4;
	private Asistencia asistencia5;
	
	

	@Before
	public void setUp() throws BusinessException {
		
		mecanico=new Mecanico("71666688X", "pepe", "fernadez pepe");
		mecanico2=new Mecanico("71999Y", "juan", "diaz juan");
		
		curso=new Curso("Principios de motores de combustion", 11122l, "Explicacion del funcionamiento de los motores de combustion", 60.0);
		
		coche = new TipoVehiculo("coche", 50.0,40);
		moto = new TipoVehiculo("moto", 30.0,40);
		quad = new TipoVehiculo("quad", 20.0,50);
		
		fragmento1=new Fragmento(curso,coche, 40d);
		fragmento2=new Fragmento(curso,moto, 20d);
		
		curso2=new Curso("Mantenimiento del vehiculo", 11122l, "Procedimientos basicos sobre el mantenimiento de un vehiculo", 100.0);
		
		
		fragmento3=new Fragmento(curso2,coche, 40d);
		fragmento4=new Fragmento(curso2,moto, 20d);
		fragmento5=new Fragmento(curso2,quad,40d);
		
		asistencia=new Asistencia(mecanico, curso, new Date(DateUtil.yesterday().getTime()), new Date(DateUtil.today().getTime()),90,AsistenciaStatus.APTO);
		asistencia2=new Asistencia(mecanico, curso2, new Date(DateUtil.yesterday().getTime()), new Date(DateUtil.today().getTime()),90,AsistenciaStatus.APTO);
		
		asistencia3=new Asistencia(mecanico2, curso, new Date(DateUtil.yesterday().getTime()), new Date(DateUtil.today().getTime()),88,AsistenciaStatus.NO_APTO);
		asistencia4=new Asistencia(curso2, new Date(DateUtil.yesterday().getTime()), new Date(DateUtil.today().getTime()));
		
		asistencia4.anadirAsistencia(mecanico2,90, AsistenciaStatus.NO_APTO);
		
		asistencia5=new Asistencia(curso, new Date(DateUtil.today().getTime()), new Date(DateUtil.fromString("04/02/2016").getTime()));
		
		asistencia5.anadirAsistencia(mecanico2,90,AsistenciaStatus.APTO);
			
	}
	
	@Test
	public void testAsistirAdd() throws BusinessException {
		
		assertTrue( mecanico.getAsitencias().contains( asistencia ));
		assertTrue( curso.getAsistencias().contains( asistencia ));
		assertTrue( asistencia.getCurso() == curso );
		assertTrue(asistencia.getMecanico() == mecanico);
		assertTrue(asistencia.getStatus() == AsistenciaStatus.APTO);
		
		
		assertTrue( mecanico.getAsitencias().contains( asistencia2 ));
		assertTrue( curso2.getAsistencias().contains( asistencia2 ));
		assertTrue( asistencia2.getCurso() == curso2 );
		assertTrue(asistencia2.getMecanico() == mecanico);
		assertTrue(asistencia2.getStatus() == AsistenciaStatus.APTO);
		
		assertTrue( mecanico.getAsitencias().size()==2);
		
		assertTrue( mecanico2.getAsitencias().contains( asistencia3 ));
		assertTrue( curso.getAsistencias().contains( asistencia3 ));
		assertTrue( asistencia3.getCurso() == curso );
		assertTrue(asistencia3.getMecanico() == mecanico2);
		assertTrue(asistencia3.getStatus() == AsistenciaStatus.NO_APTO);
		
		assertTrue( mecanico2.getAsitencias().contains( asistencia4 ));
		assertTrue( curso2.getAsistencias().contains( asistencia4 ));
		assertTrue( asistencia4.getCurso() == curso2 );
		assertTrue(asistencia4.getMecanico() == mecanico2);
		assertTrue(asistencia4.getStatus() == AsistenciaStatus.NO_APTO);
		
		assertTrue( mecanico2.getAsitencias().contains( asistencia5 ));
		assertTrue( curso.getAsistencias().contains( asistencia5 ));
		assertTrue( asistencia5.getCurso() == curso );
		assertTrue(asistencia5.getMecanico() == mecanico2);
		assertTrue(asistencia5.getStatus() == AsistenciaStatus.APTO);
		
		assertTrue( mecanico2.getAsitencias().size()==3);
		
		assertTrue( curso.getAsistencias().size()==3);
		assertTrue( curso2.getAsistencias().size()==2);
		
	}
	
	@Test
	public void testAsistirRemove() throws BusinessException {
		
		Association.Asistir.unlink(curso, asistencia, mecanico);
		
		assertFalse( mecanico.getAsitencias().contains( asistencia ));
		assertFalse( curso.getAsistencias().contains( asistencia ));
		assertFalse(asistencia.getCurso() == curso );
		assertFalse(asistencia.getMecanico() == mecanico);
		
		assertTrue( mecanico.getAsitencias().size()==1);
		assertTrue( curso.getAsistencias().size()==2);
		
		Association.Asistir.unlink(curso2, asistencia2, mecanico);
		
		assertFalse( mecanico.getAsitencias().contains( asistencia2 ));
		assertFalse( curso2.getAsistencias().contains( asistencia2 ));
		assertFalse( asistencia2.getCurso() == curso2 );
		assertFalse(asistencia2.getMecanico() == mecanico);
		
		assertTrue( mecanico.getAsitencias().size()==0);
		assertTrue( mecanico2.getAsitencias().size()==3);
		
		Association.Asistir.unlink(curso, asistencia3, mecanico2);
		
		assertFalse( mecanico2.getAsitencias().contains( asistencia3 ));
		assertFalse( curso.getAsistencias().contains( asistencia3 ));
		assertFalse( asistencia3.getCurso() == curso );
		assertFalse(asistencia3.getMecanico() == mecanico2);
		
		assertTrue( mecanico2.getAsitencias().size()==2);
		
		Association.Asistir.unlink(curso2, asistencia4, mecanico2);
		
		assertFalse( mecanico2.getAsitencias().contains( asistencia4 ));
		assertFalse( curso2.getAsistencias().contains( asistencia4 ));
		assertFalse( asistencia4.getCurso() == curso2 );
		assertFalse(asistencia4.getMecanico() == mecanico2);
		
		assertTrue( mecanico2.getAsitencias().size()==1);
		
		Association.Asistir.unlink(curso, asistencia5, mecanico2);
		
		assertFalse( mecanico2.getAsitencias().contains( asistencia5 ));
		assertFalse( curso.getAsistencias().contains( asistencia5 ));
		assertFalse( asistencia5.getCurso() == curso );
		assertFalse(asistencia5.getMecanico() == mecanico2);
		
		assertTrue( mecanico2.getAsitencias().size()==0);
		
		assertTrue( curso.getAsistencias().size()==0);
		assertTrue( curso2.getAsistencias().size()==0);
		
	}

	/*@Test
	public void testAsistirRemove() throws BusinessException {
		Association.Fragmentar.unlink(curso,fragmento1);
		
		assertTrue( ! curso.getFragmentos().contains( fragmento1 ));
		assertTrue( curso.getFragmentos().size() == 1 );
		assertTrue( fragmento1.getCurso() == null );
		
		
		Association.Fragmentar.unlink(curso,fragmento2);
		
		assertTrue( ! curso.getFragmentos().contains( fragmento2 ));
		assertTrue( curso.getFragmentos().size() == 0 );
		assertTrue( fragmento2.getCurso() == null );
	}*/

}
