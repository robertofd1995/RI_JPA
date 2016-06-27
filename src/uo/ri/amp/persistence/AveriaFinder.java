package uo.ri.amp.persistence;

import java.util.List;

import uo.ri.amp.model.Averia;
import uo.ri.model.exception.BusinessException;
import uo.ri.persistence.util.Jpa;

public class AveriaFinder {

	public static List<Averia> findByMatricula(String matricula){
		return (List<Averia>) Jpa.getManager().createNamedQuery("Averia.findByMatricula", Averia.class)
				.setParameter(1, matricula).getResultList();
	}

	public static Averia findById(Long idAveria) throws BusinessException {
		Averia averia= Jpa.getManager().find(Averia.class, idAveria);
		
		if (averia==null) {
			throw new BusinessException("No se a encontrado ninguna averia con el id : " + idAveria);
		}
		averia.getVehiculo().getTipo();
		
		return averia;
	}

	public static Object findAll() {
		
		return Jpa.getManager().createNamedQuery("Averia.findAll",Averia.class).getResultList();
	}



}
