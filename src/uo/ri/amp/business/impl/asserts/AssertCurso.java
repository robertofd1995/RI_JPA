package uo.ri.amp.business.impl.asserts;

import uo.ri.amp.model.Curso;
import uo.ri.conf.ServicesFactory;
import uo.ri.model.exception.BusinessException;

public class AssertCurso {
	
	public static void CanBeDelated(Curso c) throws BusinessException {
		if (!(c.getAsistencias().isEmpty()))
			throw new BusinessException("No se puede borrar un curso cuyo que tiene asistencias");
		
	}

	public static void NotNull(Curso c,Long idCurso) throws BusinessException {
		if (c == null)
			throw new BusinessException("No se encontro ningun curso con ID: " + idCurso);
		
	}
	
	public static void NotRepeated(Curso curso) throws BusinessException {
		
		Long i=ServicesFactory.getAdminService().existeCurso(curso.getCodigo());
		
		if (i != 0)
			throw new BusinessException("Ya existe un curso con este codigo ");
		
	}

}
