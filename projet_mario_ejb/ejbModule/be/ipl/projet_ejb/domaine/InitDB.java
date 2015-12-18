package be.ipl.projet_ejb.domaine;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.transform.stream.StreamSource;

import be.ipl.projet_ejb.daoimpl.CarteDaoImpl;
import be.ipl.projet_ejb.daoimpl.DeDaoImpl;

@Singleton
@Startup
public class InitDB {

	@EJB
	CarteDaoImpl carteDao;
	@EJB
	DeDaoImpl deDao;
	Wazabi wazabi;

	public Wazabi getWazabi() {
		return wazabi;
	}

	@PostConstruct
	public void go() throws JAXBException, IOException {
		System.out.println("Initialisation de la DB");
		// création de l’InputStream à adapter selon votre jar.
		InputStream is = new FileInputStream(
				"../standalone/deployments/projet_mario_EAR.ear/projet_mario_ejb.jar/META-INF/wazabi.xml");

		wazabi = fromStream(Wazabi.class, is);

		// enregistrement des dés
		for (int i = 0; i < wazabi.getDe().getNbTotalDes(); i++) {
			De de = new De();
			deDao.enregistrer(de);
		}

		// enregistrement des cartes
		for (Carte carte : wazabi.getCarte()) {
			int nbCartesDeCeType = carte.getNb();
			Carte[] cartes = new Carte[nbCartesDeCeType];
			for (int i = 0; i < nbCartesDeCeType; i++) {
				cartes[i] = (Carte) carte.clone();
				carteDao.enregistrer(cartes[i]);
			}
		}
	}

	@SuppressWarnings("unchecked")
	private static <T> T fromStream(Class<T> clazz, InputStream input) throws JAXBException {
		JAXBContext ctx = JAXBContext.newInstance(clazz);
		Object result = ctx.createUnmarshaller().unmarshal(new StreamSource(input), clazz);
		if (result instanceof JAXBElement<?>) {
			result = JAXBIntrospector.getValue(result);
		}
		return (T) result;
	}

}