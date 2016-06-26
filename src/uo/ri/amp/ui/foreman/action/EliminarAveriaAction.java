package uo.ri.amp.ui.foreman.action;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.conf.ServicesFactory;

public class EliminarAveriaAction implements Action{

	@Override
	public void execute() throws Exception {
		long id = Console.readLong("ID");
		
		ServicesFactory.getForemanService().deleteAveria(id);
		
		Console.println("Operacion de averia finalizada");
		
	}
	
	

}
