package uo.ri.amp.business.impl.admin.mecanico;

import java.util.HashMap;

import uo.ri.amp.persistence.MecanicoFinder;
import uo.ri.business.impl.Command;
import uo.ri.model.exception.BusinessException;

public class FormacionMecanico implements Command {

	private Long mecanicoId;

	public FormacionMecanico(Long mecanico_id) {
		this.mecanicoId=mecanico_id;
	}

	@Override
	public HashMap<String, Object> execute() throws BusinessException {
		
		return MecanicoFinder.findFormacion(mecanicoId);
	}

}
