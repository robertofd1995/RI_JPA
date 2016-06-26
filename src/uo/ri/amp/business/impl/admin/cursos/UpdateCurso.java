package uo.ri.amp.business.impl.admin.cursos;

import uo.ri.amp.model.Curso;
import uo.ri.amp.persistence.CursoFinder;
import uo.ri.business.impl.Command;
import uo.ri.model.exception.BusinessException;

public class UpdateCurso implements Command {

	private Curso curso;

	public UpdateCurso(Curso curso) {
		this.curso=curso;
	}

	@Override
	public Curso execute() throws BusinessException {
		
		Curso cursoBd=CursoFinder.findById(curso.getId());

		cursoBd.setDescripcion(curso.getDescripcion());
		cursoBd.setNombre(curso.getNombre());
		cursoBd.setTotalHoras(curso.getTotalHoras());
		
		return curso;
	}

}
