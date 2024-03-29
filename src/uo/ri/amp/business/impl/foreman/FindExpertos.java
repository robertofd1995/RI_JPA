package uo.ri.amp.business.impl.foreman;

import java.util.List;

import uo.ri.amp.model.Mecanico;
import uo.ri.amp.model.TipoVehiculo;
import uo.ri.amp.persistence.MecanicoFinder;
import uo.ri.business.impl.Command;
import uo.ri.model.exception.BusinessException;

public class FindExpertos implements Command {

	private TipoVehiculo tipoVehiculo;

	public FindExpertos(TipoVehiculo tipo) {
		this.tipoVehiculo=tipo;
	}

	@Override
	public List<Mecanico> execute() throws BusinessException {

		List<Mecanico> mecanicos= MecanicoFinder.findExpertosTipo(tipoVehiculo);

		if(mecanicos==null)
			throw new BusinessException("No se ha encontrado a ningun experto");

		return mecanicos;
	}

}
