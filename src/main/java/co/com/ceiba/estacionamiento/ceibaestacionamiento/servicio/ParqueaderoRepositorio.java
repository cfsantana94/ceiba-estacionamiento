package co.com.ceiba.estacionamiento.ceibaestacionamiento.servicio;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.entity.ParqueaderoEntity;

@Repository
public interface ParqueaderoRepositorio extends JpaRepository<ParqueaderoEntity, Long> {

	public long countByTipoVehiculoAndEstado(String tipoVehiculo, String estado);
	
	public ParqueaderoEntity findByTipoVehiculoAndPlacaAndEstado(String tipoVehiculo, String placa,String estado);
	
	public List <ParqueaderoEntity> findByEstado(String estado);
}
