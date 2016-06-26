package uo.ri.amp.ui.admin.action.curso;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.conf.ServicesFactory;

/**
 * En esta clase se llevara a cabo la obtencion de datos para eliminar un curso
 * @author rober
 *
 */
public class EliminarCursoAction implements Action{

	@Override
	public void execute() throws Exception {
		
		long id_curso=Console.readLong("Inserte el codigo del curso a eliminar "
				+ "\n(esto tambien eliminara los fragmentos asociados) \n");
		ServicesFactory.getAdminService().deleteCurso(id_curso);
		Console.println("Operacion eliminar curso finalizada");
	}

}
