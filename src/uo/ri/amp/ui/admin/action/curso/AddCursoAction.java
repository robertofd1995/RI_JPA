package uo.ri.amp.ui.admin.action.curso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import alb.util.console.Console;
import alb.util.menu.Action;
import uo.ri.amp.business.impl.asserts.AssertCurso;
import uo.ri.amp.model.Curso;
import uo.ri.conf.ServicesFactory;
import uo.ri.model.exception.BusinessException;

/**
 * En esta clase se llevara a cabo la obtencion de datos para insertar un curso
 * @author rober
 *
 */
public class AddCursoAction implements Action {

	@Override
	public void execute() throws Exception {
		
		Console.println("A�adir Curso \n");
		
		@SuppressWarnings("unused")
		List<Curso> cursos=new ArrayList<Curso>();
		
		// pedir las averias a incluir en la factura
				do {
					
					
					Console.println("Curso ");
					
					String nombre=Console.readString("Nombre ");
					Long codigo=Console.readLong("Codigo ");
					String descripcion=Console.readString("Descripcion ");
					
					
					Double totalHoras=Console.readDouble("Total horas ");
						
					
					Curso curso=new Curso(nombre, codigo, descripcion, totalHoras);
					
					AssertCurso.NotRepeated(curso);
					
					ArrayList<HashMap<String, Object>> fragmentos=new ArrayList<HashMap<String,Object>>();
					
					double acumulador=0;
					
					do {
						Console.println("\nFragmento Curso ");
						
						HashMap<String, Object> fragmento=new HashMap<String,Object>();
						
						try {
							String tipoVehiculo=Console.readString("tipo vehiculo (Ej : Coche Aviso primera letra mayuscula)");
							
							
							Double porcentaje=Console.readDouble("porcentaje ");
							
							if(acumulador+porcentaje>100){
								throw new BusinessException("La suma de los porcentajes de los" +
										" fragmentos exceden el 100% \n Curso no a�adido");
							}else{
								acumulador+=porcentaje;
							}
							
							fragmento.put("tipo", tipoVehiculo);
							fragmento.put("porcentaje", (porcentaje/100)*totalHoras);
						} catch (RuntimeException e) {
							Console.println("ERROR : ha intentado introducir caracteres como numeros");
							break;
						}
						
						fragmentos.add(fragmento);
						
						
					} while (masFragmentos());
					
					ServicesFactory.getAdminService().newCurso(curso,fragmentos);
					
				} while ( masCursos() );

								
				Console.println("Operacion de curso finalizada");
				
		
	}
	
	private boolean masFragmentos() {
		return Console.readString("Anadir mas fragmetos? (s/n) ").equalsIgnoreCase("s");
	}

	private boolean masCursos() {
		return Console.readString("Anadir mas cursos? (s/n) ").equalsIgnoreCase("s");
	}

}
