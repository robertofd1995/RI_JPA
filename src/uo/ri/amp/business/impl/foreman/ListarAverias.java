package uo.ri.amp.business.impl.foreman;

import uo.ri.amp.persistence.AveriaFinder;
import uo.ri.business.impl.Command;
import uo.ri.model.exception.BusinessException;

public class ListarAverias implements Command {

	@Override
	public Object execute() throws BusinessException {
		
		return AveriaFinder.findAll();
	}

}
