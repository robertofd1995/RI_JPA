package uo.ri.amp.persistence;

import java.util.List;

import uo.ri.amp.business.impl.asserts.AssertCurso;
import uo.ri.amp.model.Curso;
import uo.ri.model.exception.BusinessException;
import uo.ri.persistence.util.Jpa;

public class CursoFinder {
	
	public static Curso findById(Long idCurso) {
		Curso curso= Jpa.getManager().find(Curso.class, idCurso);
		return curso;
	}
	
	public static List<Curso> findAll() {
		
		List<Curso> cursos= Jpa.getManager().createNamedQuery("Curso.findAll",Curso.class).getResultList();
		for (Curso curso : cursos) {
			curso.getFragmentos().size();
		}
		
		return cursos;
	}

	public static Curso findByCodigo(Long codigoCurso) throws BusinessException {
		
		Curso c;
		try {
			c= (Curso) Jpa.getManager().createNamedQuery("Curso.findByCodigo",Curso.class)
					.setParameter(1, codigoCurso).getSingleResult();
		} catch (Exception e) {
			throw new BusinessException("No existe curso con codigo : "+codigoCurso );
		}
		 AssertCurso.NotNull(c, codigoCurso);
		  c.getAsistencias().size();
		  
		 return c;
	}

	public static Long existeCurso(Long codigo) {
	
		return Jpa.getManager().createNamedQuery("Curso.existeCurso",Long.class)
				.setParameter(1, codigo).getSingleResult();
	}

}
