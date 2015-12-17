package be.ipl.projet_ejb.daoimpl;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import be.ipl.projet_ejb.domaine.De;
import be.ipl.projet_ejb.domaine.Face;

@Stateless
@LocalBean
public class DeDaoImpl extends DaoImpl<Integer, De> {

	public DeDaoImpl(){
		super(De.class);
	}
	
	public DeDaoImpl(Class<De> entityClass) {
		super(entityClass);
	}
	
	public Face getFace (De d,int num){
		return d.getFace().get(num);
	}
	
	public void supprimer(De entite) {
		super.supprimer(entite.getId());
	}
	
	public De rechercher(De entite) {
		return super.rechercher(entite.getId());
	}
	
	public De initFaces(De de){
		de = rechercher(de);
		List<Face> faces = de.getFace();
		for (int i = 0; i < 6; i++) {
			Face face = new Face();
			face.getIdentif(i);
			faces.add(face);
		}
		return super.mettreAJour(de);
	}
	
}
