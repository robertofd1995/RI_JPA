package uo.ri.amp.business.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import uo.ri.amp.business.AdminService;
import uo.ri.amp.business.impl.admin.GenerarCertificados;
import uo.ri.amp.business.impl.admin.asistencias.AddAsistencia;
import uo.ri.amp.business.impl.admin.asistencias.DeleteAsistencia;
import uo.ri.amp.business.impl.admin.asistencias.FindAsistenciaByCurso;
import uo.ri.amp.business.impl.admin.asistencias.UpdateAsistencia;
import uo.ri.amp.business.impl.admin.cursos.AddCurso;
import uo.ri.amp.business.impl.admin.cursos.DeleteCurso;
import uo.ri.amp.business.impl.admin.cursos.ExisteCurso;
import uo.ri.amp.business.impl.admin.cursos.FindAllCursos;
import uo.ri.amp.business.impl.admin.cursos.FindByCodigo;
import uo.ri.amp.business.impl.admin.cursos.FindCursoById;
import uo.ri.amp.business.impl.admin.cursos.UpdateCurso;
import uo.ri.amp.business.impl.admin.fragmentos.AddFragmento;
import uo.ri.amp.business.impl.admin.fragmentos.DeleteFragmento;
import uo.ri.amp.business.impl.admin.fragmentos.FindAllFragmentos;
import uo.ri.amp.business.impl.admin.fragmentos.FindFragmento;
import uo.ri.amp.business.impl.admin.fragmentos.UpdateFragmento;
import uo.ri.amp.business.impl.admin.mecanico.FindMecanicoDni;
import uo.ri.amp.business.impl.admin.mecanico.FormacionMecanico;
import uo.ri.amp.business.impl.admin.mecanico.FormacionPorTipo;
import uo.ri.amp.business.impl.admin.tipoVehiculo.FindAllTipoVehiculo;
import uo.ri.amp.business.impl.admin.tipoVehiculo.FindTipoVehiculoByNombre;
import uo.ri.amp.model.Asistencia;
import uo.ri.amp.model.Curso;
import uo.ri.amp.model.Fragmento;
import uo.ri.amp.model.Mecanico;
import uo.ri.amp.model.TipoVehiculo;
import uo.ri.amp.model.types.AsistenciaKey;
import uo.ri.amp.model.types.AsistenciaStatus;
import uo.ri.amp.model.types.FragmentoKey;
import uo.ri.business.impl.CommandExecutor;
import uo.ri.business.impl.admin.AddMechanic;
import uo.ri.business.impl.admin.DeleteMechanic;
import uo.ri.business.impl.admin.FindAllMechanics;
import uo.ri.business.impl.admin.FindMechanicById;
import uo.ri.business.impl.admin.UpdateMechanic;
import uo.ri.model.exception.BusinessException;

public class AdminServiceImpl implements AdminService {
	CommandExecutor executor=new CommandExecutor();

	@Override
	public void newMechanic(Mecanico mecanico) throws BusinessException {
		executor.execute(new AddMechanic( mecanico ));
	}

	@Override
	public void updateMechanic(Mecanico mecanico) throws BusinessException {
		executor.execute(new UpdateMechanic( mecanico ));
	}

