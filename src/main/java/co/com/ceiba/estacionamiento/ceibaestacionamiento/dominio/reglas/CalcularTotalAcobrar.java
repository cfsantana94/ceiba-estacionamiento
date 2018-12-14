package co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.reglas;

import java.time.Duration;
import java.time.LocalDateTime;

import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Moto;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.Vehiculo;
import co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.constantes.Constantes;

public class CalcularTotalAcobrar implements IReglas {

	@Override
	public void ejecutar(Vehiculo vehiculo) {
		double totalAPagar = calculartotal(vehiculo);
		vehiculo.setValorACobrar(totalAPagar);
	}

	public double calculartotal(Vehiculo vehiculo) {
		int[] tiempo = calcularTiempo(vehiculo.getFechaEntrada(), vehiculo.getFechaSalida());
		double valorACobrar = 0;
		if (vehiculo.getTipoVehiculo().equals(Constantes.TIPO_VEHICULO_CARRO)) {
			valorACobrar += Constantes.VALOR_HORA_CARRO * tiempo[1];
			valorACobrar += Constantes.VALOR_DIA_CARRO * tiempo[0];
		} else if (vehiculo.getTipoVehiculo().equals(Constantes.TIPO_VEHICULO_MOTO)) {
			Moto moto = (Moto) vehiculo;
			valorACobrar += Constantes.VALOR_HORA_MOTO * tiempo[1];
			valorACobrar += Constantes.VALOR_DIA_MOTO * tiempo[0];
			if (moto.getCilindraje() >= Constantes.CILINDRAJE_MAX_MOTO) {
				valorACobrar += Constantes.VALOR_ADICIONAL_CILINDRAJE_MOTO;
			}
		}

		return valorACobrar;

	}

	public int[] calcularTiempo(LocalDateTime fechaEntrada, LocalDateTime fechaSalida) {
		double tiempohoras =0;
		double tiempominutos = Duration.between(fechaEntrada, fechaSalida).toMinutes();
		
		tiempohoras = Math.ceil(tiempominutos/60);
		
		if (tiempohoras == 0)
			tiempohoras++;

		double dias = (tiempohoras / Constantes.LIMITE_MAX_HORAS_DIA);

		int totalDias = (int) dias;

		int totalHoras = (int) Math.ceil ((dias - totalDias) * Constantes.LIMITE_MAX_HORAS_DIA);

		if (totalHoras >= Constantes.LIMITE_MIN_HORAS_DIA ) {
			totalDias++;
			totalHoras = 0;
		}
		int[] tiempo = { totalDias, totalHoras };
		return tiempo;
	}

}
