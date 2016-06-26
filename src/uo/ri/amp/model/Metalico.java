package uo.ri.amp.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("TMetalico")
public class Metalico extends MedioPago {

	public Metalico(){}
	
	public Metalico(Cliente cliente)
	{
		super(cliente);
	}
	
	
}
