package uo.ri.ui.admin.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.amp.business.AdminService;
import uo.ri.amp.model.Mecanico;
import uo.ri.conf.ServicesFactory;
import uo.ri.model.exception.BusinessException;
import uo.ri.ui.util.Printer;

import java.util.List;

public class ListMechanicsAction implements Action {

	@Override
	public void execute() throws BusinessException {
	
		AdminService as = ServicesFactory.getAdminService();
		List<Mecanico> mechanics = as.findAllMechanics();
		
		Console.println("\nListado de mecánicos\n");  
		for(Mecanico m : mechanics) {
			Printer.printMechanic( m );
		}

	}
}
