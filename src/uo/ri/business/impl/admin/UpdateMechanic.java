package uo.ri.business.impl.admin;

import uo.ri.amp.model.Mecanico;
import uo.ri.business.impl.Command;
import uo.ri.model.exception.BusinessException;
import uo.ri.persistence.util.Jpa;

public class UpdateMechanic implements Command
{

	private Mecanico mecanico;

	public UpdateMechanic(Mecanico mecanico)
	{
		this.mecanico = mecanico;
	}

	public Object execute() throws BusinessException
	{
		Jpa.getManager().merge(mecanico);

		return mecanico;
	}

}
