package test;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import model.Adresse;
import model.Competence;
import model.Coordonnees;
import model.Cursus;
import model.Formateur;
import model.FormateurMatierePK;
import model.Gestionnaire;
import model.Matiere;
import model.Module;
import model.Niveau;
import model.Ordinateur;
import model.Projecteur;
import model.Salle;
import model.Stagiaire;
import model.Technicien;
import repository.CompetenceRepository;
import repository.CursusRepository;
import repository.MaterielRepository;
import repository.MatiereRepository;
import repository.ModuleRepository;
import repository.SalleRepository;
import repository.UserRepository;

public class Test {
	public static void main(String[] args) {
		creerBase();
	}

	public static void creerBase() {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
		CursusRepository cursusRepository = ctx.getBean(CursusRepository.class);
		CompetenceRepository competenceRepository = ctx.getBean(CompetenceRepository.class);
		MaterielRepository materielRepository = ctx.getBean(MaterielRepository.class);
		MatiereRepository matiereRepository = ctx.getBean(MatiereRepository.class);
		ModuleRepository moduleRepository = ctx.getBean(ModuleRepository.class);
		SalleRepository salleRepository = ctx.getBean(SalleRepository.class);
		UserRepository userRepository = ctx.getBean(UserRepository.class);

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
		asus.setCout((double) 15);
		asus.setDD(1024);
		asus.setProcesseur("INTEL CORE i5");
		asus.setRam(8);

		// Projecteur
		Projecteur acer = new Projecteur();
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
		Module modInfo = new Module();

		// Cursus
		Cursus mpi = new Cursus();
		mpi.setNom("Mathematiques, Physique et Informatique");

		// Salle
		Salle salle = new Salle();
		salle.setCapacite(40);
		salle.setNumero("F001");

		// Creation en base
		cursusRepository.save(mpi);
		materielRepository.save(acer);
		materielRepository.save(asus);
		matiereRepository.save(compter);
		matiereRepository.save(info);
		moduleRepository.save(modInfo);
		salleRepository.save(salle);
		userRepository.save(jacky);
		userRepository.save(johanna);
		userRepository.save(richard);
		userRepository.save(olivier);

		// Associations
		FormateurMatierePK cptKey = new FormateurMatierePK();
		cptKey.setFormateur(jacky);
		cptKey.setMatiere(info);

		Competence cpt = new Competence();
		cpt.setKey(cptKey);
		cpt.setNiveau(Niveau.Expert);
		competenceRepository.save(cpt);

		Set<Competence> cpts = new HashSet<Competence>();
		cpts.add(cpt);

		jacky.setCompetences(cpts);
		jacky.setCursus(new HashSet<Cursus>());
		jacky.getCursus().add(mpi);

		Set<Module> mods = new HashSet<Module>();
		mods.add(modInfo);

		jacky.setModules(mods);

		Set<Cursus> setCursus = new HashSet<Cursus>();
		setCursus.add(mpi);

		johanna.setCursus(setCursus);

		richard.setCursus(mpi);
		richard.setOrdinateur(asus);

		asus.setStagiaire(richard);

		acer.setCursus(mpi);

		mpi.setGestionnaire(johanna);
		mpi.setModules(mods);
		mpi.setProjecteur(acer);
		mpi.setReferent(jacky);

		Set<Stagiaire> stags = new HashSet<Stagiaire>();
		stags.add(richard);

		mpi.setStagiaires(stags);

		modInfo.setCursus(mpi);
		modInfo.setFormateur(jacky);
		modInfo.setMatiere(info);

		salle.setCursus(setCursus);

		Set<Matiere> infoPrerequis = new HashSet<Matiere>();
		infoPrerequis.add(compter);
		info.setPrerequis(infoPrerequis);

		// Update
		cursusRepository.save(mpi);
		competenceRepository.save(cpt);
		materielRepository.save(acer);
		materielRepository.save(asus);
		matiereRepository.save(compter);
		matiereRepository.save(info);
		moduleRepository.save(modInfo);
		salleRepository.save(salle);
		userRepository.save(jacky);
		userRepository.save(johanna);
		userRepository.save(richard);
		userRepository.save(olivier);
	}
}
