package co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.reglas;

import java.time.DayOfWeek;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Vehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.constantes.Constantes;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.excepcion.Excepciones;

public class ValidarPlaca implements IReglas {


	@Override
	public void ejecutar(Vehiculo vehiculo) {

		if ((vehiculo.getPlaca().toUpperCase().startsWith(Constantes.LETRA_INICIAL_PLACA))
				&& ((vehiculo.getFechaEntrada().getDayOfWeek().compareTo(DayOfWeek.MONDAY))!= 0)
				&& ((vehiculo.getFechaEntrada().getDayOfWeek().compareTo(DayOfWeek.SUNDAY))!= 0)) {
			
			throw new Excepciones(Constantes.MENSAJE_RESTRICCION_DIAS_DOMINGO_LUNES);
		}
	}

}
