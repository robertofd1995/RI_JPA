package uo.ri.amp.persistence;

import java.util.Date;
import java.util.List;

import uo.ri.amp.model.Asistencia;
import uo.ri.amp.model.Curso;
import uo.ri.amp.model.types.AsistenciaKey;
import uo.ri.model.exception.BusinessException;
import uo.ri.persistence.util.Jpa;

public class AsistenciaFinder {

	public static Asistencia findByKey(AsistenciaKey key) throws BusinessException {
		Asistencia asistencia= Jpa.getManager().createNamedQuery("Asistencia.findByKey",Asistencia.class)
				.setParameter(1, key.getCurso()).setParameter(2, key.getMecanico())
				.setParameter(3, key.getfInicio()).getResultList().get(0);

		return asistencia;
	}
	

	public static List<Asistencia> findAllByCurso(Curso curso) {
		
		return Jpa.getManager().createNamedQuery("Asistencia.findByCursoId",
				Asistencia.class).setParameter(1, curso.getCodigo()).getResultList();
	}

	public static Long existeAveria(String dni, Long codigo, Date finicio) {
		
		Long result= (Long) Jpa.getManager().createNamedQuery("Asistencia.existe",Long.class)
				.setParameter(2, dni).setParameter(1, codigo).setParameter(3, finicio).getSingleResult();
		return result;
	}

}
