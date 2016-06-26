package uo.ri.amp.business.impl.asserts;

import uo.ri.amp.model.Fragmento;
import uo.ri.amp.model.types.FragmentoKey;
import uo.ri.model.exception.BusinessException;

public class AssertFragmento {
	
	public static void CanBeDelated(Fragmento f) throws BusinessException {
		if (f.getCurso()!=null)
			throw new BusinessException("No se puede borrar un fragmento que esta vinculado a un curso");
		
	}
	
	public static void NotNull(Fragmento f,FragmentoKey key) throws BusinessException {
		if (f == null)
			throw new BusinessException("No se encontro ningun curso con Key: " + key);
		
	}

}
