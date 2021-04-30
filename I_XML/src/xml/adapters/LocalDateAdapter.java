package xml.adapters;

import java.time.LocalDate;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {

	@Override
	public LocalDate unmarshal(String v) throws Exception {
		// Aus der Zeichenfolge ein LocalDate-Objekt erzeugen (parsen)
		return v != null && !v.isEmpty() ? LocalDate.parse(v) : null;
	}

	@Override
	public String marshal(LocalDate v) throws Exception {
		// Das LocalDate-Objekt in eine Zeichenfolge umwandeln
		return v != null ? v.toString() : null;
	}

}
