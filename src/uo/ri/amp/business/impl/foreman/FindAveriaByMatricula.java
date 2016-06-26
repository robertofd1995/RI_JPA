package uo.ri.amp.business.impl.foreman;

import java.util.List;

import uo.ri.amp.model.Averia;
import uo.ri.amp.persistence.AveriaFinder;
import uo.ri.business.impl.Command;
import uo.ri.model.exception.BusinessException;

public class FindAveriaByMatricula implements Command {
	
	private String matricula;

	public FindAveriaByMatricula(String matricula) {
		this.matricula=matricula;
	}

	@Override
	public List<Averia> execute() throws BusinessException {
		
		return AveriaFinder.findByMatricula(matricula);
	}

}
