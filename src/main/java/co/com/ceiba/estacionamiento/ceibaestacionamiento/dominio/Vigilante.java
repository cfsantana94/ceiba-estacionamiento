package co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.constantes.Constantes;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.reglas.CalcularTotalAcobrar;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.reglas.IReglas;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.reglas.ValidarDisponibilidad;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.reglas.ValidarPlaca;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.reglas.ValidarTipoVehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.servicio.ParqueaderoImpl;

public class Vigilante {

	private List<IReglas> reglasDeIngreso = new ArrayList<>();
	private List<IReglas> reglasDeSalida = new ArrayList<>();
	ParqueaderoImpl parqueaderoImpl;
	String mensaje = null;
	public Vigilante(ParqueaderoImpl parqueaderoImpl) {
		this.parqueaderoImpl = parqueaderoImpl;
		reglasDeIngreso.add(new ValidarDisponibilidad(parqueaderoImpl));
		reglasDeIngreso.add(new ValidarTipoVehiculo());
		reglasDeIngreso.add(new ValidarPlaca());
		reglasDeSalida.add(new CalcularTotalAcobrar());

	}

	public String ingresarVehiculo(Vehiculo vehiculo) {
		try {
			reglasDeIngreso.forEach(regla -> regla.ejecutar(vehiculo));
			parqueaderoImpl.ingresarVehiculo(vehiculo);
			mensaje = "El vehiculo con placa "+vehiculo.getPlaca()+" se ingreso correctamente a "+vehiculo.getFechaEntrada();
		} catch (Exception e) {
			mensaje = e.getMessage();
		}
		
		return mensaje;
	}

	public String sacarVehiculo(String placa,String tipoVehiculo) {
		try {
			Vehiculo vehiculo = parqueaderoImpl.buscarVehiculo(placa, tipoVehiculo, Constantes.ESTADO_ACTIVO );
			vehiculo.setFechaSalida(LocalDateTime.now());
			reglasDeSalida.forEach(regla -> regla.ejecutar(vehiculo));
			parqueaderoImpl.sacarVehiculo(vehiculo);
			mensaje = "El vehiculo con placa "+vehiculo.getPlaca()+" se retirado correctamente a "+vehiculo.getFechaSalida();
		} catch (Exception e) {
			mensaje = e.getMessage();
		}
		return mensaje;
		
	}
	
	public List<Vehiculo> consultarEstadoActualParqueadero(String estado){
		List<Vehiculo> listaVehiculos = parqueaderoImpl.consultarEstadoActualParqueadero(Constantes.ESTADO_ACTIVO);
		return listaVehiculos;
	}

}
