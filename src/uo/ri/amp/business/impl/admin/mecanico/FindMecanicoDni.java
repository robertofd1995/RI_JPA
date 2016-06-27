package uo.ri.amp.business.impl.admin.mecanico;

import uo.ri.amp.model.Mecanico;
import uo.ri.amp.persistence.MecanicoFinder;
import uo.ri.business.impl.Command;
import uo.ri.model.exception.BusinessException;

public class FindMecanicoDni implements Command {

	
	
	private String dni;

	public FindMecanicoDni(String dniMecanico) {
		this.dni=dniMecanico;
	}

	@Override
	public Mecanico execute() throws BusinessException {
		Mecanico m= MecanicoFinder.findByDni(dni);

		if (m == null) {
			throw new BusinessException("Mecanico con dni : " + dni + " no encontrado");
		}

		return m;
	}

}
