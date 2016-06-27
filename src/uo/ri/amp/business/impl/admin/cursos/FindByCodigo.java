package uo.ri.amp.business.impl.admin.cursos;

import uo.ri.amp.business.impl.asserts.AssertCurso;
import uo.ri.amp.model.Curso;
import uo.ri.amp.persistence.CursoFinder;
import uo.ri.business.impl.Command;
import uo.ri.model.exception.BusinessException;

public class FindByCodigo implements Command {
	
	private Long codigoCurso;

	public FindByCodigo(Long idCurso) {
		this.codigoCurso=idCurso;
	}

	@Override
	public Curso execute() throws BusinessException {
		
		Curso curso= CursoFinder.findByCodigo(codigoCurso);

		AssertCurso.NotNull(curso, codigoCurso);

		return curso;
	}

}
