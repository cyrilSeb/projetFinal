package com.exemple.restController;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.exemple.model.Competence;
import com.exemple.model.Cursus;
import com.exemple.model.Formateur;
import com.exemple.model.Gestionnaire;
import com.exemple.model.JsonViews;
import com.exemple.model.Materiel;
import com.exemple.model.Module;
import com.exemple.model.Ordinateur;
import com.exemple.model.Stagiaire;
import com.exemple.model.Technicien;
import com.exemple.model.User;
import com.exemple.repository.CompetenceRepository;
import com.exemple.repository.CursusRepository;
import com.exemple.repository.MaterielRepository;
import com.exemple.repository.ModuleRepository;
import com.exemple.repository.UserRepository;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserRestController {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CompetenceRepository competenceRepository;

	@Autowired
	private CursusRepository cursusRepository;

	@Autowired
	private ModuleRepository moduleRepository;

	@Autowired
	private MaterielRepository materielRepository;

	@JsonView(JsonViews.Common.class)
	@RequestMapping(path = { "", "/" }, method = RequestMethod.GET)
	public ResponseEntity<List<User>> findAll() {
		return new ResponseEntity<List<User>>(userRepository.findAll(), HttpStatus.OK);
	}

	@JsonView(JsonViews.User.class)
	@RequestMapping(value = "/infos", method = RequestMethod.GET)
	public ResponseEntity<List<User>> findAllWithLinks() {
		return new ResponseEntity<List<User>>(userRepository.findAll(), HttpStatus.OK);
	}

	@JsonView(JsonViews.Common.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> findById(@PathVariable(name = "id") Long numero) {
		Optional<User> opt = userRepository.findById(numero);
		if (opt.isPresent()) {
			return new ResponseEntity<User>(opt.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@JsonView(JsonViews.User.class)
	@RequestMapping(value = "/{id}/infos", method = RequestMethod.GET)
	public ResponseEntity<User> findByIdWithLinks(@PathVariable(name = "id") Long numero) {
		Optional<User> opt = userRepository.findById(numero);
		if (opt.isPresent()) {
			return new ResponseEntity<User>(opt.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@JsonView(JsonViews.Authentification.class)
	@RequestMapping(value = "/authentification/{name}/{mdp}", method = RequestMethod.GET)
	public ResponseEntity<User> findByUsername(@PathVariable(name = "name") String username,
			@PathVariable(name = "mdp") String password) {
		Optional<User> opt = userRepository.findByUsername(username);
		if (opt.isPresent()) {
			User user = opt.get();
			if (user.getPassword().equals(password)) {
				return new ResponseEntity<User>(user, HttpStatus.OK);
			}
		}
		return new ResponseEntity<User>(HttpStatus.FORBIDDEN);
	}

	@RequestMapping(value = "/formateur", method = RequestMethod.POST)
	public ResponseEntity<Void> createFormateur(@RequestBody Formateur formateur, BindingResult rs,
			UriComponentsBuilder ucb) {
		if (formateur.getCompetences() != null) {
			Set<Competence> setCpts = formateur.getCompetences();
			formateur.getCompetences().clear();
			while (setCpts.iterator().hasNext()) {
				Competence cpt = setCpts.iterator().next();
				Optional<Competence> opt = competenceRepository.findById(cpt.getKey());
				if (opt.isPresent()) {
					formateur.getCompetences().add(opt.get());
				} else {
					return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
				}

			}
		}
		if (formateur.getCursus() != null) {
			Set<Cursus> setCursus = formateur.getCursus();
			formateur.getCursus().clear();
			while (setCursus.iterator().hasNext()) {
				Cursus cursus = setCursus.iterator().next();
				if (cursus.getId() != null) {
					Optional<Cursus> opt = cursusRepository.findById(cursus.getId());
					if (opt.isPresent()) {
						formateur.getCursus().add(opt.get());
					} else {
						return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
					}
				}
			}
		}
		if (formateur.getModules() != null) {
			Set<Module> setMods = formateur.getModules();
			formateur.getModules().clear();
			while (setMods.iterator().hasNext()) {
				Module mod = setMods.iterator().next();
				if (mod.getId() != null) {
					Optional<Module> opt = moduleRepository.findById(mod.getId());
					if (opt.isPresent()) {
						formateur.getModules().add(opt.get());
					} else {
						return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
					}
				}
			}
		}
		return create(formateur, rs, ucb);
	}

	@RequestMapping(value = "/gestionnaire", method = RequestMethod.POST)
	public ResponseEntity<Void> createGestionnaire(@RequestBody Gestionnaire gestionnaire, BindingResult rs,
			UriComponentsBuilder ucb) {
		if (gestionnaire.getCursus() != null) {
			Set<Cursus> setCursus = gestionnaire.getCursus();
			gestionnaire.getCursus().clear();
			while (setCursus.iterator().hasNext()) {
				Cursus cursus = setCursus.iterator().next();
				if (cursus.getId() != null) {
					Optional<Cursus> opt = cursusRepository.findById(cursus.getId());
					if (opt.isPresent()) {
						gestionnaire.getCursus().add(opt.get());
					} else {
						return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
					}
				}
			}
		}
		return create(gestionnaire, rs, ucb);
	}

	@RequestMapping(value = "/stagiaire", method = RequestMethod.POST)
	public ResponseEntity<Void> createStagiaire(@RequestBody Stagiaire stagiaire, BindingResult rs,
			UriComponentsBuilder ucb) {
		if (stagiaire.getCursus() != null) {
			if (stagiaire.getCursus().getId() != null) {
				Optional<Cursus> opt = cursusRepository.findById(stagiaire.getCursus().getId());
				if (opt.isPresent()) {
					stagiaire.setCursus(opt.get());
				} else {
					return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
				}
			}
		}
		if (stagiaire.getOrdinateur() != null) {
			if (stagiaire.getOrdinateur().getCode() != null) {
				Optional<Materiel> opt = materielRepository.findById(stagiaire.getOrdinateur().getCode());
				if (opt.isPresent()) {
					if (opt.get() instanceof Ordinateur) {
						stagiaire.setOrdinateur((Ordinateur) opt.get());
					} else {
						return new ResponseEntity<Void>(HttpStatus.METHOD_NOT_ALLOWED);
					}
				} else {
					return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
				}
			}
		}
		return create(stagiaire, rs, ucb);
	}

	@RequestMapping(value = "/technicien", method = RequestMethod.POST)
	public ResponseEntity<Void> createTechnicien(@RequestBody Technicien technicien, BindingResult rs,
			UriComponentsBuilder ucb) {
		return create(technicien, rs, ucb);
	}

	@RequestMapping(value = "/formateur", method = RequestMethod.PUT)
	public ResponseEntity<User> updateFormateur(@RequestBody Formateur formateur) {
		Optional<User> opt = userRepository.findById(formateur.getId());
		if (opt.isPresent()) {
			if (opt.get() instanceof Formateur) {
				Formateur user = (Formateur) opt.get();
				if (user.getCompetences() != null) {
					Set<Competence> setCpts = user.getCompetences();
					user.getCompetences().clear();
					while (setCpts.iterator().hasNext()) {
						Competence cpt = setCpts.iterator().next();
						Optional<Competence> optCpt = competenceRepository.findById(cpt.getKey());
						if (optCpt.isPresent()) {
							user.getCompetences().add(optCpt.get());
						}
					}
				}
				if (user.getCursus() != null) {
					Set<Cursus> setCursus = user.getCursus();
					user.getCursus().clear();
					while (setCursus.iterator().hasNext()) {
						Cursus cursus = setCursus.iterator().next();
						if (cursus.getId() != null) {
							Optional<Cursus> optCrs = cursusRepository.findById(cursus.getId());
							if (optCrs.isPresent()) {
								user.getCursus().add(optCrs.get());
							}
						}
					}
				}
				if (user.getModules() != null) {
					Set<Module> setMods = user.getModules();
					user.getModules().clear();
					while (setMods.iterator().hasNext()) {
						Module mod = setMods.iterator().next();
						if (mod.getId() != null) {
							Optional<Module> optMod = moduleRepository.findById(mod.getId());
							if (optMod.isPresent()) {
								user.getModules().add(optMod.get());
							}
						}
					}
				}
				return update(formateur, user);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(value = "/gestionnaire", method = RequestMethod.PUT)
	public ResponseEntity<User> updateGestionnaire(@RequestBody Gestionnaire gestionnaire) {
		Optional<User> opt = userRepository.findById(gestionnaire.getId());
		if (opt.isPresent()) {
			if (opt.get() instanceof Gestionnaire) {
				Gestionnaire user = (Gestionnaire) opt.get();
				if (user.getCursus() != null) {
					Set<Cursus> setCrs = user.getCursus();
					user.getCursus().clear();
					while (setCrs.iterator().hasNext()) {
						Cursus crs = setCrs.iterator().next();
						if (crs.getId() != null) {
							Optional<Cursus> optCrs = cursusRepository.findById(crs.getId());
							if (optCrs.isPresent()) {
								user.getCursus().add(optCrs.get());
							}
						}
					}
				}
				return update(gestionnaire, user);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(value = "/stagiaire", method = RequestMethod.PUT)
	public ResponseEntity<User> updateStagiaire(@RequestBody Stagiaire stagiaire) {
		Optional<User> opt = userRepository.findById(stagiaire.getId());
		if (opt.isPresent()) {
			if (opt.get() instanceof Stagiaire) {
				Stagiaire user = (Stagiaire) opt.get();
				if (user.getCursus() != null) {
					if (user.getCursus().getId() != null) {
						Optional<Cursus> optCrs = cursusRepository.findById(user.getCursus().getId());
						if (optCrs.isPresent()) {
							user.setCursus(optCrs.get());
						}
					}
				}
				if (user.getOrdinateur() != null) {
					if (user.getOrdinateur().getCode() != null) {
						Optional<Materiel> optMat = materielRepository.findById(user.getOrdinateur().getCode());
						if (optMat.isPresent()) {
							if (optMat.get() instanceof Ordinateur) {
								user.setOrdinateur((Ordinateur) optMat.get());
							}
						}
					}
				}
				return update(stagiaire, user);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(value = "/technicien", method = RequestMethod.PUT)
	public ResponseEntity<User> updateTechnicien(@RequestBody Technicien technicien) {
		Optional<User> opt = userRepository.findById(technicien.getId());
		if (opt.isPresent()) {
			if (opt.get() instanceof Technicien) {
				Technicien user = (Technicien) opt.get();
				return update(technicien, user);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable(name = "id") Long numero) {
		Optional<User> opt = userRepository.findById(numero);
		if (opt.isPresent()) {
			if (opt.get() instanceof Formateur) {
				Formateur user = (Formateur) opt.get();
				if (user.getCompetences() != null) {
					Set<Competence> setCps = user.getCompetences();
					user.getCompetences().clear();
					while (setCps.iterator().hasNext()) {
						Competence cps = setCps.iterator().next();
						Optional<Competence> optCps = competenceRepository.findById(cps.getKey());
						cps = optCps.get();
						competenceRepository.delete(cps);
					}
				}
				if (user.getCursus() != null) {
					Set<Cursus> setCursus = user.getCursus();
					user.getCursus().clear();
					while (setCursus.iterator().hasNext()) {
						Cursus cursus = setCursus.iterator().next();
						Optional<Cursus> optCrs = cursusRepository.findById(cursus.getId());
						cursus = optCrs.get();
						cursus.setReferent(null);
						cursusRepository.save(cursus);
					}
				}
				if (user.getModules() != null) {
					Set<Module> setCps = user.getModules();
					user.getModules().clear();
					while (setCps.iterator().hasNext()) {
						Module cps = setCps.iterator().next();
						Optional<Module> optCps = moduleRepository.findById(cps.getId());
						cps = optCps.get();
						cps.setFormateur(null);
						moduleRepository.save(cps);
					}
				}
			}
			if (opt.get() instanceof Gestionnaire) {
				Gestionnaire user = (Gestionnaire) opt.get();
				if (user.getCursus() != null) {
					Set<Cursus> setCrs = user.getCursus();
					user.getCursus().clear();
					while (setCrs.iterator().hasNext()) {
						Cursus crs = setCrs.iterator().next();
						Optional<Cursus> optCrs = cursusRepository.findById(crs.getId());
						crs = optCrs.get();
						crs.setGestionnaire(null);
						cursusRepository.save(crs);
					}
				}
			}
			if (opt.get() instanceof Stagiaire) {
				Stagiaire user = (Stagiaire) opt.get();
				if (user.getCursus() != null) {
					Optional<Cursus> optCrs = cursusRepository.findById(user.getCursus().getId());
					Cursus crs = optCrs.get();
					crs.getStagiaires().remove(user);
					cursusRepository.save(crs);
				}
				if (user.getOrdinateur() != null) {
					Optional<Materiel> optMat = materielRepository.findById(user.getOrdinateur().getCode());
					Ordinateur mat = (Ordinateur) optMat.get();
					mat.setStagiaire(null);
					materielRepository.save(mat);
				}
			}
			userRepository.deleteById(numero);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	private ResponseEntity<User> update(User userNew, User userOld) {
		if (userNew.getAdresse() != null) {
			userOld.setAdresse(userNew.getAdresse());
		}
		if (userNew.getCoordonnees() != null) {
			userOld.setCoordonnees(userNew.getCoordonnees());
		}
		if (userNew.getNom() != null) {
			userOld.setNom(userNew.getNom());
		}
		if (userNew.getPassword() != null) {
			userOld.setPassword(userNew.getPassword());
		}
		if (userNew.getPrenom() != null) {
			userOld.setPrenom(userNew.getPrenom());
		}
		if (userNew.getRoles() != null) {
			userOld.setRoles(userNew.getRoles());
		}
		if (userNew.getUsername() != null) {
			userOld.setUsername(userNew.getUsername());
		}
		userRepository.save(userOld);
		return new ResponseEntity<User>(userOld, HttpStatus.OK);
	}

	private ResponseEntity<Void> create(User user, BindingResult rs, UriComponentsBuilder ucb) {
		if (user.getId() != null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			userRepository.save(user);
			HttpHeaders header = new HttpHeaders();
			header.setLocation(ucb.path("/user/{id}").buildAndExpand(user.getId()).toUri());
			return new ResponseEntity<>(header, HttpStatus.OK);
		}
	}
}
