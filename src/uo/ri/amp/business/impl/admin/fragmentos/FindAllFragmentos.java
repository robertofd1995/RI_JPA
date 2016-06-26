package uo.ri.amp.business.impl.admin.fragmentos;

import java.util.List;

import uo.ri.amp.model.Curso;
import uo.ri.amp.model.Fragmento;
import uo.ri.amp.persistence.FragmentoFinder;
import uo.ri.business.impl.Command;
import uo.ri.model.exception.BusinessException;

public class FindAllFragmentos implements Command {

	private Curso curso;

	public FindAllFragmentos(Curso curso) {
		this.curso=curso;
	}

	@Override
	public List<Fragmento> execute() throws BusinessException {
		
		return FragmentoFinder.findAllByCurso(curso);
	}

}
