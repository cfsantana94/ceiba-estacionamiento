package co.com.ceiba.estacionamiento.ceibaestacionamiento.entity;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Carro;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Moto;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Vehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.constantes.Constantes;

public class ParqueaderoBuilder {

	public static ParqueaderoEntity transformacionEntidad(Vehiculo vehiculo) {
		ParqueaderoEntity parqueaderoEntity = new ParqueaderoEntity();
		parqueaderoEntity.setFechaEntrada(vehiculo.getFechaEntrada());
		parqueaderoEntity.setFechaSalida(vehiculo.getFechaSalida());
		parqueaderoEntity.setPlaca(vehiculo.getPlaca());
		parqueaderoEntity.setValorACobrar(vehiculo.getValorACobrar());
		parqueaderoEntity.setTipoVehiculo(vehiculo.getTipoVehiculo());

		if (vehiculo.getTipoVehiculo().equals(Constantes.TIPO_VEHICULO_MOTO)) {
			parqueaderoEntity.setCilindraje(((Moto) vehiculo).getCilindraje());
		}
		return parqueaderoEntity;
	}

	public static Vehiculo transformacionDominio(ParqueaderoEntity parqueaderoEntity) {
		Vehiculo vehiculo = null;

		if (parqueaderoEntity != null) {
			if (parqueaderoEntity.getTipoVehiculo().equals(Constantes.TIPO_VEHICULO_MOTO)) {
				vehiculo = new Moto(parqueaderoEntity.getPlaca(), parqueaderoEntity.getFechaEntrada(),
						parqueaderoEntity.getFechaSalida(), parqueaderoEntity.getValorACobrar(),
						parqueaderoEntity.getTipoVehiculo(), parqueaderoEntity.getCilindraje());
			}

			if (parqueaderoEntity.getTipoVehiculo().equals(Constantes.TIPO_VEHICULO_CARRO)) {
				vehiculo = new Carro(parqueaderoEntity.getPlaca(), parqueaderoEntity.getFechaEntrada(),
						parqueaderoEntity.getFechaSalida(), parqueaderoEntity.getValorACobrar(),
						parqueaderoEntity.getTipoVehiculo());
			}
		}

		return vehiculo;
	}

}
