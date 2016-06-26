package uo.ri.amp.business.impl.admin.cursos;

import uo.ri.amp.business.impl.asserts.AssertCurso;
import uo.ri.amp.model.Association;
import uo.ri.amp.model.Curso;
import uo.ri.amp.model.Fragmento;
import uo.ri.amp.persistence.CursoFinder;
import uo.ri.business.impl.Command;
import uo.ri.model.exception.BusinessException;
import uo.ri.persistence.util.Jpa;

public class DeleteCurso implements Command {

	private Long idCurso;

	public DeleteCurso(Long idCurso) {
		this.idCurso=idCurso;
	}

	@Override
	public Curso execute() throws BusinessException {
		
		Curso c=CursoFinder.findByCodigo(idCurso);
		
		for (Fragmento fragmento : c.getFragmentos()) {
			Association.Fragmentar.unlink(fragmento);
			Jpa.getManager().remove(fragmento);
		}
		
		AssertCurso.NotNull(c, idCurso);
		AssertCurso.CanBeDelated(c);
		
		Jpa.getManager().remove(c);
		
		return c;
	}

}
