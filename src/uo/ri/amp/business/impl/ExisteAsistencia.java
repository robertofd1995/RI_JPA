package uo.ri.amp.business.impl;

import java.util.Date;

import uo.ri.amp.persistence.AsistenciaFinder;
import uo.ri.business.impl.Command;
import uo.ri.model.exception.BusinessException;

public class ExisteAsistencia implements Command {

	private String dni;
	private Long codigo;
	private Date finicio;

	public ExisteAsistencia(String dniMecanico, Long codigoCurso, Date finicio) {
		this.dni=dniMecanico;
		this.codigo=codigoCurso;
		this.finicio=finicio;
	}

	@Override
	public Long execute() throws BusinessException {
		
		return AsistenciaFinder.existeAveria(dni,codigo,finicio);
	}

}
