package uo.ri.ui.admin.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.amp.business.AdminService;
import uo.ri.amp.model.Mecanico;
import uo.ri.conf.ServicesFactory;
import uo.ri.model.exception.BusinessException;

public class AddMechanicAction implements Action {

	@Override
	public void execute() throws BusinessException {
		
		// Pedir datos
		String dni = Console.readString("Dni"); 
		String nombre = Console.readString("Nombre"); 
		String apellidos = Console.readString("Apellidos");
		
		// Procesar
		AdminService as = ServicesFactory.getAdminService();
		as.newMechanic( new Mecanico(dni, nombre, apellidos) );
				
		// Mostrar resultado
		Console.println("Nuevo mecánico añadido");
	}

}
