package uo.ri.amp.business.impl.asserts;


import java.util.Date;

import uo.ri.conf.ServicesFactory;
import uo.ri.model.exception.BusinessException;

public class AssertAsistencia {
	
	
/**
 * Este metodo comprobara si existe ya una asitencia con esta clave , en caso de que si lanza una excepcion	
 * @param dniMecanico
 * @param codigoCurso
 * @param finicio
 * @throws BusinessException
 */
	public static void NotRepeated(String dniMecanico, Long codigoCurso, Date finicio) throws BusinessException {
			
		Long i=ServicesFactory.getAdminService().existeAsistencia(dniMecanico,codigoCurso,finicio);
		
		if (i != 0)
			throw new BusinessException("Ya existe esta asistencia , intenta modificarla en lugar de crear una nueva");
			
	}


	public static void Existe(String dniMecanico, Long codigoCurso, Date finicio) throws BusinessException {
		
		Long i=ServicesFactory.getAdminService().existeAsistencia(dniMecanico,codigoCurso,finicio);
		
		if (i == 0)
			throw new BusinessException("La asistencia que estas intentando eliminar no existe");
		
	}
}
