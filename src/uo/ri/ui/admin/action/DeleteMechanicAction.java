package uo.ri.ui.admin.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.amp.business.AdminService;
import uo.ri.conf.ServicesFactory;
import uo.ri.model.exception.BusinessException;

public class DeleteMechanicAction implements Action {

	@Override
	public void execute() throws BusinessException {
		
		Long idMecanico = Console.readLong("Id de mecánico"); 
		
		AdminService as = ServicesFactory.getAdminService();
		as.deleteMechanic(idMecanico);
		
		Console.println("Se ha eliminado el mecánico");
	}

}
