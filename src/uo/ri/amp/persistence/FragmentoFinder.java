package uo.ri.amp.persistence;

import java.util.List;

import uo.ri.amp.model.Curso;
import uo.ri.amp.model.Fragmento;
import uo.ri.amp.model.types.FragmentoKey;
import uo.ri.persistence.util.Jpa;

public class FragmentoFinder {
	
	public static Fragmento findById(FragmentoKey key) {
		Fragmento curso= Jpa.getManager().find(Fragmento.class, key);
		return curso;
	}
	

	public static List<Fragmento> findAllByCurso(Curso curso) {
		
		return Jpa.getManager().createNamedQuery("Fragmento.findByCursoId",Fragmento.class).setParameter(1, curso.getId()).getResultList();
	}

}
