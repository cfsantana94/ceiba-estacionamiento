package co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio;

import java.time.LocalDateTime;

public class Moto extends Vehiculo {

	public Moto() {
	}

	private double cilindraje;

	public Moto(String placa, LocalDateTime fechaEntrada, LocalDateTime fechaSalida, double valorACobrar,
			String tipoVehiculo, double cilindraje) {
		super(placa, fechaEntrada, fechaSalida, valorACobrar, tipoVehiculo);
		this.cilindraje = cilindraje;
	}

	public double getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(double cilindraje) {
		this.cilindraje = cilindraje;
	}

}