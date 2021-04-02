package bitoperatoren;

import java.time.LocalDate;

public class Wetterbits {
	// Konstante für die einzelnen Wetterphänomene
	public final static byte SONNE = 1, WOLKEN = 2, REGEN = 4, SCHNEE = 8, WIND = 16, NEBEL = 32;

	private String stadt;
	private LocalDate datum;
	private byte wetterBits;

	public Wetterbits(String stadt, LocalDate datum, byte wetterBits) {
		super();
		this.stadt = stadt;
		this.datum = datum;
		this.wetterBits = wetterBits;
	}

	public String getStadt() {
		return stadt;
	}

	public LocalDate getDatum() {
		return datum;
	}

	public byte getWetterBits() {
		return wetterBits;
	}

	public boolean enthaeltBits(byte testBits) {
		// Wenn alle Bits aus testBits gesetzt sind -> true zurücklieferen.
		if ((wetterBits & testBits) == testBits) {
			return true;
		} else {
			return false;
		}
	}

	public void bitsHinzu(byte bits) {
		// wetterBits = (byte) (wetterBits | bits);
		// wetterBits = wetterBits | bits;
		// Die Bits in den eigenen wetterBits hinzufügen.
		wetterBits |= bits;
	}

	public void bitsEntfernen(byte bits) {
		// Den Kehrwert der Bits berechnen und mit unseren Wetterbits verknüpfen.
		wetterBits = (byte) (wetterBits & (~bits));
		// wetterBits = (byte) (wetterBits & (~bits));
		wetterBits &= ~bits;
	}

	public String toString() {
		StringBuilder buffer = new StringBuilder(100);
		// 1. Zeile: Wo und Wann
		buffer.append(String.format("Wetter in %s am %s: \n", stadt, datum));
		// 2. Zeile: Die Zahl als Ganzzahl und Binär-String
		buffer.append(String.format("%3d (%8s)\n", wetterBits, Integer.toBinaryString(wetterBits)));

		return buffer.toString();
	}

}
