package uo.ri.amp.ui.admin.action.asistencias;

import java.sql.Date;

import alb.util.console.Console;
import alb.util.date.DateUtil;
import alb.util.menu.Action;
import uo.ri.amp.business.impl.asserts.AssertAsistencia;
import uo.ri.amp.model.Curso;
import uo.ri.amp.model.Mecanico;
import uo.ri.amp.model.types.AsistenciaKey;
import uo.ri.conf.ServicesFactory;

/**
 * En esta clase se llevara a cabo la obtencion de datos para borrar una averia
 * @author rober
 *
 */
public class EliminarAsistenciaAction implements Action {

	@Override
	public void execute() throws Exception {
		Date finicio=null;
		long curso_codigo=Console.readLong("codigo curso de la asistencia");
		String mecanico_dni=Console.readString("dni mecanico de la asistencia");
		
		try {
			finicio=new Date(DateUtil.fromString(Console.readString("Fecha inicio (dd/mm/yyyy)")).getTime());
		} catch (RuntimeException e) {
			Console.println("Formato de fecha introducido no valido");
		}
		
		Mecanico m=ServicesFactory.getAdminService().findMecanicoByDni(mecanico_dni);
		Curso c=ServicesFactory.getAdminService().findCursoByCodigo(curso_codigo);
		
		AssertAsistencia.Existe(mecanico_dni,curso_codigo, finicio);
		ServicesFactory.getAdminService().deleteAsistencia( new AsistenciaKey(c.getId(), m.getId(), finicio));
		
		Console.println("Operacion de eliminacion finalizada");

	}

}
