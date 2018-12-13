package co.com.ceiba.estacionamiento.ceibaestacionamiento.unitaria;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Vehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.constantes.Constantes;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.reglas.ValidarPlaca;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.reglas.ValidarTipoVehiculo;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.utilidad.TestDataBuilder;

@SuppressWarnings("deprecation")
@RunWith(MockitoJUnitRunner.class)
public class PruebasUnitarias {

	@InjectMocks
	ValidarTipoVehiculo validarTipoVehiculo;

	@InjectMocks
	ValidarPlaca validarPlaca;

	@Test
	public void validarTipoVehiculoCarroTest() {
		// Arrange
		Vehiculo vehiculo = new TestDataBuilder(Constantes.TIPO_VEHICULO_CARRO).build();

		try {
			// Act
			validarTipoVehiculo.ejecutar(vehiculo);
		} catch (Exception e) {
			// Assert
			assertNotEquals(e.getMessage(), Constantes.MENSAJE_TIPO_VEHICULO_NO_ACEPTADO);

		}
	}

	@Test
	public void validarTipoVehiculoMotoTest() {
		// Arrange
		Vehiculo vehiculo = new TestDataBuilder(Constantes.TIPO_VEHICULO_MOTO).build();

		try {
			// Act
			validarTipoVehiculo.ejecutar(vehiculo);
		} catch (Exception e) {
			// Assert
			assertNotEquals(e.getMessage(), Constantes.MENSAJE_TIPO_VEHICULO_NO_ACEPTADO);

		}
	}

	@Test
	public void validarTipoVehiculoIncorrectoTest() {
		Vehiculo vehiculo = new TestDataBuilder(Constantes.TIPO_VEHICULO_ERRADO).build();
		try {
			// Act
			validarTipoVehiculo.ejecutar(vehiculo);

		} catch (Exception e) {
			// Assert
			Assert.assertEquals(Constantes.MENSAJE_TIPO_VEHICULO_NO_ACEPTADO, e.getMessage());

		}
	}

	@Test
	public void validarPlacaConRestriccionTest() {
		TestDataBuilder testDataBuilder = new TestDataBuilder(Constantes.TIPO_VEHICULO_CARRO);
		Vehiculo vehiculo = testDataBuilder.setPlaca(Constantes.PLACA_VEHICULO_CARRO_INICIO_CON_RESTRI)
				.setFechaEntrada(LocalDateTime.parse("2018-12-10T07:00:00")).build();

		try {
			validarPlaca.ejecutar(vehiculo);
		} catch (Exception e) {
			assertEquals(Constantes.MENSAJE_RESTRICCION_DIAS_DOMINGO_LUNES, e.getMessage());
		}
	}

	@Test
	public void validarPlacaSinRestriccionTest() {
		TestDataBuilder testDataBuilder = new TestDataBuilder(Constantes.TIPO_VEHICULO_CARRO);
		Vehiculo vehiculo = testDataBuilder.setPlaca(Constantes.PLACA_VEHICULO_CARRO)
				.setFechaEntrada(LocalDateTime.parse("2018-12-10T07:00:00")).build();
		try {
			validarPlaca.ejecutar(vehiculo);
		} catch (Exception e) {
			assertNotEquals(Constantes.MENSAJE_RESTRICCION_DIAS_DOMINGO_LUNES, e.getMessage());
		}
	}

}
