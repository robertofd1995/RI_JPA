package uo.ri.amp.business.impl.admin.cursos;

import uo.ri.amp.business.impl.asserts.AssertCurso;
import uo.ri.amp.model.Curso;
import uo.ri.amp.persistence.CursoFinder;
import uo.ri.business.impl.Command;
import uo.ri.model.exception.BusinessException;

public class FindCursoById implements Command {
	
	Long id;

	public FindCursoById(Long id) {
		this.id=id;
	}

	@Override
	public Curso execute() throws BusinessException {
		Curso r= CursoFinder.findById(id);
		
		AssertCurso.NotNull(r, id);
		return r;
	}

}
