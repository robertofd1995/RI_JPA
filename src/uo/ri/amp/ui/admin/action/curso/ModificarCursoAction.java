package uo.ri.amp.ui.admin.action.curso;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.amp.model.Curso;
import uo.ri.conf.ServicesFactory;
import uo.ri.model.exception.BusinessException;

/**
 * En esta clase se llevara a cabo la obtencion de datos para modificar un curso
 * @author rober
 *
 */
public class ModificarCursoAction implements Action{

	@Override
	public void execute() throws Exception {
		double totalHoras=0;
		long codigoCurso=Console.readLong("Inserte el codigo del curso a modificar");
		
		Curso curso=ServicesFactory.getAdminService().findCursoByCodigo(codigoCurso);
		
		String nombre=Console.readString("Nombre ");
		Long codigo=Console.readLong("Codigo :");
		String descripcion=Console.readString("Descripcion ");
		
		curso.setNombre(nombre);
		curso.setDescripcion(descripcion);
		curso.setCodigo(codigo);
		
		try {
			totalHoras=Console.readDouble("Total horas ");
		} catch (RuntimeException e) {
			Console.println("ERROR : ha intentado introducir caracteres como numeros");
		}
		if (curso.getTotalHoras()<=totalHoras) {
			curso.setTotalHoras(totalHoras);
		}else{
			throw new BusinessException("El total de horas del nuevo curso no puede ser menor al anterior");
		}
		
		
		ServicesFactory.getAdminService().updateCurso(curso);
		
	}

}
