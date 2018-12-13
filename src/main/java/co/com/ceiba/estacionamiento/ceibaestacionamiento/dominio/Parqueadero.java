package co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio;

import java.time.LocalDateTime;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.constantes.Constantes;



public class Parqueadero {
	private String placa;
	private LocalDateTime fechaEntrada;
	private LocalDateTime fechaSalida;
	private double valorACobrar;
	private double cilindraje;
	private String tipoVehiculo;

	public Parqueadero() {
	}

	public void setFechaEntrada(LocalDateTime fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public LocalDateTime getFechaEntrada() {
		return fechaEntrada;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getPlaca() {
		return placa;
	}

	public void setFechaSalida(LocalDateTime fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public LocalDateTime getFechaSalida() {
		return fechaSalida;
	}

	public void setValorACobrar(double valorACobrar) {
		this.valorACobrar = valorACobrar;
	}

	public double getValorACobrar() {
		return valorACobrar;
	}

	public void setCilindraje(double cilindraje) {
		this.cilindraje = cilindraje;
	}

	public double getCilindraje() {
		return cilindraje;
	}

	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public Vehiculo crearVehiculo() {
		if (getTipoVehiculo().equals(Constantes.TIPO_VEHICULO_CARRO))
			return new Carro(getPlaca(), LocalDateTime.now(), getFechaSalida(), getValorACobrar(), getTipoVehiculo());
		else
			return new Moto(getPlaca(), LocalDateTime.now(), getFechaSalida(), getValorACobrar(), getTipoVehiculo(),
					getCilindraje());
	}

}
