package uo.ri.business.impl.admin;

import uo.ri.amp.model.Mecanico;
import uo.ri.business.impl.Command;
import uo.ri.persistence.util.Jpa;

public class AddMechanic implements Command
{

	private Mecanico mecanico;

	public AddMechanic(Mecanico mecanico)
	{
		this.mecanico = mecanico;
	}

	public Object execute()
	{
		Jpa.getManager().persist(mecanico);
		return mecanico;
	}

}
