package co.com.ceiba.estacionamiento.ceibaestacionamiento.unitaria;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Vehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.constantes.Constantes;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.reglas.CalcularTotalAcobrar;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.reglas.ValidarDisponibilidad;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.reglas.ValidarPlaca;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.reglas.ValidarTipoVehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.servicio.ParqueaderoImpl;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.servicio.ParqueaderoRepositorio;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.utilidad.TestDataBuilder;

@SuppressWarnings("deprecation")
@RunWith(MockitoJUnitRunner.class)
public class PruebasUnitarias {

	@Mock
	ParqueaderoImpl parqueaderoImpl;

	@Mock
	ParqueaderoRepositorio parqueaderoRepositorio;

	@InjectMocks
	ValidarTipoVehiculo validarTipoVehiculo;

	@InjectMocks
	ValidarPlaca validarPlaca;

	@InjectMocks
	ValidarDisponibilidad validarDisponibilidad;

	@InjectMocks
	CalcularTotalAcobrar calcularTotalAcobrar;

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
	public void validarPlacaConRestriccionLunesTest() {
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
	public void validarPlacaConRestriccionDomingoTest() {
		TestDataBuilder testDataBuilder = new TestDataBuilder(Constantes.TIPO_VEHICULO_CARRO);
		Vehiculo vehiculo = testDataBuilder.setPlaca(Constantes.PLACA_VEHICULO_CARRO_INICIO_CON_RESTRI)
				.setFechaEntrada(LocalDateTime.parse("2018-12-09T07:00:00")).build();

		try {
			validarPlaca.ejecutar(vehiculo);
		} catch (Exception e) {
			assertEquals(Constantes.MENSAJE_RESTRICCION_DIAS_DOMINGO_LUNES, e.getMessage());
		}
	}

	@Test
	public void validarPlacaConRestriccionMartesTest() {
		TestDataBuilder testDataBuilder = new TestDataBuilder(Constantes.TIPO_VEHICULO_CARRO);
		Vehiculo vehiculo = testDataBuilder.setPlaca(Constantes.PLACA_VEHICULO_CARRO_INICIO_CON_RESTRI)
				.setFechaEntrada(LocalDateTime.parse("2018-12-11T07:00:00")).build();

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

	@Test
	public void validarNoHayDisponibilidadCarroTest() {
		Vehiculo vehiculo = new TestDataBuilder(Constantes.TIPO_VEHICULO_CARRO).build();

		when(parqueaderoImpl.verificarCantidad(vehiculo.getTipoVehiculo(), Constantes.ESTADO_ACTIVO))
				.thenReturn((long) Constantes.CAPACIDAD_CARRO);

		try {
			validarDisponibilidad.ejecutar(vehiculo);
		} catch (Exception e) {
			assertEquals(Constantes.MENSAJE_CUPO_CARRO_LLENO, e.getMessage());
		}
	}

	@Test
	public void validarDisponibilidadCarroTest() {
		Vehiculo vehiculo = new TestDataBuilder(Constantes.TIPO_VEHICULO_CARRO).build();

		when(parqueaderoImpl.verificarCantidad(vehiculo.getTipoVehiculo(), Constantes.ESTADO_ACTIVO))
				.thenReturn((long) Constantes.CAPACIDAD_LIBRE_CARRO);

		try {
			validarDisponibilidad.ejecutar(vehiculo);
		} catch (Exception e) {
			assertEquals(Constantes.MENSAJE_CUPO_CARRO_LLENO, e.getMessage());
		}
	}

	@Test
	public void validarNoHayDisponibilidadMotoTest() {
		Vehiculo vehiculo = new TestDataBuilder(Constantes.TIPO_VEHICULO_MOTO).build();

		when(parqueaderoImpl.verificarCantidad(vehiculo.getTipoVehiculo(), Constantes.ESTADO_ACTIVO))
				.thenReturn((long) Constantes.CAPACIDAD_MOTO);

		try {
			validarDisponibilidad.ejecutar(vehiculo);
		} catch (Exception e) {
			assertEquals(Constantes.MENSAJE_CUPO_MOTO_LLENO, e.getMessage());
		}
	}

	@Test
	public void validarDisponibilidadMotoTest() {
		Vehiculo vehiculo = new TestDataBuilder(Constantes.TIPO_VEHICULO_MOTO).build();

		when(parqueaderoImpl.verificarCantidad(vehiculo.getTipoVehiculo(), Constantes.ESTADO_ACTIVO))
				.thenReturn((long) Constantes.CAPACIDAD_LIBRE_MOTO);

		try {
			validarDisponibilidad.ejecutar(vehiculo);
		} catch (Exception e) {
			assertEquals(Constantes.MENSAJE_CUPO_MOTO_LLENO, e.getMessage());
		}
	}

	@Test
	public void validarTiempoNoMasDeUnaHoraTest() {

		int[] tiempo = calcularTotalAcobrar.calcularTiempo(LocalDateTime.parse("2018-12-10T07:00:00"),
				LocalDateTime.parse("2018-12-10T07:15:00"));

		assertEquals(0, tiempo[0]);// dias
		assertEquals(1, tiempo[1]);// horas

	}

	@Test
	public void validarTiempoMasDeUnaHoraTest() {

		int[] tiempo = calcularTotalAcobrar.calcularTiempo(LocalDateTime.parse("2018-12-10T07:00:00"),
				LocalDateTime.parse("2018-12-10T09:15:00"));

		assertEquals(0, tiempo[0]);// dias
		assertEquals(3, tiempo[1]);// horas

	}

	@Test
	public void validarTiempoMasDeUnDiaTest() {

		int[] tiempo = calcularTotalAcobrar.calcularTiempo(LocalDateTime.parse("2018-12-10T00:00:00"),
				LocalDateTime.parse("2018-12-12T01:00:00"));

		assertEquals(2, tiempo[0]);// dias
		assertEquals(1, tiempo[1]);// horas

	}

	@Test
	public void validarTotalACobrarCarroUnaHoraTest() {
		TestDataBuilder testDataBuilder = new TestDataBuilder(Constantes.TIPO_VEHICULO_CARRO);
		Vehiculo vehiculo = testDataBuilder.setFechaEntrada(LocalDateTime.parse("2018-12-10T07:00:00"))
				.setFechaSalida(LocalDateTime.parse("2018-12-10T07:15:00")).build();

		calcularTotalAcobrar.ejecutar(vehiculo);
		Assert.assertEquals(Constantes.VALOR_HORA_CARRO, vehiculo.getValorACobrar(), 0);

	}

	@Test
	public void validarTotalACobrarCarroMasDeUnaHoraTest() {
		TestDataBuilder testDataBuilder = new TestDataBuilder(Constantes.TIPO_VEHICULO_CARRO);
		Vehiculo vehiculo = testDataBuilder.setFechaEntrada(LocalDateTime.parse("2018-12-10T07:00:00"))
				.setFechaSalida(LocalDateTime.parse("2018-12-10T09:15:00")).build();

		calcularTotalAcobrar.ejecutar(vehiculo);
		Assert.assertEquals(3000, vehiculo.getValorACobrar(), 0);

	}

	@Test
	public void validarTotalACobrarCarroMasDeUnDiaTest() {
		TestDataBuilder testDataBuilder = new TestDataBuilder(Constantes.TIPO_VEHICULO_CARRO);
		Vehiculo vehiculo = testDataBuilder.setFechaEntrada(LocalDateTime.parse("2018-12-10T00:00:00"))
				.setFechaSalida(LocalDateTime.parse("2018-12-12T01:00:00")).build();

		calcularTotalAcobrar.ejecutar(vehiculo);
		Assert.assertEquals(17000, vehiculo.getValorACobrar(), 0);

	}

	@Test
	public void validarTotalACobrarMotoUnaHoraTest() {
		TestDataBuilder testDataBuilder = new TestDataBuilder(Constantes.TIPO_VEHICULO_MOTO);
		Vehiculo vehiculo = testDataBuilder.setFechaEntrada(LocalDateTime.parse("2018-12-10T07:00:00"))
				.setFechaSalida(LocalDateTime.parse("2018-12-10T07:15:00")).build();

		calcularTotalAcobrar.ejecutar(vehiculo);
		Assert.assertEquals(Constantes.VALOR_HORA_MOTO, vehiculo.getValorACobrar(), 0);

	}

	@Test
	public void validarTotalACobrarMotoUnaHoraConCCTest() {
		TestDataBuilder testDataBuilder = new TestDataBuilder(Constantes.TIPO_VEHICULO_MOTO);
		Vehiculo vehiculo = testDataBuilder.setCilindraje(Constantes.CILINDRAJE_MAX_MOTO)
				.setFechaEntrada(LocalDateTime.parse("2018-12-10T07:00:00"))
				.setFechaSalida(LocalDateTime.parse("2018-12-10T07:15:00")).build();

		calcularTotalAcobrar.ejecutar(vehiculo);
		Assert.assertEquals(2500, vehiculo.getValorACobrar(), 0);

	}

	@Test
	public void validarTotalACobrarMotoMasDeUnaHoraTest() {
		TestDataBuilder testDataBuilder = new TestDataBuilder(Constantes.TIPO_VEHICULO_MOTO);
		Vehiculo vehiculo = testDataBuilder.setFechaEntrada(LocalDateTime.parse("2018-12-10T07:00:00"))
				.setFechaSalida(LocalDateTime.parse("2018-12-10T09:15:00")).build();

		calcularTotalAcobrar.ejecutar(vehiculo);
		Assert.assertEquals(1500, vehiculo.getValorACobrar(), 0);

	}

	@Test
	public void validarTotalACobrarMotoMasDeUnaConCCHoraTest() {
		TestDataBuilder testDataBuilder = new TestDataBuilder(Constantes.TIPO_VEHICULO_MOTO);
		Vehiculo vehiculo = testDataBuilder.setFechaEntrada(LocalDateTime.parse("2018-12-10T07:00:00"))
				.setFechaSalida(LocalDateTime.parse("2018-12-10T09:15:00"))
				.setCilindraje(Constantes.CILINDRAJE_MAX_MOTO).build();

		calcularTotalAcobrar.ejecutar(vehiculo);
		Assert.assertEquals(3500, vehiculo.getValorACobrar(), 0);
	}

	@Test
	public void validarTotalACobrarMotoMasDeUnDiaTest() {
		TestDataBuilder testDataBuilder = new TestDataBuilder(Constantes.TIPO_VEHICULO_MOTO);
		Vehiculo vehiculo = testDataBuilder.setFechaEntrada(LocalDateTime.parse("2018-12-10T00:00:00"))
				.setFechaSalida(LocalDateTime.parse("2018-12-12T01:00:00")).build();

		calcularTotalAcobrar.ejecutar(vehiculo);
		Assert.assertEquals(8500, vehiculo.getValorACobrar(), 0);

	}

	@Test
	public void validarTotalACobrarMotoMasDeUnDiaConCCTest() {
		TestDataBuilder testDataBuilder = new TestDataBuilder(Constantes.TIPO_VEHICULO_MOTO);
		Vehiculo vehiculo = testDataBuilder.setFechaEntrada(LocalDateTime.parse("2018-12-10T00:00:00"))
				.setFechaSalida(LocalDateTime.parse("2018-12-12T01:00:00"))
				.setCilindraje(Constantes.CILINDRAJE_MAX_MOTO).build();

		calcularTotalAcobrar.ejecutar(vehiculo);
		Assert.assertEquals(10500, vehiculo.getValorACobrar(), 0);

	}
}
