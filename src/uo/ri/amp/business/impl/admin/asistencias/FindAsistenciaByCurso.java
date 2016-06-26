package uo.ri.amp.business.impl.admin.asistencias;

import java.util.List;

import uo.ri.amp.model.Asistencia;
import uo.ri.amp.model.Curso;
import uo.ri.amp.persistence.AsistenciaFinder;
import uo.ri.business.impl.Command;
import uo.ri.model.exception.BusinessException;

public class FindAsistenciaByCurso implements Command {

	private Curso curso;

	public FindAsistenciaByCurso(Curso curso) {
		this.curso=curso;
	}

	@Override
	public List<Asistencia> execute() throws BusinessException {
		
		return AsistenciaFinder.findAllByCurso(curso);
	}

}
