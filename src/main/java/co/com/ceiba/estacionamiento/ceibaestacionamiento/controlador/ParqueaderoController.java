package co.com.ceiba.estacionamiento.ceibaestacionamiento.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Parqueadero;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Vehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Vigilante;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.servicio.ParqueaderoImpl;

@RestController
@RequestMapping(value = "/parqueadero")
public class ParqueaderoController {

	@Autowired
	ParqueaderoImpl parqueaderoImpl;

	@PostMapping(value = "/ingresarVehiculo")
	public String ingresarVehiculo(@RequestBody Parqueadero parqueadero) {

		Vigilante vigilante = new Vigilante(parqueaderoImpl);
		return vigilante.ingresarVehiculo(parqueadero.crearVehiculo());

	}

	@PutMapping(value = "/sacarVehiculo")
	public String sacarVehiculo(@RequestBody Vehiculo vehiculo) {
		Vigilante vigilante = new Vigilante(parqueaderoImpl);
		 return vigilante.sacarVehiculo(vehiculo.getPlaca(), vehiculo.getTipoVehiculo());
	}

	@RequestMapping(value = "/listarVehiculosParqueados")
	public List<Vehiculo> consultarEstadoActualParqueados(@RequestBody String estado) {
		Vigilante vigilante = new Vigilante(parqueaderoImpl);
		return vigilante.consultarEstadoActualParqueadero(estado);
	}

}
