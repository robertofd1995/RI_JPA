package uo.ri.amp.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


import uo.ri.amp.model.Asistencia;
import uo.ri.amp.model.Curso;
import uo.ri.amp.model.Fragmento;
import uo.ri.amp.model.Mecanico;
import uo.ri.amp.model.TipoVehiculo;
import uo.ri.amp.model.types.AsistenciaKey;
import uo.ri.amp.model.types.AsistenciaStatus;
import uo.ri.amp.model.types.FragmentoKey;
import uo.ri.model.exception.BusinessException;

/**
 * Esta interfaz contendra los metodos que nos permira realizar las distintas operaciones de SCRUD y demas en los objetos 
 * @author rober
 *
 */
public interface AdminService {

	//Mecanicos
	/**
	 * Este metodo a�adira un nuevo mecanico a la base de datos
	 * @param mecanico El mecanico que se quiere a�adir
	 * @throws BusinessException
	 */
	void newMechanic(Mecanico mecanico) throws BusinessException;
	/**
	 * Este metodo eliminar el mecanico identificado por su id de la base de datos
	 * @param idMecanico
	 * @throws BusinessException en caso de que el mecanico no exista
	 */
	void deleteMechanic(Long idMecanico) throws BusinessException;
	/**
	 * Este metodo actualizara con los datos del mecanico del parametro 
	 * ,la fila del mecanico que tenga el mimsmo id que el del parametro
	 * @param mecanico los nuevos datos del mecanico
	 * @throws BusinessException en caso de que el mecanico no exista
	 */
	void updateMechanic(Mecanico mecanico) throws BusinessException;

	/**
	 * Retornara al mecanico que tenga por id , la id del parametro
	 * @param id
	 * @return Mecanico
	 * @throws BusinessException
	 */
	Mecanico findMechanicById(Long id) throws BusinessException;
	/**
	 * Retornara una lista  con todos los mecanicos en la base de datos
	 * @return List<Mecanico>
	 * @throws BusinessException
	 */
	List<Mecanico> findAllMechanics() throws BusinessException;
	
	
	//Cursos
	
	/**
	 * Devolvera el curso que tenga por id , el mismo que el del parametro
	 * @param id del curso
	 * @return Curso
	 * @throws BusinessException en caso de que no exista el curso
	 */
	Curso findCursoById(Long id) throws BusinessException;
	/**
	 * Devolvera el curso que tenga por codigo , el mismo que el del parametro
	 * @param codigo del curso
	 * @return Curso
	 * @throws BusinessException en caso de que no exista el curso
	 */
	Curso findCursoByCodigo(Long codigo) throws BusinessException;
	/**
	 * Retornara una lista  con todos los cursos en la base de datos
	 * @return List<Curso>
	 * @throws BusinessException 
	 */
	List<Curso> findAllCursos() throws BusinessException;
	
	/**
	 * Comprueba si existe algun curso con el mismo codigo , retorna el numero de coincidencias
	 * @param codigo
	 * @return el numero de cursos con el mismo codigo
	 * @throws BusinessException
	 */
	Long existeCurso(Long codigo) throws BusinessException;
	
	/**
	 * A�ade un nuevo curso a la base de datos
	 * @param curso
	 * @param fragmentos
	 * @throws BusinessException en caso de que se haya introducido algun dato no valido
	 */
	void newCurso(Curso curso,List<HashMap<String, Object>> fragmentos) throws BusinessException;
	/**
	 * Elimina el curso de la base de datos que tenga por id el mismo que el del parametro
	 * @param idCurso
	 * @throws BusinessException
	 */
	void deleteCurso(Long idCurso) throws BusinessException;
	/**
	 * Actualiza el curso existente por el nuevo
	 * @param curso
	 * @throws BusinessException
	 */
	void updateCurso(Curso curso) throws BusinessException;
	
	//Fragmentos
	/**
	 * Retornara el fragmento que tenga por key la misma que la del parametro
	 * @param key (Long curso_id , Long tipo_id)
	 * @return
	 * @throws BusinessException
	 */
	Fragmento findFragmentoByKey(FragmentoKey key) throws BusinessException;
	/**
	 * Retornara una lista con todos los fragmentos que correspondan al curso pasado por el parametro
	 * @param curso
	 * @return
	 * @throws BusinessException
	 */
	List<Fragmento> findAllFragmentoByCurso(Curso curso) throws BusinessException;
	
