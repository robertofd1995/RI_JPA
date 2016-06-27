package uo.ri.amp.business.impl.admin.asistencias;

import java.util.Date;

import uo.ri.amp.business.impl.asserts.AssertCurso;
import uo.ri.amp.model.Asistencia;
import uo.ri.amp.model.Curso;
import uo.ri.amp.model.Mecanico;
import uo.ri.amp.model.types.AsistenciaStatus;
import uo.ri.amp.persistence.CursoFinder;
import uo.ri.amp.persistence.MecanicoFinder;
import uo.ri.business.impl.Command;
import uo.ri.model.exception.BusinessException;
import uo.ri.persistence.util.Jpa;

public class AddAsistencia implements Command {

	private String dniMecanico;
	private Long codigoCurso;
	private Date fincio;
	private Date ffinal;
	private double pasitencia;
	private AsistenciaStatus status;

	public AddAsistencia(String dniMecanico, Long codigoCurso, Date finicio, Date ffinal, double pasistencia,
			AsistenciaStatus status) {
		this.dniMecanico=dniMecanico;
		this.codigoCurso=codigoCurso;
		this.fincio=finicio;
		this.ffinal=ffinal;
		this.pasitencia=pasistencia;
		this.status=status;
	}

	@Override
	public Asistencia execute() throws BusinessException {
		
		Mecanico m=MecanicoFinder.findByDni(dniMecanico);

		if (m == null) {
			throw new BusinessException("Mecanico con dni : " + dniMecanico + " no encontrado");
		}
		
		Curso c=CursoFinder.findByCodigo(codigoCurso);

		AssertCurso.NotNull(c, codigoCurso);

		Asistencia a=new Asistencia(m, c, fincio, ffinal, pasitencia, status);
		
		Jpa.getManager().persist(a);
		
		return a;
	}

	

}
