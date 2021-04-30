
// Das Binding auf die Felder der Klasse abzielen
@XmlAccessorType(XmlAccessType.FIELD)
//@XmlJavaTypeAdapter(LocalDateAdapter.class)
@XmlJavaTypeAdapters({ @XmlJavaTypeAdapter(LocalDateAdapter.class) })

package xml.model;

import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapters;
import xml.adapters.LocalDateAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;