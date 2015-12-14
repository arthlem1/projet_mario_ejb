package be.ipl.projet_ejb.domaine;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.transform.stream.StreamSource;

import be.ipl.projet_ejb.daoimpl.CarteDaoImpl;
import be.ipl.projet_ejb.daoimpl.DeDaoImpl;

//j'ai mis @Startup en commentaire pour que ca ne bug pas toute l'application
//apparement il y a un soucis de path, on demandera au prof demain (laeti)

@Singleton
// @Startup
public class InitDB {

	private CarteDaoImpl carteDao;

	private DeDaoImpl deDao;

	@PostConstruct
	public void go() throws JAXBException, IOException {
		System.out.println("Initialisation de la DB");
		// cr�ation de l�InputStream � adapter selon votre jar.
		InputStream is = new FileInputStream("../standalone/deployments/projet_mario_EAR.ear/wazabi.xml");

		Wazabi wazabi = fromStream(Wazabi.class, is);

		// enregistrement des d�s
		for (int i = 0; i < wazabi.getDe().getNbTotalDes(); i++) {
			De de = new De();
			deDao.enregistrer(de);
		}

		// enregistrement des cartes
		for (Carte carte : wazabi.getCarte()) {
			int nbCartesDeCeType = carte.getNb();
			Carte[] cartes = new Carte[nbCartesDeCeType];
			for (int i = 0; i < nbCartesDeCeType; i++) {
				cartes[i] = (Carte) carte;// .clone() bug
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