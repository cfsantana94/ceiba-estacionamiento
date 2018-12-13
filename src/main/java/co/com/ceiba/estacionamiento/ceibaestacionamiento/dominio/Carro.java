package co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio;

import java.time.LocalDateTime;

public class Carro extends Vehiculo {

	public Carro() {
	}

	public Carro(String placa, LocalDateTime fechaEntrada, LocalDateTime fechaSalida, double valorACobrar,
			String tipoVehiculo) {
		super(placa, fechaEntrada, fechaSalida, valorACobrar, tipoVehiculo);
	}

}