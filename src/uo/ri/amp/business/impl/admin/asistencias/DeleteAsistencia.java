package uo.ri.amp.business.impl.admin.asistencias;

import uo.ri.amp.model.Asistencia;
import uo.ri.amp.model.types.AsistenciaKey;
import uo.ri.amp.persistence.AsistenciaFinder;
import uo.ri.business.impl.Command;
import uo.ri.model.exception.BusinessException;
import uo.ri.persistence.util.Jpa;

public class DeleteAsistencia implements Command {

	private AsistenciaKey asistencia;

	public DeleteAsistencia(AsistenciaKey asistencia) {
		this.asistencia=asistencia;
	}

	@Override
	public Asistencia execute() throws BusinessException {
		
		Asistencia a=AsistenciaFinder.findByKey(asistencia);

		if (a==null){ throw new BusinessException("No se ha encontrado la asistencia");}
		
		Jpa.getManager().remove(a);
		
		return a;
	}

}
