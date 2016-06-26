package uo.ri.amp.business.impl.admin.cursos;

import uo.ri.amp.persistence.CursoFinder;
import uo.ri.business.impl.Command;
import uo.ri.model.exception.BusinessException;

public class ExisteCurso implements Command {

	private Long codigo;

	public ExisteCurso(Long codigo) {
		this.codigo=codigo;
	}

	@Override
	public Long execute() throws BusinessException {
		
		return CursoFinder.existeCurso(codigo);
	}

}
