package uo.ri.amp.business.impl.foreman;

import uo.ri.amp.business.impl.asserts.AssertAveria;
import uo.ri.amp.model.Averia;
import uo.ri.amp.persistence.AveriaFinder;
import uo.ri.business.impl.Command;
import uo.ri.model.exception.BusinessException;

public class UpdateAveria implements Command {
	
	private Averia averia;

	public UpdateAveria(Averia averia) {
		this.averia=averia;
	}

	@Override
	public Averia execute() throws BusinessException {
		Averia averiaBD=AveriaFinder.findById(averia.getId());

		AssertAveria.NotNull(averiaBD,averia.getId());
		
		averiaBD.setDescripcion(averia.getDescripcion());
		averiaBD.setImporte(averia.getImporte());
		averiaBD.setStatus(averia.getStatus());
		
		return averia;
	}

}
