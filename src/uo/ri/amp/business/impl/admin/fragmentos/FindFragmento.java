package uo.ri.amp.business.impl.admin.fragmentos;

import uo.ri.amp.model.Fragmento;
import uo.ri.amp.model.types.FragmentoKey;
import uo.ri.amp.persistence.FragmentoFinder;
import uo.ri.business.impl.Command;
import uo.ri.model.exception.BusinessException;

public class FindFragmento implements Command {

	private FragmentoKey key;

	public FindFragmento(FragmentoKey key) {
		this.key=key;
	}

	@Override
	public Fragmento execute() throws BusinessException {
		
		return FragmentoFinder.findById(key);
	}

}
