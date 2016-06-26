package uo.ri.amp.business.impl.foreman;

import uo.ri.amp.model.Averia;
import uo.ri.business.impl.Command;
import uo.ri.persistence.util.Jpa;

public class AddAveria implements Command {
	
	private Averia averia;

	public AddAveria(Averia averia) {
		this.averia=averia;
	}

	@Override
	public Averia execute() {
		Jpa.getManager().persist(averia);
		return averia;

	}

}
