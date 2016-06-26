package uo.ri.amp.business.impl.admin.cursos;

import java.util.List;

import uo.ri.amp.model.Curso;
import uo.ri.amp.persistence.CursoFinder;
import uo.ri.business.impl.Command;
import uo.ri.model.exception.BusinessException;

public class FindAllCursos implements Command {

	@Override
	public List<Curso> execute() throws BusinessException {
		
		return CursoFinder.findAll();
	}

}
