package co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.reglas;

import org.springframework.beans.factory.annotation.Autowired;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Vehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.constantes.Constantes;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.excepcion.Excepciones;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.servicio.ParqueaderoImpl;

public class ValidarDisponibilidad implements IReglas {

	@Autowired
	ParqueaderoImpl parqueaderoImpl;

	public ValidarDisponibilidad(ParqueaderoImpl parqueaderoImpl) {
		this.parqueaderoImpl = parqueaderoImpl;
	}

	@Override
	public void ejecutar(Vehiculo vehiculo) {
		long cantidadVehiculosEstacionados = parqueaderoImpl.verificarCantidad(vehiculo.getTipoVehiculo(),
				Constantes.ESTADO_ACTIVO);

		if (vehiculo.getTipoVehiculo().equals(Constantes.TIPO_VEHICULO_CARRO)
				&& cantidadVehiculosEstacionados >= Constantes.CAPACIDAD_CARRO) {
			throw new Excepciones(Constantes.MENSAJE_CUPO_CARRO_LLENO);
		}
		if (vehiculo.getTipoVehiculo().equals(Constantes.TIPO_VEHICULO_MOTO)
				&& cantidadVehiculosEstacionados >= Constantes.CAPACIDAD_MOTO) {
			throw new Excepciones(Constantes.MENSAJE_CUPO_MOTO_LLENO);
		}
	}

}
