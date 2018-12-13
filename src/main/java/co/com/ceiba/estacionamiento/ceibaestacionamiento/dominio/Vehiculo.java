package co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio;


import java.time.LocalDateTime;

public class Vehiculo {

	public Vehiculo() {

	}

	private LocalDateTime fechaEntrada;
	private LocalDateTime fechaSalida;
	private String placa;
	private String tipoVehiculo;
	private double valorACobrar;

	public Vehiculo(String placa, LocalDateTime fechaEntrada, LocalDateTime fechaSalida, double valorACobrar,
			String tipoVehiculo) {
		this.placa = placa;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.valorACobrar = valorACobrar;
		this.tipoVehiculo = tipoVehiculo;
	}

	public LocalDateTime getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(LocalDateTime fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public LocalDateTime getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(LocalDateTime fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	public double getValorACobrar() {
		return valorACobrar;
	}

	public void setValorACobrar(double valorACobrar) {
		this.valorACobrar = valorACobrar;
	}

}