	/**
	 * A�ade un nuevo fragmetno a la base de datos
	 * @param fragmento
	 * @throws BusinessException
	 */
	void newFragmento(Fragmento fragmento) throws BusinessException;
	
	/**
	 * Elimina de la base de datos el fragmento que tenga de key la misma que el parametro
	 * @param key (Long curso_id , Long tipo_id)
	 * @throws BusinessException
	 */
	void deleteFragmento(FragmentoKey key) throws BusinessException;
	/**
	 * Actualiza el valor del fragmento por el nuevo dado en el parametro
	 * @param fragmento
	 * @throws BusinessException
	 */
	void updateFragmento(Fragmento fragmento) throws BusinessException;
	
	//tipos de vehiculos
	/**
	 * Devuelve una lista con todos los tipos de vehiculos
	 * @return List<Vehiculo>
	 * @throws BusinessException
	 */
	List<TipoVehiculo> listarTiposVehiculos() throws BusinessException;
	/**
	 * Retorna el tipo de vehiculo que tenga por nombre el mismo dado en el parametro
	 * @param String nombre
	 * @return TipoVehiculo.class
	 * @throws BusinessException
	 */
	TipoVehiculo findTipoVehiculoByNombre(String nombre) throws BusinessException;
	
	//Asistencias
	/**
	 * A�ade una nueva asistencia a la base de datos
	 * @param dniMecanico
	 * @param codigoCurso
	 * @param finicio
	 * @param ffinal
	 * @param pasistencia
	 * @param status
	 * @throws BusinessException en caso de algun dato mal introducido o que la asistencia ya exista
	 */
	void AddAsistencia(String dniMecanico, Long codigoCurso, Date finicio, Date ffinal, double pasistencia,
			AsistenciaStatus status) throws BusinessException;
	/**
	 * Devuelve una lista con todas las asitencias pertenecientes a un curso
	 * @param curso
	 * @return List<Asistencia>
	 * @throws BusinessException
	 */
	List<Asistencia> findAllAsistenciasByCurso(Curso curso) throws BusinessException;
	
	/**
	 * Este metodo comprobara si existe una asistencia dada por el parametro
	 * @param dniMecanico
	 * @param codigoCurso
	 * @param finicio
	 * @return el numero de asistencias que haya iguales a las del parametro
	 * @throws BusinessException
	 */
	Long existeAsistencia(String dniMecanico, Long codigoCurso, Date finicio) throws BusinessException;
	
	/**
	 * Actualiza una asitencia por la pasada en el parametro
	 * @param dniMecanico
	 * @param codigoCurso
	 * @param finicio
	 * @param ffinal
	 * @param pasistencia
	 * @param status
	 * @throws BusinessException
	 */
	void updateAsistencia(String dniMecanico,Long codigoCurso,Date finicio,
			Date ffinal,Double pasistencia,AsistenciaStatus status) throws BusinessException;
	/**
	 * Elimina una asitencia la asistencia tenga por key la del parametro 
	 * @param asistenciakey
	 * @throws BusinessException
	 */
	void deleteAsistencia(AsistenciaKey asistenciakey) throws BusinessException;
	/**
	 * A�ade un nuevo fragmento a la base de datos
	 * @param fragmento
	 * @throws BusinessException
	 */
	void addFragmento(Fragmento fragmento) throws BusinessException;
	
	//Mecanico
	/**
	 * Devuelve el mecanico que tenga por dni el del paremtro
	 * @param dniMecanico
	 * @return Mecanico
	 * @throws BusinessException
	 */
	Mecanico findMecanicoByDni(String dniMecanico) throws BusinessException;
	/**
	 * Este metodo generara todos los nuevos certificados de aquellos mecanicos que hayan superado las
	 * condiciones para obtener el certficado
	 * @throws BusinessException
	 */
	void generarCertificados() throws BusinessException;
	
	HashMap<String, Object> listarFormacion(Long mecanico_id) throws BusinessException;
	ArrayList<HashMap<String, Object>> listarFormacionPorTipos() throws BusinessException;

}
