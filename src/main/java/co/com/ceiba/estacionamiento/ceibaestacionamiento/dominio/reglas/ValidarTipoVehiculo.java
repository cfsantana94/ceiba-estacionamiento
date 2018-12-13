package co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.reglas;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Vehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.constantes.Constantes;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.excepcion.Excepciones;

public class ValidarTipoVehiculo implements IReglas {

	@Override
	public void ejecutar(Vehiculo vehiculo) {

		if ((!vehiculo.getTipoVehiculo().equals(Constantes.TIPO_VEHICULO_CARRO))
				&& (!vehiculo.getTipoVehiculo().equals(Constantes.TIPO_VEHICULO_MOTO))) {

			throw new Excepciones(Constantes.MENSAJE_TIPO_VEHICULO_NO_ACEPTADO);

		}
	}
}