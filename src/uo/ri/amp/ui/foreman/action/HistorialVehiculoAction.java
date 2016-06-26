package uo.ri.amp.ui.foreman.action;

import java.util.List;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.amp.model.Averia;
import uo.ri.conf.ServicesFactory;

public class HistorialVehiculoAction implements Action {

	@Override
	public void execute() throws Exception {
		String matricula = Console.readString("matricula coche");
		
		List<Averia> averias=ServicesFactory.getForemanService().findAveriasByMatricula(matricula);
		
		for (Averia averia : averias) {
			StringBuilder str=new StringBuilder();
			str.append(" id :" +averia.getId());
			str.append(" fecha :" +averia.getFecha());
			str.append(" status :" +averia.getStatus());
			str.append(" importe:" +averia.getImporte());
			str.append(" descripcion :" +averia.getDescripcion());
			
			Console.println(str.toString());
		}
		
		Console.println("Operacion de averia finalizada");

	}

}
