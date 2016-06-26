package uo.ri.amp.ui.admin.action.mecanicos;

import java.util.List;
import uo.ri.amp.business.AdminService;
import uo.ri.amp.model.Mecanico;

import uo.ri.conf.ServicesFactory;
import uo.ri.model.exception.BusinessException;
import alb.util.console.Console;
import alb.util.menu.Action;


/**
 *  En esta clase se listara los mecanicos disponibles
 * @author rober
 *
 */
public class ListMechanicsAction implements Action {

	@Override
	public void execute() throws BusinessException {

		Console.println("\nListado de mecánicos\n");  

		AdminService adminService=ServicesFactory.getAdminService();
		
		
		List<Mecanico> lista=adminService.findAllMechanics();
		
		for (int i = 0; i < lista.size(); i++) {
			long id=(long)lista.get(i).getId();
			String nombre=(String)lista.get(i).getNombre();
			String apellido=(String)lista.get(i).getApellidos();
			
			StringBuilder str=new StringBuilder();
			str.append(id+",");
			str.append(nombre+",");
			str.append(apellido+"\n");
			
			System.out.println(str.toString());
			
			
		}
		
	}
}
