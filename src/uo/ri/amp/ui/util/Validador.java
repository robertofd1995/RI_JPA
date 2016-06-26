package uo.ri.amp.ui.util;



import java.util.Date;

import alb.util.date.DateUtil;
import uo.ri.model.exception.BusinessException;

public final class Validador {
	
	
	public static Date validarFecha(String fecha) throws BusinessException{
		
		try {
			Date fechaVa=new Date(DateUtil.fromString(fecha).getTime());
			return fechaVa;
		} 
		catch (RuntimeException e) {
			
			throw new BusinessException("\nADVERTENCIA :Formato no valido , la fecha ha de tener el formato dd/mm/yyyy");
		}
		
	}
	
	
	
	
	

}
