package uo.ri.amp.business.impl.admin.fragmentos;

import uo.ri.amp.business.impl.asserts.AssertFragmento;
import uo.ri.amp.model.Fragmento;
import uo.ri.amp.model.types.FragmentoKey;
import uo.ri.amp.persistence.FragmentoFinder;
import uo.ri.business.impl.Command;
import uo.ri.model.exception.BusinessException;
import uo.ri.persistence.util.Jpa;

public class DeleteFragmento implements Command {

	private FragmentoKey key;

	public DeleteFragmento(FragmentoKey key) {
		this.key=key;
	}

	@Override
	public Fragmento execute() throws BusinessException {
		
		Fragmento f=FragmentoFinder.findById(key);
		
		AssertFragmento.NotNull(f, key);
		AssertFragmento.CanBeDelated(f);
		
		Jpa.getManager().remove(f);
		
		return f;
	}

}
