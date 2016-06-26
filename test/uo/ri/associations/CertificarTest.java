package uo.ri.associations;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import alb.util.date.DateUtil;
import uo.ri.amp.model.Asistencia;
import uo.ri.amp.model.Association;
import uo.ri.amp.model.Certificado;
import uo.ri.amp.model.Curso;
import uo.ri.amp.model.Fragmento;
import uo.ri.amp.model.Mecanico;
import uo.ri.amp.model.TipoVehiculo;
import uo.ri.amp.model.types.AsistenciaStatus;
import uo.ri.model.exception.BusinessException;

@SuppressWarnings("unused")
public class CertificarTest {

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
	
	private Certificado certificadoCoche;
	private Certificado certificadoMoto;
	
	

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
		
		asistencia3=new Asistencia(mecanico2, curso, new Date(DateUtil.yesterday().getTime()), new Date(DateUtil.today().getTime()),90,AsistenciaStatus.NO_APTO);
		asistencia4=new Asistencia(curso2, new Date(DateUtil.yesterday().getTime()), new Date(DateUtil.today().getTime()));
		
		asistencia4.anadirAsistencia(mecanico2,90, AsistenciaStatus.NO_APTO);
		
		asistencia5=new Asistencia(curso, new Date(DateUtil.today().getTime()), new Date(DateUtil.fromString("04/02/2016").getTime()));
		
		asistencia5.anadirAsistencia(mecanico2,90 ,AsistenciaStatus.APTO);
		
		certificadoCoche=new Certificado(coche);
		certificadoCoche.generarCertificado(mecanico);
		
		certificadoMoto=new Certificado(mecanico, moto);
		certificadoMoto.generarCertificado(mecanico);
		
		certificadoCoche.generarCertificado(mecanico2);
	}
	
	@Test
	public void testCertificadoAdd() throws BusinessException {
		assertTrue( mecanico.getCertificados().contains(certificadoCoche));
		assertTrue( mecanico2.getCertificados().contains(certificadoCoche));
		assertTrue( mecanico.getCertificados().contains(certificadoMoto));
	}

	@Test
	public void testCertificadoRemove() throws BusinessException {
		Association.Certificar.unlink(certificadoCoche, mecanico);
		assertFalse( mecanico.getCertificados().contains(certificadoCoche));
	}
	
	@Test(expected=BusinessException.class)
	public void testCertificadoAnadirNoValido() throws BusinessException {
		certificadoMoto.generarCertificado(mecanico2);
	}


}
