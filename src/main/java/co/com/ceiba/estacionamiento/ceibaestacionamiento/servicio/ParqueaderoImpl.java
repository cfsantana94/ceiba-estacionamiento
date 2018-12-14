package co.com.ceiba.estacionamiento.ceibaestacionamiento.servicio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Vehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.constantes.Constantes;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.entity.ParqueaderoBuilder;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.entity.ParqueaderoEntity;

@Service
public class ParqueaderoImpl {

	@Autowired
	ParqueaderoRepositorio parqueaderoRepositorio;

	public void ingresarVehiculo(Vehiculo vehiculo) {
			ParqueaderoEntity parqueaderoEntity = ParqueaderoBuilder.transformacionEntidad(vehiculo);
			parqueaderoEntity.setEstado(Constantes.ESTADO_ACTIVO);
			parqueaderoRepositorio.save(parqueaderoEntity);
	
	}

	public long verificarCantidad(String tipoVehiculo, String estado) {
		return parqueaderoRepositorio.countByTipoVehiculoAndEstado(tipoVehiculo, estado);
	}

	public void sacarVehiculo(Vehiculo vehiculo) {
			ParqueaderoEntity parqueaderoEntity = parqueaderoRepositorio.findByTipoVehiculoAndPlacaAndEstado(
					vehiculo.getTipoVehiculo(), vehiculo.getPlaca(), Constantes.ESTADO_ACTIVO);
			parqueaderoEntity.setEstado(Constantes.ESTADO_INACTIVO);
			parqueaderoEntity.setFechaSalida(LocalDateTime.now());
			parqueaderoEntity.setValorACobrar(vehiculo.getValorACobrar());
			parqueaderoRepositorio.save(parqueaderoEntity);
		}

	public Vehiculo buscarVehiculo(String placa, String tipoVehiculo, String estado) {
		ParqueaderoEntity parqueaderoEntity = parqueaderoRepositorio.findByTipoVehiculoAndPlacaAndEstado(tipoVehiculo,
				placa, estado);
		return ParqueaderoBuilder.transformacionDominio(parqueaderoEntity);
	}

	public List<Vehiculo> consultarEstadoActualParqueadero(String estado) {
		List<Vehiculo> parqueadero = new ArrayList<Vehiculo>();
		List<ParqueaderoEntity> parqueaderoEntity = parqueaderoRepositorio.findByEstado(estado);
		for (ParqueaderoEntity vehiculo : parqueaderoEntity) {
			parqueadero.add(ParqueaderoBuilder.transformacionDominio(vehiculo));
		}
		return parqueadero;
	}

}
