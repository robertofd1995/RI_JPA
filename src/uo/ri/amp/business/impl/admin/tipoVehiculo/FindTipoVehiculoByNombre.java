package uo.ri.amp.business.impl.admin.tipoVehiculo;

import uo.ri.amp.model.TipoVehiculo;
import uo.ri.amp.persistence.TipoVehiculoFinder;
import uo.ri.business.impl.Command;
import uo.ri.model.exception.BusinessException;

public class FindTipoVehiculoByNombre implements Command {
	
	private String tipoNombre;

	public FindTipoVehiculoByNombre(String nombre) {
		this.tipoNombre=nombre;
	}

	@Override
	public TipoVehiculo execute() throws BusinessException {
		
		TipoVehiculo tipo= TipoVehiculoFinder.findByNombre(tipoNombre);

		if (tipo==null) {
			throw new BusinessException("El tipo de vehiculo introducido no existe ");
		}
		return tipo;
	}

}
