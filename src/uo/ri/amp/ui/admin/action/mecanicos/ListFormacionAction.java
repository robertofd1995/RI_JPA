package uo.ri.amp.ui.admin.action.mecanicos;

import java.util.ArrayList;
import java.util.HashMap;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.conf.ServicesFactory;

/**
 *  En esta clase se listara la formacion de la que dispone un mecanico
 * @author rober
 *
 */
public class ListFormacionAction implements Action {

	@Override
	public void execute() throws Exception {
		
		Long mecanico_id=Console.readLong("inserte el id del mecanico");
		HashMap<String, Object> formacion=ServicesFactory.getAdminService().listarFormacion(mecanico_id);
		
		StringBuilder str=new StringBuilder();
		
		Double horasC=(Double) formacion.get("horasCurso");
		Double horasA=(Double) formacion.get("horasAsistidas");
		
		str.append("\nTotal de horas de los cursos : "+ horasC+"\n");
		str.append("Total de horas asistidas : "+ horasA+"\n");
		
		@SuppressWarnings("unchecked")
		ArrayList<HashMap<String, Object>> tipos=(ArrayList<HashMap<String, Object>>) formacion.get("tipos");
		
		for (HashMap<String, Object> tipo : tipos) {
			str.append("\n\t"+(String)tipo.get("nombre")+" ");
			str.append((Double)tipo.get("horas"));
		}
		
		Console.println(str.toString());

	}

}
