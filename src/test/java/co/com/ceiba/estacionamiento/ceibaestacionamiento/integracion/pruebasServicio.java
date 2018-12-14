package co.com.ceiba.estacionamiento.ceibaestacionamiento.integracion;

import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.reglas.CalcularTotalAcobrar;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.reglas.ValidarDisponibilidad;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.reglas.ValidarPlaca;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.reglas.ValidarTipoVehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.servicio.ParqueaderoImpl;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.servicio.ParqueaderoRepositorio;

@RunWith(MockitoJUnitRunner.class)
public class pruebasServicio {

	@InjectMocks
	ParqueaderoImpl parqueaderoImpl;
	
	@Mock
	ParqueaderoRepositorio parqueaderoRepositorio;
	
	@Mock
	ValidarDisponibilidad validarDisponibilidad;
	
	@Mock
	ValidarTipoVehiculo validarTipoVehiculo;
	
	@Mock
	ValidarPlaca validarPlaca;
	
	@Mock
	CalcularTotalAcobrar calcularTotalAcobrar;
	
	
	
	
}
