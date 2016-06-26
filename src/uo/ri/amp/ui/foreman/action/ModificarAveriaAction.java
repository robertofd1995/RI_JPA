package uo.ri.amp.ui.foreman.action;

import java.util.Date;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.amp.model.Averia;
import uo.ri.amp.ui.util.Validador;
import uo.ri.conf.ServicesFactory;
import uo.ri.model.exception.BusinessException;

public class ModificarAveriaAction implements Action {

	@Override
	public void execute() throws BusinessException {
		
		long id = Console.readLong("ID");
		String descripcion = Console.readString("Descripcion"); 
		
		//Console.readString("¿Quiere cambiar la fecha?");
		
		String fechaStr = Console.readString("Fecha (formato dd/mm/yyyy)");
		double importe = Console.readDouble("Importe");
		Date fecha=null;
		
		
		fecha=Validador.validarFecha(fechaStr);
		
		
		Averia averia=ServicesFactory.getForemanService().findAveriaById(id);
		
		averia.setDescripcion(descripcion);
		averia.setImporte(importe);
		averia.setFecha(fecha);
		
		
		ServicesFactory.getForemanService().updateAveria(averia);
		
		Console.println("Operacion de averia finalizada");

	}

}
