package uo.ri.amp.business.impl.foreman;

import uo.ri.amp.business.impl.asserts.AssertAveria;
import uo.ri.amp.model.Averia;
import uo.ri.amp.persistence.AveriaFinder;
import uo.ri.business.impl.Command;
import uo.ri.model.exception.BusinessException;

public class FindAveriaById implements Command {
	
	private Long idAveria;

	public FindAveriaById(Long id) {
		this.idAveria=id;
	}

	@Override
	public Averia execute() throws BusinessException {
		Averia a= AveriaFinder.findById(idAveria);
		
		AssertAveria.NotNull(a, idAveria);
		return a;
	}

}
