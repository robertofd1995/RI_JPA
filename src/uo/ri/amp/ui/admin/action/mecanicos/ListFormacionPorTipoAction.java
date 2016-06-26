package uo.ri.amp.ui.admin.action.mecanicos;

import java.util.ArrayList;
import java.util.HashMap;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.conf.ServicesFactory;

/**
 *  En esta clase se llevara listara que mecanicos han asistido a cursos para los distintos tipos de vehiculos
 * @author rober
 *
 */
public class ListFormacionPorTipoAction implements Action {

	
	@Override
	public void execute() throws Exception {
		
		ArrayList<HashMap<String,Object>> tipos=ServicesFactory.getAdminService().listarFormacionPorTipos();
		
		for (HashMap<String, Object> tipo : tipos) {
			Console.println("tipo "+(String)tipo.get("tipo"));
			
			@SuppressWarnings("unchecked")
			ArrayList<HashMap<String,Object>> formaciones=(ArrayList<HashMap<String, Object>>) tipo.get("mecanicos");
			
			StringBuilder str=new StringBuilder();
			
			for (HashMap<String, Object> formacion : formaciones) {
				str.append("\n\t"+ formacion.get("nombre")+ " "+formacion.get("horas")+ " horas\n");
			}
			
			Console.print(str.toString());
		}

	}

}
