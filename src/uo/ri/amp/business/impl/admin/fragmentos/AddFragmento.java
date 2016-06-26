package uo.ri.amp.business.impl.admin.fragmentos;

import uo.ri.amp.model.Fragmento;
import uo.ri.business.impl.Command;
import uo.ri.model.exception.BusinessException;
import uo.ri.persistence.util.Jpa;

public class AddFragmento implements Command {

	private Fragmento fragmento;

	public AddFragmento(Fragmento fragmento) {
		this.fragmento=fragmento;
	}

	@Override
	public Fragmento execute() throws BusinessException {
		
		Jpa.getManager().persist(fragmento);
		return fragmento;
	}

}
