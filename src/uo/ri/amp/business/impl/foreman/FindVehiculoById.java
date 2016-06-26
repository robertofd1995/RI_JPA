package uo.ri.amp.business.impl.foreman;

import uo.ri.amp.model.Vehiculo;
import uo.ri.amp.persistence.VehiculoFinder;
import uo.ri.business.impl.Command;
import uo.ri.model.exception.BusinessException;

public class FindVehiculoById implements Command {

	private Long idVehiculo;
	
	public FindVehiculoById(long vehiculo_id) {
		this.idVehiculo=vehiculo_id;
	}

	@Override
	public Vehiculo execute() throws BusinessException {
		return VehiculoFinder.findById(idVehiculo);
	}

}
