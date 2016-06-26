package uo.ri.amp.ui.foreman.action;

import java.util.List;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.amp.model.Averia;
import uo.ri.amp.model.Mecanico;
import uo.ri.conf.ServicesFactory;

public class AsignarAveriaAction implements Action{

	@Override
	public void execute() throws Exception {
		long idAveria=Console.readLong("Introduzca el id de la averia");
		
		Averia averia=ServicesFactory.getForemanService().findAveriaById(idAveria);
		
		List<Mecanico> mecanicosExpertos=
				ServicesFactory.getForemanService().listarExpertos(averia.getVehiculo().getTipo());
		
		Console.println("Mecanicos disponibles");
		
		
		StringBuilder str=new StringBuilder();
		for (Mecanico mecanico : mecanicosExpertos) {
			str.append("\t\nId : " + mecanico.getId() + " nombre :"+mecanico.getNombre() );
		}
		
		Console.println(str.toString());
		
		long mecanico_id=Console.readLong("Introduzca el id del mecanico al que le quiere asignar la averia");
		
		ServicesFactory.getForemanService().asignarAveria(averia,mecanico_id);
		
		Console.println("Operacion de asignacion finalizada");
		
	}

}