	@Override
	public void deleteMechanic(Long idMecanico) throws BusinessException {
		executor.execute(new DeleteMechanic(idMecanico));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Mecanico> findAllMechanics() throws BusinessException {
		return (List<Mecanico>) executor.execute(new FindAllMechanics());
	}

	@Override
	public Mecanico findMechanicById(Long id) throws BusinessException {
		return (Mecanico) executor.execute(new FindMechanicById(id));
	}

	@Override
	public Curso findCursoById(Long id) throws BusinessException {
		return (Curso) executor.execute(new FindCursoById(id));
	}
	
	@Override
	public Curso findCursoByCodigo(Long codigo) throws BusinessException {
		
		return (Curso) executor.execute(new FindByCodigo(codigo));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Curso> findAllCursos() throws BusinessException {
		
		return (List<Curso>) executor.execute(new FindAllCursos());
	}
	
	@Override
	public Long existeCurso(Long codigo) throws BusinessException {
		
		return  (Long) executor.execute(new ExisteCurso(codigo));
	}

	@Override
	public void newCurso(Curso curso,List<HashMap<String,Object>> fragmentos) throws BusinessException {
		executor.execute(new AddCurso(curso,fragmentos));
		
	}

	@Override
	public void deleteCurso(Long idCurso) throws BusinessException {
		executor.execute(new DeleteCurso(idCurso));
		
	}

	@Override
	public void updateCurso(Curso curso) throws BusinessException {
		executor.execute(new UpdateCurso(curso));
		
	}

	@Override
	public Fragmento findFragmentoByKey(FragmentoKey key) throws BusinessException {
		// TODO Auto-generated method stub
		return (Fragmento) executor.execute(new FindFragmento(key));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Fragmento> findAllFragmentoByCurso(Curso curso) throws BusinessException {
		
		return (List<Fragmento>) executor.execute(new FindAllFragmentos(curso));
	}

	@Override
	public void newFragmento(Fragmento fragmento) throws BusinessException {
		executor.execute(new AddFragmento(fragmento));
		
	}

	@Override
	public void deleteFragmento(FragmentoKey key) throws BusinessException {
		executor.execute(new DeleteFragmento(key));
		
	}

	@Override
	public void updateFragmento(Fragmento fragmento) throws BusinessException {
		executor.execute(new UpdateFragmento(fragmento));
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TipoVehiculo> listarTiposVehiculos() throws BusinessException {
		
		return (List<TipoVehiculo>) executor.execute(new FindAllTipoVehiculo());
	}
	
	@Override
	public TipoVehiculo findTipoVehiculoByNombre(String nombre) throws BusinessException {
		
		return (TipoVehiculo) executor.execute(new FindTipoVehiculoByNombre(nombre));
	}
	

	

	@Override
	public Long existeAsistencia(String dniMecanico, Long codigoCurso, Date finicio) throws BusinessException {
		
		return (Long) executor.execute(new ExisteAsistencia(dniMecanico,codigoCurso,finicio));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Asistencia> findAllAsistenciasByCurso(Curso curso) throws BusinessException {
		return (List<Asistencia>) executor.execute(new FindAsistenciaByCurso(curso));
	}
	
	@Override
	public void AddAsistencia(String dniMecanico, Long codigoCurso, Date finicio, Date ffinal, double pasistencia,
			AsistenciaStatus status) throws BusinessException {
		executor.execute(new AddAsistencia(dniMecanico,codigoCurso,finicio,ffinal,pasistencia,status));
		
	}

	@Override
	public void updateAsistencia(String dniMecanico,Long codigoCurso,Date finicio,
			Date ffinal,Double pasistencia,AsistenciaStatus status) throws BusinessException {
		executor.execute(new UpdateAsistencia(dniMecanico,codigoCurso, finicio, ffinal, pasistencia,status));
		
	}
	
	@Override
	public void deleteAsistencia(AsistenciaKey asistenciakey) throws BusinessException {
		executor.execute(new DeleteAsistencia(asistenciakey));
		
	}

	@Override
	public void addFragmento(Fragmento fragmento) throws BusinessException {
		executor.execute(new AddFragmento(fragmento));
		
	}

	@Override
	public Mecanico findMecanicoByDni(String dniMecanico) throws BusinessException {

		return (Mecanico) executor.execute(new FindMecanicoDni(dniMecanico));
	}

	@Override
	public void generarCertificados() throws BusinessException {
		executor.execute(new GenerarCertificados());
		
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> listarFormacion(Long mecanico_id) throws BusinessException {
		return (HashMap<String, Object>) executor.execute(new FormacionMecanico(mecanico_id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<HashMap<String, Object>> listarFormacionPorTipos() throws BusinessException {
		
		return (ArrayList<HashMap<String, Object>>) executor.execute(new FormacionPorTipo());
	}

	

	

	

	

	



}
