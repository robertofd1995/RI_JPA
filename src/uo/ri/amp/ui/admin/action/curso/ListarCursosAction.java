package uo.ri.amp.ui.admin.action.curso;

import java.util.List;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.amp.model.Curso;
import uo.ri.amp.model.Fragmento;
import uo.ri.conf.ServicesFactory;

/**
 * En esta clase se llevara a cabo el listado de los cursos
 * el usuario podra decirdir si quiere ver toda la informacion de estos (fragmenos incluidos) o no
 * @author rober
 *
 */
public class ListarCursosAction implements Action {

	@Override
	public void execute() throws Exception {
		
		Console.println("Lista cursos");
		
		
		List<Curso> cursos=ServicesFactory.getAdminService().findAllCursos();
		List<Fragmento> fragmentos;
		
		boolean resuesta=(Console.readString("¿desea listar el curso con los fragmentos asociados? y/n").contains("y")==true);
		Console.println("\n\tID --- nombre --- descripcion --- total horas");
		
		StringBuilder str=new StringBuilder();
		
		for (Curso curso : cursos) {
			
			str.append("\n\t"+curso.getCodigo()+" --- "+curso.getNombre()+" --- "+curso.getDescripcion()+" --- "+curso.getTotalHoras().doubleValue());
			if(resuesta){
				fragmentos=ServicesFactory.getAdminService().findAllFragmentoByCurso(curso);
						for (Fragmento fragmento : fragmentos) {
							str.append("\n\t\t Tipo : "+fragmento.getTipoVehiculo().getNombre()+" Horas : "+fragmento.getHoras());
						}
				str.append("\n");		
			}
				
			
		}
		
		Console.println(str.toString());
	}

}
