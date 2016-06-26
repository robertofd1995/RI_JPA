package uo.ri.amp.business.impl.admin.tipoVehiculo;

import java.util.List;

import uo.ri.amp.model.TipoVehiculo;
import uo.ri.amp.persistence.TipoVehiculoFinder;
import uo.ri.business.impl.Command;
import uo.ri.model.exception.BusinessException;

public class FindAllTipoVehiculo implements Command {

	@Override
	public List<TipoVehiculo> execute() throws BusinessException {
		
		return TipoVehiculoFinder.findAll();
	}

}
