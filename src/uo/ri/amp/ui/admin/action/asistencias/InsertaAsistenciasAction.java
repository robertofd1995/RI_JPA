package uo.ri.amp.ui.admin.action.asistencias;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import alb.util.console.Console;
import alb.util.date.DateUtil;
import alb.util.menu.Action;
import uo.ri.amp.business.AdminService;
import uo.ri.amp.model.Asistencia;
import uo.ri.amp.model.types.AsistenciaStatus;
import uo.ri.conf.ServicesFactory;

/**
 * En esta clase se llevara a cabo la obtencion de datos para insertar una averia
 * @author rober
 *
 */
public class InsertaAsistenciasAction implements Action {

	@SuppressWarnings("unused")
	@Override
	public void execute() throws Exception {
		Date finicio = null,ffinal = null;
		AsistenciaStatus status=AsistenciaStatus.NO_APTO;
		
		List<Asistencia> asistencias=new ArrayList<Asistencia>();
		Asistencia asistencia;
		do {
			
			
			String dniMecanico = Console.readString("Dni del mecanico");
			Long codigoCurso = Console.readLong("Codigo del curso");
			
			try {
				finicio=new Date( DateUtil.fromString( Console.readString("fecha de inicio (DD/MM/YYYY) ")).getTime() );
				ffinal=new Date( DateUtil.fromString( Console.readString("fecha de final (DD/MM/YYYY) ")).getTime() );
				
			} catch (RuntimeException e) {
				Console.println(e.toString());
				Console.println("Operacion finalizada");
				break;
			}
			
			double pasistencia = Console.readDouble("Porcentaje de asistencias a clase");
			
			if(pasistencia>100){
				Console.println("El porcentaje de asistencia no puede ser mayor a 100");
				break;
			}
			
			boolean apto=(Console.readString("�Es apto? (s/n")).contains("s")?true:false;
			
			if (apto) {
				status=AsistenciaStatus.APTO;
			}
			
			AdminService admin = ServicesFactory.getAdminService();

			admin.AddAsistencia(dniMecanico,codigoCurso, finicio, ffinal, pasistencia,status);

		} while (masAsistencias());
		

		Console.println("Operacion finalizada");

	}
	
	private boolean masAsistencias() {
		return Console.readString("�Anadir mas asistencias? (s/n) ").equalsIgnoreCase("s");
	}

}
