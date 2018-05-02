package test;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import model.*;
import repository.*;

public class Test {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

		// Formateur
		Formateur jacky = new Formateur();

		Adresse ajc = new Adresse();
		ajc.setNumero(6);
		ajc.setRue("Rue Rougemont");
		ajc.setCodePostal("75009");
		ajc.setVille("Paris");
		ajc.setPays("FRANCE");

		jacky.setAdresse(ajc);

		Coordonnees coordonnees = new Coordonnees();
		coordonnees.setEmail("john.doe@gmail.com");
		coordonnees.setTelephone("+33192638574");

		jacky.setCoordonnees(coordonnees);
		jacky.setNom("MELLOUL");
		jacky.setPrenom("Jacky");

		// Gestionnaire
		Gestionnaire johanna = new Gestionnaire();
		johanna.setAdresse(ajc);
		johanna.setNom("LEBLANC");
		johanna.setPrenom("Johanna");

		// Stagiaire
		Stagiaire richard = new Stagiaire();
		richard.setCoordonnees(coordonnees);
		richard.setNom("RAZAFINDRAKOTO");
		richard.setPrenom("Mirijason");

		// Technicien
		Technicien olivier = new Technicien();
		olivier.setNom("GOZLAN");
		olivier.setPrenom("Olivier");

		// Ordinateur
		Ordinateur asus = new Ordinateur();
		asus.setCode((long) 48);
		asus.setCout((double) 15);
		asus.setDD(1024);
		asus.setProcesseur("INTEL CORE i5");
		asus.setRam(8);

		// Projecteur
		Projecteur acer = new Projecteur();
		acer.setCode((long) 42);
		acer.setCout((double) 10);

		// Matiere 1
		Matiere compter = new Matiere();
		compter.setContenu("Nombres naturels");
		compter.setNbHeure(4);
		compter.setObjectifs("Apprendre à compter jusqu'à 10");
		compter.setTitre("Compter");

		// Matiere 2
		Matiere info = new Matiere();
		info.setContenu("JAVA");
		info.setNbHeure(8);
		info.setObjectifs("Devenir Dieu");
		info.setTitre("JAVA/JEE");

		// Module
		Module ifo = new Module();

		// Associations
		richard.setOrdinateur(asus);
		asus.setStagiaire(richard);

		Set<Matiere> infoPrerequis = new HashSet<Matiere>();
		infoPrerequis.add(compter);
		info.setPrerequis(infoPrerequis);

		CursusRepository cursusRepository = ctx.getBean(CursusRepository.class);
	}
}
