package mitarbeiter.repository.xml;

import java.util.ArrayList;
import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlElementWrapper;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlSeeAlso;
import mitarbeiter.repository.Experte;
import mitarbeiter.repository.Manager;
import mitarbeiter.repository.Mitarbeiter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "mitarbeiterRepository")
@XmlSeeAlso({ Manager.class, Experte.class })
public class MitarbeiterCollection {

	@XmlElementWrapper(name = "mitarbeiters")
	@XmlElement(name = "mitarbeiter")
	private List<Mitarbeiter> mitarbeiter = new ArrayList<>();

	public List<Mitarbeiter> getMitarbeiter() {
		return mitarbeiter;
	}

	private int nextMitarbeiterId = 1;

	public int getNextMitarbeiterId() {
		return nextMitarbeiterId;
	}

	public int incrementNextMitarbeiterId() {
		return nextMitarbeiterId;
	}

	public void setNextNummer(int nextNummer) {
		this.nextMitarbeiterId = nextNummer;
	}

}
