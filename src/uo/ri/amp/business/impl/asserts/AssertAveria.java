package uo.ri.amp.business.impl.asserts;

import uo.ri.amp.model.Averia;
import uo.ri.model.exception.BusinessException;
import uo.ri.model.types.AveriaStatus;

public final class AssertAveria {
	
	public static void CanBeDelated(Averia a) throws BusinessException {
		if (!(a.getStatus()==AveriaStatus.ABIERTA))
			throw new BusinessException("No se puede borrar una averia cuyo estado sea distinto de ABIERTA");
		
	}

	public static void NotNull(Averia a,Long idAveria) throws BusinessException {
		if (a == null)
			throw new BusinessException("No se encontro ninguna averia con ID: " + idAveria);
		
	}

	public static boolean Abierta(Averia a) {
		return a.getStatus()==AveriaStatus.ABIERTA;
	}

}
