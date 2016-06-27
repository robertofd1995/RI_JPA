package uo.ri.amp.ui.admin.action.asistencias;

import java.sql.Date;

import alb.util.console.Console;
import alb.util.date.DateUtil;
import alb.util.menu.Action;
import uo.ri.amp.model.types.AsistenciaStatus;
import uo.ri.conf.ServicesFactory;

/**
 * En esta clase se llevara a cabo la obtencion de datos para modificar una averia
 * @author rober
 *
 */
public class ModificarAsistenciaAction implements Action {

	@Override
	public void execute() throws Exception {
		
		Date finicio = null,ffinal = null;
		AsistenciaStatus status=AsistenciaStatus.NO_APTO;
		
		
		String dniMecanico = Console.readString("Dni del mecanico");
		Long codigoCurso = Console.readLong("codigo del curso");
		
		try {
			finicio=new Date( DateUtil.fromString( Console.readString("fecha de inicio (DD/MM/YYYY) ")).getTime() );
			ffinal=new Date( DateUtil.fromString( Console.readString("fecha de final (DD/MM/YYYY) ")).getTime() );
			
		} catch (RuntimeException e) {
			Console.println(e.toString());
			Console.println("Operacion finalizada");
		}
		
		Double pasistencia = Console.readDouble("Porcentaje de asistencias a clase");
		boolean apto=(Console.readString("�Es apto? (s/n")).contains("s")?true:false;
		
		if (apto) {
			status=AsistenciaStatus.APTO;
		}

		ServicesFactory.getAdminService().updateAsistencia(dniMecanico,codigoCurso, finicio, ffinal, pasistencia,status);

	}

}
