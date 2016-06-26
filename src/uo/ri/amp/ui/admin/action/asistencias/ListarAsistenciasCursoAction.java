package uo.ri.amp.ui.admin.action.asistencias;

import java.util.List;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.amp.model.Asistencia;
import uo.ri.amp.model.Curso;
import uo.ri.conf.ServicesFactory;

/**
 * En esta clase se llevara a un listado de las averias
 * @author rober
 *
 */
public class ListarAsistenciasCursoAction implements Action {

	@Override
	public void execute() throws Exception {
		
		Console.println("Listar Asistencias por cursos");
		
		boolean listarTodo=Console.readString("¿Quiere listar todo? (s/n) ").contains("s");
		
		
		//long id_curso=Console.readLong("Introduzca el id del curso");
		
		StringBuilder str=null;
		
		for (Curso curso : ServicesFactory.getAdminService().findAllCursos()) {
			
			
			
			List<Asistencia> asistencias=ServicesFactory.getAdminService().findAllAsistenciasByCurso(curso);
			
			Console.println("\n\n Asistencias del curso :" + curso.getNombre()+" ( Codigo: "+curso.getCodigo()+")\n" );
			
			if(listarTodo){
				Console.println("\tnombre --mecanico id -- fecha inicio --fecha final-- %asistencia -- status \n" );
			}else{
				Console.println("\tnombre --fecha final-- %asistencia -- status \n" );
			}
			
			str=new StringBuilder();
			
			for (Asistencia asistencia : asistencias) {
				
				
				str.append("\t"+ asistencia.getMecanico().getNombre());
				
				if(listarTodo){
					str.append(" -- "+asistencia.getMecanico().getId());
					str.append(" -- "+asistencia.getfInicio());
				}
					
				str.append(" -- "+asistencia.getfFinal());
				str.append(" -- "+asistencia.getAsistencia());
				str.append(" -- "+asistencia.getStatus()+"\n");
				
			}
			
			Console.println(str.toString());
			
			
		}
		
	}
}

