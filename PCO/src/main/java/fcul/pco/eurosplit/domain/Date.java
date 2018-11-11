package fcul.pco.eurosplit.domain;

import java.time.LocalDateTime;


public class Date {
	private int year;
	private int mes;
	private int dia;
	private int horas;
	private int minutos;
	
	/*
	 * Cria uma instancia de Date para usar como 
	 * Data.
	 * @param y. Eh um ano > 0 e inteiro
	 * @param m. Eh um mes [1-12] e inteiro.
	 * @param d. Eh um dia [1-31] e inteiro.
	 * @param min. Minutos [0-60] e inteiro.
	 * Requires: Respeitar os parametros acima referidos.
	 */
	public Date(int y, int m,int d, int h, int min) {
		this.year=m;
		this.mes=m;
		this.dia=d;
		this.horas=h;
		this.minutos=min;
	}
	/*
	 * Eh criado um objecto com o valor deste formato:
	 * "2018-11-10T12:02:35.876" para saber o valor atual da 
	 * data ao qual foi criado.
	 * Ensures: Devolve em um objecto Date. 
	 * Date(int y, int m,int d, int h, int min)
	 */
	public static Date now() {
		LocalDateTime DateRightNow = LocalDateTime.now();
		int getYear = DateRightNow.getYear();
	    int getMonth = DateRightNow.getMonthValue();
	    int getDay = DateRightNow.getDayOfMonth();
	    int getHour = DateRightNow.getHour();
	    int getMin = DateRightNow.getMinute();
	    Date dt = new Date(getYear,getMonth,getDay,getHour,getMin);
		return dt;
	}
	
	/*
	 * Converte uma Data em String da forma:
	 * "year-mes-dias-horas-minutos"
	 */
	public String toString() {
		return year + "-" + mes + "-" + dia + "-" + horas + "-" + minutos;
	}
	
	/*
	 * Recebe uma String s que eh uma Data
	 * com o formato igual ao metodo toString()
	 * 
	 * requires: s tem de ser uma String em formato
	 * "year-mes-horas-minutos"
	 * Ensures: Devolve um objecto em forma de data
	 */
	public Date fromString(String s) {
		
		int year = Integer.parseInt(s.split("-")[0]);
		int month = Integer.parseInt(s.split("-")[1]);
		int day = Integer.parseInt(s.split("-")[2]);
		int hours = Integer.parseInt(s.split("-")[3]);
		int minuts = Integer.parseInt(s.split("-")[4]);
		
		Date dt = new Date(year,month,day,hours,minuts);
		return dt;
	}
	
}
