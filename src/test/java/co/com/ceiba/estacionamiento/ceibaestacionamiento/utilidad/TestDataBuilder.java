package co.com.ceiba.estacionamiento.ceibaestacionamiento.utilidad;

import java.time.LocalDateTime;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Carro;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Moto;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Vehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.constantes.Constantes;

public class TestDataBuilder {

	private String placa;
	private String tipoVehiculo;
	private double cilindraje;
	private double valorACobrar;
	private LocalDateTime fechaEntrada;
	private LocalDateTime fechaSalida;

	public TestDataBuilder() {
		this.placa = Constantes.PLACA_VEHICULO_CARRO;
		this.tipoVehiculo = Constantes.TIPO_VEHICULO_CARRO;
		this.cilindraje = 0;
		this.valorACobrar = 0;
		this.fechaEntrada = LocalDateTime.now();
		this.fechaSalida = null;
	}

	public TestDataBuilder(String tipoVehiculo) {
		this.placa = Constantes.PLACA_VEHICULO_CARRO;
		this.fechaEntrada = LocalDateTime.now();
		this.tipoVehiculo = tipoVehiculo;

	}

	public Vehiculo build() {
		if (this.tipoVehiculo.equals(Constantes.TIPO_VEHICULO_CARRO)) {
			return new Carro(this.placa, this.fechaEntrada, this.fechaSalida, this.valorACobrar, this.tipoVehiculo);
		}
		if (this.tipoVehiculo.equals(Constantes.TIPO_VEHICULO_MOTO)) {
			return new Moto(this.placa, this.fechaEntrada, this.fechaSalida, this.valorACobrar, this.tipoVehiculo,
					this.cilindraje);
		}
		return new Vehiculo(this.placa, this.fechaEntrada, this.fechaSalida, this.valorACobrar, this.tipoVehiculo);
	}

	public String getPlaca() {
		return placa;
	}

	public TestDataBuilder setPlaca(String placa) {
		this.placa = placa;
		return this;
	}

	public String getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(String tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
	}

	public double getCilindraje() {
		return cilindraje;
	}

	public TestDataBuilder setCilindraje(double cilindraje) {
		this.cilindraje = cilindraje;
		return this;
	}

	public double getValorACobrar() {
		return valorACobrar;
	}

	public void setValorACobrar(double valorACobrar) {
		this.valorACobrar = valorACobrar;
	}

	public LocalDateTime getFechaEntrada() {
		return fechaEntrada;
	}

	public TestDataBuilder setFechaEntrada(LocalDateTime fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
		return this;
	}

	public LocalDateTime getFechaSalida() {
		return fechaSalida;
	}

	public TestDataBuilder setFechaSalida(LocalDateTime fechaSalida) {
		this.fechaSalida = fechaSalida;
		return this;
	}

}
