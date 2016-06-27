package uo.ri.amp.business.impl.foreman;

import uo.ri.amp.business.impl.asserts.AssertAveria;
import uo.ri.amp.model.Association;
import uo.ri.amp.model.Averia;
import uo.ri.amp.model.Mecanico;
import uo.ri.amp.persistence.AveriaFinder;
import uo.ri.amp.persistence.MecanicoFinder;
import uo.ri.business.impl.Command;
import uo.ri.model.exception.BusinessException;
import uo.ri.model.types.AveriaStatus;

public class AsignarAveria implements Command {

	private Averia averia;
	private long mecanicoId;

	public AsignarAveria(Averia averia, long mecanico_id) {
		this.averia=averia;
		this.mecanicoId=mecanico_id;
	}

	@Override
	public Object execute() throws BusinessException {
		
		Averia averiaBD=AveriaFinder.findById(averia.getId());
		Mecanico mecanico =MecanicoFinder.findById(mecanicoId);

		if(!AssertAveria.Abierta(averia))
			throw new BusinessException("La averia no puede ser asignada puesto que se encuentra en estado "
					+ averiaBD.getStatus());

		averiaBD.setStatus(AveriaStatus.ASIGNADA);
		Association.Asignar.link(mecanico,averiaBD);


		return averiaBD;
	}

}
