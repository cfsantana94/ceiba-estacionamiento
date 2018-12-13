package co.com.ceiba.estacionamiento.ceibaestacionamiento.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "estacionamiento")
public class ParqueaderoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private String placa;
	private LocalDateTime fechaEntrada;
	private LocalDateTime fechaSalida;
	private String tipoVehiculo;
	private double valorACobrar;
	private double cilindraje;
	private String estado;

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
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

	public ParqueaderoEntity() {
	}

	public ParqueaderoEntity(String placa, LocalDateTime fechaEntrada, LocalDateTime fechaSalida, String tipoVehiculo,
			double cilindraje, double valorACobrar) {
		this.placa = placa;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.tipoVehiculo = tipoVehiculo;
		this.cilindraje = cilindraje;
		this.valorACobrar = valorACobrar;
	}

	public double getCilindraje() {
		return cilindraje;
	}

	public void setCilindraje(double cilindraje) {
		this.cilindraje = cilindraje;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
