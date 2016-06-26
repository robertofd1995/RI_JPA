package uo.ri.amp.model;

public class Association
{

	public static class Poseer
	{

		public static void link(Cliente cliente, Vehiculo vehiculo)
		{
			vehiculo._setCliente(cliente);
			cliente._getVehiculos().add(vehiculo);
			
		}

		public static void unlink(Cliente cliente, Vehiculo vehiculo)
		{
			cliente._getVehiculos().remove(vehiculo);
			vehiculo._setCliente(null);
			
		}
		
	}
	
	public static class Asistir
	{

		public static void link(Curso curso, Asistencia asistencia, Mecanico mecanico) {
			
			asistencia._setCurso(curso);
			asistencia._setMecanico(mecanico);
			
			curso._getAsistencias().add(asistencia);
			mecanico._getAsistencias().add(asistencia);
			
		}
		
		public static void unlink(Curso curso, Asistencia asistencia, Mecanico mecanico) {
			
			curso._getAsistencias().remove(asistencia);
			mecanico._getAsistencias().remove(asistencia);
			
			
			asistencia._setCurso(null);
			asistencia._setMecanico(null);
			
			
		}

		
		
	}
	
	public static class Fragmentar
	{

		public static void link(Curso curso, Fragmento fragmento)
		{
			fragmento._setCurso(curso);
			curso._getFragmentos().add(fragmento);	
		}

		public static void unlink(Curso curso, Fragmento fragmento)
		{
			curso._getFragmentos().remove(fragmento);
			fragmento._setCurso(null);
		}
		
	}
	
	public static class Certificar
	{

		public static void link(Certificado certificado, Mecanico mecanico)
		{
			
			//TODO Comprobar si cumple requisitos
			certificado.setMecanico(mecanico);
			mecanico._getCertificados().add(certificado);
			
		}

		public static void unlink(Certificado certificado, Mecanico mecanico)
		{
			
			mecanico._getCertificados().remove(certificado);
			certificado.setMecanico(null);
			
		}
		
		
	}

	public static class Clasificar
	{
		public static void link(TipoVehiculo tipoVehiculo, Vehiculo vehiculo)
		{
			vehiculo._setTipo(tipoVehiculo);
			tipoVehiculo._getVehiculos().add(vehiculo);
		}

		public static void unlink(TipoVehiculo tipoVehiculo, Vehiculo vehiculo)
		{
			tipoVehiculo._getVehiculos().remove(vehiculo);
			vehiculo._setTipo(null);
		}
	}

	public static class Pagar
	{
		public static void link(Cliente cliente, MedioPago medioPago)
		{
			medioPago._setCliente(cliente);
			cliente._getMediosPago().add(medioPago);
		}

		public static void unlink(Cliente cliente, MedioPago medioPago)
		{
			cliente._getMediosPago().remove(medioPago);
			medioPago._setCliente(null);
		}
	}

	/* package */static class Averiar
	{

		public static void link(Vehiculo vehiculo, Averia averia)
		{
			averia._setVehiculo(vehiculo);
			vehiculo._getAsignadas().add(averia);
			vehiculo.setNumAverias(vehiculo.getNumAverias()+1);
			
		}

		public static void unlink(Vehiculo vehiculo, Averia averia)
		{
			vehiculo._getAsignadas().remove(averia);
			averia._setVehiculo(null);
			vehiculo.setNumAverias(vehiculo.getNumAverias()-1);			
		}

	}

	/* package */static class Facturar
	{

		public static void link(Factura factura, Averia averia)
		{
			averia._setFactura(factura);
			factura._getAsignadas().add(averia);
		}

		public static void unlink(Factura factura, Averia averia)
		{
			factura._getAsignadas().remove(averia);
			averia._setFactura(null);
			
		}
		
	}

	public static class Cargar
	{

		public static void link(Cargo cargo, Factura factura, MedioPago medioPago)
		{
			cargo._setFactura(factura);
			cargo._setMedioPago(medioPago);
			
			factura._getCargos().add(cargo);
			medioPago._getCargos().add(cargo);

			cargo.getMedioPago().setAcumulado(cargo.getImporte());	
			medioPago.getCliente()._getMediosPago().add(medioPago);
		}

		public static void unlink(Cargo cargo)
		{
			cargo.getMedioPago().setAcumulado(0);
			
			cargo.getFactura()._getCargos().remove(cargo);
			cargo.getMedioPago()._getCargos().remove(cargo);
			
			cargo.getMedioPago().getCliente()._getMediosPago().add(null);

			cargo._setFactura(null);
			cargo._setMedioPago(null);
			
		}
	}

	public static class Asignar
	{
		public static void link(Mecanico mecanico, Averia averia)
		{
			averia._setMecanico(mecanico);
			mecanico._getAsignadas().add(averia);
		}

		public static void unlink(Mecanico mecanico, Averia averia)
		{
			mecanico._getAsignadas().remove(averia);
			averia._setMecanico(null);
		}
	}

	public static class Intervenir
	{

		public static void link(Averia averia, Intervencion intervencion, Mecanico mecanico)
		{
			intervencion._setAveria(averia);
			intervencion._setMecanico(mecanico);
			averia._setMecanico(mecanico);

			averia._getIntervenciones().add(intervencion);
			mecanico._getIntervenciones().add(intervencion);
		}

		public static void unlink(Intervencion intervencion)
		{

			intervencion.getAveria()._getIntervenciones().remove(intervencion);
			intervencion.getMecanico()._getIntervenciones().remove(intervencion);
			intervencion.getAveria()._setMecanico(null);
			
			intervencion._setAveria(null);
			intervencion._setMecanico(null);

		}
	}

	public static class Sustituir
	{

		public static void link(Sustitucion sustitucion, Repuesto repuesto, Intervencion intervencion)
		{
			sustitucion._setIntevencion(intervencion);
			sustitucion._setRepuesto(repuesto);
			
			intervencion._getSustituciones().add(sustitucion);
			repuesto._getSustituciones().add(sustitucion);
			
		}

		public static void unlink(Sustitucion sustitucion)
		{
			sustitucion.getIntervencion()._getSustituciones().remove(sustitucion);
			sustitucion.getRepuesto()._getSustituciones().remove(sustitucion);
			
			sustitucion._setIntevencion(null);
			sustitucion._setRepuesto(null);
			
		}

	}

}
