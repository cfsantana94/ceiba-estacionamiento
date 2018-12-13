package co.com.ceiba.estacionamiento.ceibaestacionamiento.controlador;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Parqueadero;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Vehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Vigilante;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.entity.ParqueaderoEntity;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.servicio.ParqueaderoImpl;

@RestController
@RequestMapping(value = "/parqueadero")
public class ParqueaderoController {

	@Autowired
	ParqueaderoImpl parqueaderoImpl;

	@PostMapping(value = "/ingresarVehiculo")
	public void ingresarVehiculo(@RequestBody Parqueadero parqueadero) {

		Vigilante vigilante = new Vigilante(parqueaderoImpl);
		vigilante.ingresarVehiculo(parqueadero.crearVehiculo());

	}

	@PutMapping(value = "/sacarVehiculo")
	public void sacarVehiculo(@RequestBody Vehiculo vehiculo) {
		Vigilante vigilante = new Vigilante(parqueaderoImpl);
		vigilante.sacarVehiculo(vehiculo.getPlaca(), vehiculo.getTipoVehiculo());
	}

	@GetMapping("{id}")
	public ParqueaderoEntity getParqueaderoEntity() {
		ParqueaderoEntity parqueaderoEntity = new ParqueaderoEntity();
		parqueaderoEntity.setCilindraje(0);
		parqueaderoEntity.setEstado("A");
		parqueaderoEntity.setFechaEntrada(LocalDateTime.now());
		parqueaderoEntity.setFechaSalida(null);
		parqueaderoEntity.setPlaca("CCX659");
		parqueaderoEntity.setTipoVehiculo("CARRO");
		parqueaderoEntity.setValorACobrar(2000);
		return parqueaderoEntity;
	}

}
