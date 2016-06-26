package uo.ri.amp.business.impl.admin.asistencias;

import java.util.Date;

import uo.ri.amp.model.Asistencia;
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
		
		Asistencia asistenciaBD=AsistenciaFinder.findByKey(new AsistenciaKey(CursoFinder.findByCodigo(codigoCurso).getId(), MecanicoFinder.findByDni(dniMecanico).getId(), fincio));
		
		asistenciaBD.setAsistencia(pasitencia);
		asistenciaBD.setStatus(status);
		asistenciaBD.setfFinal(ffinal);
		
		return asistenciaBD;
	}

}
