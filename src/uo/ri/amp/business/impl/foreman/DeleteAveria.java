package uo.ri.amp.business.impl.foreman;

import uo.ri.amp.business.impl.asserts.AssertAveria;
import uo.ri.amp.model.Averia;
import uo.ri.amp.persistence.AveriaFinder;
import uo.ri.business.impl.Command;
import uo.ri.model.exception.BusinessException;
import uo.ri.persistence.util.Jpa;

public class DeleteAveria implements Command {

	private Long idAveria;
	
	public DeleteAveria(Long idAveria) {
		this.idAveria=idAveria;
	}

	@Override
	public Object execute() throws BusinessException {
		
		Averia a = AveriaFinder.findById(idAveria);
		
		AssertAveria.CanBeDelated(a);
		AssertAveria.NotNull(a,idAveria);
		
		Jpa.getManager().remove(a);
		return a;
		
	}

	

}
