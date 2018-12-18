package co.com.ceiba.estacionamiento.ceibaestacionamiento.integracion;


import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Vehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Vigilante;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.constantes.Constantes;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.servicio.ParqueaderoRepositorio;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.utilidad.TestDataBuilder;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.servicio.ParqueaderoImpl;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PruebasIntegracion {
	
	@Autowired
	ParqueaderoImpl parqueaderoImpl;
	
	@Autowired
	ParqueaderoRepositorio parqueaderoRepositorio;
	

	
	@Test
	public void ingresarVehiculoCarro() {
		Vehiculo carro = new TestDataBuilder(Constantes.TIPO_VEHICULO_CARRO).setPlaca(Constantes.PLACA_VEHICULO_CARRO).build();
		Vigilante vigilante= new Vigilante (parqueaderoImpl);
		vigilante.ingresarVehiculo(carro);
		Assert.assertNotNull(parqueaderoImpl.buscarVehiculo(carro.getPlaca(), carro.getTipoVehiculo(), Constantes.ESTADO_ACTIVO));
		
	}
	
	@Test
	public void ingresarVehiculoMoto() {
		Vehiculo moto = new TestDataBuilder(Constantes.TIPO_VEHICULO_MOTO).setPlaca("M30D").build();
		Vigilante vigilante= new Vigilante (parqueaderoImpl);
		vigilante.ingresarVehiculo(moto);
		Assert.assertNotNull(parqueaderoImpl.buscarVehiculo(moto.getPlaca(), moto.getTipoVehiculo(), Constantes.ESTADO_ACTIVO));
		
	}
	
	
	
	@Test
	public void sacarVehiculo() {
		Vehiculo  carro = new Vehiculo("DFD798",LocalDateTime.parse("2018-12-12T12:00:00"),null,0, Constantes.TIPO_VEHICULO_CARRO);
		Vigilante vigilante=new Vigilante(parqueaderoImpl);
		vigilante.ingresarVehiculo(carro);
		vigilante.sacarVehiculo(carro.getPlaca(),carro.getTipoVehiculo());
		Assert.assertNotNull(parqueaderoImpl.buscarVehiculo(carro.getPlaca(), carro.getTipoVehiculo(), Constantes.ESTADO_INACTIVO));
	}
	
	
	
}