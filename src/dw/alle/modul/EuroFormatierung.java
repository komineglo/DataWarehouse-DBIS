package dw.alle.modul;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.JFormattedTextField;

/**
 * Hilfsklasse zum Formatieren von Feldern mit Euro-Angaben.
 * @author janssela
 *
 */
public class EuroFormatierung {
	private static final DecimalFormat formDec = new DecimalFormat(",##0.00");
	private static final NumberFormat formCur = NumberFormat
			.getCurrencyInstance();

	/**
	 * Liefert den formatierten Inhalt zurück.
	 */
	public static String getDecimalFormat(double value) {
		return formDec.format(value);
	}

	/**
	 * Liefert den formatierten Inhalt zurück.
	 */
	public static String getCurrencyFormat(double value) {
		return formCur.format(value);
	}

	/**
	 * Liefert den formatierten Inhalt als double-Wert zurück.
	 */
	private double getDoubleValue(JFormattedTextField field) throws Exception {
		double value = 0.0;
		if (field.getValue() instanceof Integer)
			value = (Integer) field.getValue();
		else if (field.getValue() instanceof Double)
			value = (Double) field.getValue();
		else if (field.getValue() instanceof Float)
			value = (Float) field.getValue();
		else if (field.getValue() instanceof Long)
			value = (Long) field.getValue();
		else {
			try {
				value = Double.parseDouble((String) field.getValue());
			} catch (Exception e) {
				e.printStackTrace();
				throw new Exception(
						"Die Methode getDoubleValue() wird falsch verwendet!");
			}
		}

		return value;
	}

	/**
	 * Liefert den formatierten Inhalt als double-Wert zurück.
	 */
	public static double getDoubleValue(String field) {
		// Mögliche formatierte Zahlen: 00,10 9.000,00 90,00
		field = field.replace(".", "");
		field = field.replace(",", ".");
		field = field.replace(" €", "").trim();

		double value = 0;
		try {
			value = Double.parseDouble(field);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return value;
	}

	/**
	 * Liefert den formatierten Inhalt als float-Wert zurück.
	 */
	public static float getFloatValue(String field) {
		// Mögliche formatierte Zahlen: 00,10 9.000,00 90,00
		field = field.replace(".", "");
		field = field.replace(",", ".");
		field = field.replace(" €", "").trim();

		float value = 0;
		try {
			value = Float.parseFloat(field);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return value;
	}

	/**
	 * Rundet eine Double-Zahl auf die letzten zwei Nachkommastellen
	 */
	public static double roundPreis(double d) {
		return Math.rint(d * 100) / 100.;
	}
}
