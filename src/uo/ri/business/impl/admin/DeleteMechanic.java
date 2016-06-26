package uo.ri.business.impl.admin;

import uo.ri.amp.model.Mecanico;
import uo.ri.amp.persistence.MecanicoFinder;
import uo.ri.business.impl.Command;
import uo.ri.model.exception.BusinessException;
import uo.ri.persistence.util.Jpa;

public class DeleteMechanic implements Command
{

	private Long idMecanico;

	public DeleteMechanic(Long idMecanico)
	{
		this.idMecanico = idMecanico;
	}

	public Object execute() throws BusinessException
	{
		Mecanico m = MecanicoFinder.findById(idMecanico);
		asertNotNull(m);
		assertCanBeDelated(m);
		Jpa.getManager().remove(m);
		return m;
	}

	private void assertCanBeDelated(Mecanico m) throws BusinessException
	{
		if (!m.getAsignadas().isEmpty() || !m.getIntervenciones().isEmpty())
			throw new BusinessException("No se puede borrar un mecanico con dependiencias a averias");
	}

	private void asertNotNull(Mecanico m) throws BusinessException
	{
		if (m == null)
			throw new BusinessException("No se encontro ningun mecanico con ID: " + idMecanico);
	}

}
