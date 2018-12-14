package co.com.ceiba.estacionamiento.ceibaestacionamiento.dominio.constantes;

public class Constantes {

	private Constantes() {

	}

	public static final int CAPACIDAD_CARRO = 20;
	public static final int CAPACIDAD_MOTO = 10;

	public static final double VALOR_HORA_CARRO = 1000;
	public static final double VALOR_HORA_MOTO = 500;
	public static final double VALOR_DIA_CARRO = 8000;
	public static final double VALOR_DIA_MOTO = 4000;
	public static final double VALOR_ADICIONAL_CILINDRAJE_MOTO = 2000;

	public static final double CILINDRAJE_MAX_MOTO = 500;

	public static final int LIMITE_MIN_HORAS_DIA = 9;
	public static final double LIMITE_MAX_HORAS_DIA = 24;

	public static final String MENSAJE_RESTRICCION_DIAS_DOMINGO_LUNES = "El vehiculo no esta autorizado a ingresar";
	public static final String MENSAJE_CUPO_CARRO_LLENO = "No hay cupo disponible para carro";
	public static final String MENSAJE_CUPO_MOTO_LLENO = "No hay cupo disponible para carro";
	public static final String MENSAJE_TIPO_VEHICULO_NO_ACEPTADO = "Este tipo de vehiculo no esta permitido en el estacionamiento";

	public static final String TIPO_VEHICULO_CARRO = "CARRO";
	public static final String TIPO_VEHICULO_MOTO = "MOTO";
	public static final String ESTADO_ACTIVO = "A";
	public static final String ESTADO_INACTIVO = "I";
	public static final String LETRA_INICIAL_PLACA="A";
	
	//CONSTANTES PRUEBAS
	public static final String PLACA_VEHICULO_CARRO = "CCX568";
	public static final String PLACA_VEHICULO_CARRO_INICIO_CON_RESTRI = "CCX568";
	public static final String TIPO_VEHICULO_ERRADO = "AVION";
	public static final int CAPACIDAD_LIBRE_CARRO = 10;
	public static final int CAPACIDAD_LIBRE_MOTO = 6;

}