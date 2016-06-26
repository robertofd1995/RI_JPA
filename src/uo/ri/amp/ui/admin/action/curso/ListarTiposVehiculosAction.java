package uo.ri.amp.ui.admin.action.curso;

import java.util.List;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.amp.model.TipoVehiculo;
import uo.ri.conf.ServicesFactory;

/**
 * En esta clase se llevara a imprimira un listado de los tipos de vehiculos disponibles en la base de datos
 * @author rober
 */
public class ListarTiposVehiculosAction implements Action {

	@Override
	public void execute() throws Exception {
		
		Console.println("Lista tipo vehiculos");
		
		boolean mostrarTodo=(Console.readString("Mostrar todo (s/n) ").contains("s")? true : false);
		
		Console.println("\n\t ---ID--- nombre ---");
		
		List<TipoVehiculo> tipos=ServicesFactory.getAdminService().listarTiposVehiculos();
		
		StringBuilder str=new StringBuilder();
		
		for (TipoVehiculo tipo : tipos) {
			
			str.append("\n\t   "+tipo.getId()+" --- "+tipo.getNombre());
			if (mostrarTodo) {
				str.append("---"+tipo.getPrecioHora().doubleValue() +"---"+tipo.getHorasExperto().doubleValue());			}
		}
		
		Console.println(str.toString());

	}

}
