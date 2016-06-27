package uo.ri.amp.business.impl.admin.asistencias;

import java.util.Date;

import uo.ri.amp.business.impl.asserts.AssertCurso;
import uo.ri.amp.model.Asistencia;
import uo.ri.amp.model.Curso;
import uo.ri.amp.model.Mecanico;
import uo.ri.amp.model.types.AsistenciaKey;
import uo.ri.amp.model.types.AsistenciaStatus;
import uo.ri.amp.persistence.AsistenciaFinder;
import uo.ri.amp.persistence.CursoFinder;
import uo.ri.amp.persistence.MecanicoFinder;
import uo.ri.business.impl.Command;
import uo.ri.model.exception.BusinessException;

public class UpdateAsistencia implements Command {

	
	private String dniMecanico;
	private Long codigoCurso;
	private Date fincio;
	private Date ffinal;
	private double pasitencia;
	private AsistenciaStatus status;

	public UpdateAsistencia(String dniMecanico,Long codigoCurso,Date finicio,
			Date ffinal,Double pasistencia,AsistenciaStatus status) {
		
		this.dniMecanico=dniMecanico;
		this.codigoCurso=codigoCurso;
		this.fincio=finicio;
		this.ffinal=ffinal;
		this.pasitencia=pasistencia;
		this.status=status;
		
	}

	@Override
	public Asistencia execute() throws BusinessException {

		Curso curso = CursoFinder.findByCodigo(codigoCurso);
		AssertCurso.NotNull(curso, codigoCurso);

		Mecanico mecanico =MecanicoFinder.findByDni(dniMecanico);
		if (mecanico == null) {
			throw new BusinessException("Mecanico con dni : " + dniMecanico + " no encontrado");
		}

		Asistencia asistenciaBD=AsistenciaFinder.findByKey
				(new AsistenciaKey(curso.getId(),mecanico.getId(), fincio));

		if (asistenciaBD==null){ throw new BusinessException("No se ha encontrado la asistencia");}
		
		asistenciaBD.setAsistencia(pasitencia);
		asistenciaBD.setStatus(status);
		asistenciaBD.setfFinal(ffinal);
		
		return asistenciaBD;
	}

}
