package uo.ri.amp.ui.foreman.action;

import java.util.Date;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.amp.business.ForemanService;
import uo.ri.amp.model.Averia;
import uo.ri.amp.ui.util.Validador;
import uo.ri.conf.ServicesFactory;
import uo.ri.model.exception.BusinessException;

public class RegistrarAveriaAction implements Action {

	@Override
	public void execute() throws BusinessException {
		
			//Pedir datos
			String descripcion = Console.readString("Descripcion"); 
			Date fecha=null;
			
			try {
				fecha=Validador.validarFecha(Console.readString("Fecha (formato dd/mm/yyyy):"));
			} catch (RuntimeException e) {
				Console.println(e.getMessage());
			}
			
			double importe = Console.readDouble("Importe");
			long vehiculo_id=Console.readLong("Id del vehiculo");
			
			//Procesar
			
			ForemanService fs = ServicesFactory.getForemanService();
			
			fs.newAveria(new Averia(fecha, fs.findVehiculoById(vehiculo_id),descripcion,importe));
				 
				
			// Mostrar resultado
			Console.println("Nueva averia registrada");
		
	}

}
