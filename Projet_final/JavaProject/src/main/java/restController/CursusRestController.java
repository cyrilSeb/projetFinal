package restController;

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

import com.fasterxml.jackson.annotation.JsonView;

import model.Cursus;
import model.Formateur;
import model.Gestionnaire;
import model.JsonViews;
import model.Materiel;
import model.Module;
import model.Projecteur;
import model.Salle;
import model.Stagiaire;
import model.User;
import repository.CursusRepository;
import repository.MaterielRepository;
import repository.ModuleRepository;
import repository.SalleRepository;
import repository.UserRepository;

@RestController
@RequestMapping("/cursus")
@CrossOrigin(origins = "*")
public class CursusRestController {
	@Autowired
	private CursusRepository cursusRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModuleRepository moduleRepository;

	@Autowired
	private MaterielRepository materielRepository;

	@Autowired
	private SalleRepository salleRepository;

	@JsonView(JsonViews.Common.class)
	@RequestMapping(path = { "", "/" }, method = RequestMethod.GET)
	public ResponseEntity<List<Cursus>> findAll() {
		return new ResponseEntity<List<Cursus>>(cursusRepository.findAll(), HttpStatus.OK);
	}

	@JsonView(JsonViews.Cursus.class)
	@RequestMapping(value = "/infos", method = RequestMethod.GET)
	public ResponseEntity<List<Cursus>> findAllWithLinks() {
		return new ResponseEntity<List<Cursus>>(cursusRepository.findAll(), HttpStatus.OK);
	}

	@RequestMapping(path = { "", "/", "/infos", "/infos/" }, method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody Cursus cursus, BindingResult rs, UriComponentsBuilder ucb) {
		ResponseEntity<Void> check;
		if (cursus.getId() != null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			check = cursusSetGest(cursus);
			if (check != null) {
				return check;
			}
			check = cursusSetModule(cursus);
			if (check != null) {
				return check;
			}
			check = cursusSetProjo(cursus);
			if (check != null) {
				return check;
			}
			check = cursusSetRef(cursus);
			if (check != null) {
				return check;
			}
			check = cursusSetSalle(cursus);
			if (check != null) {
				return check;
			}
			check = cursusSetStag(cursus);
			if (check != null) {
				return check;
			}
			cursusRepository.save(cursus);
			HttpHeaders header = new HttpHeaders();
			header.setLocation(ucb.path("/cursus/{id}").buildAndExpand(cursus.getId()).toUri());
			return new ResponseEntity<>(header, HttpStatus.OK);
		}
	}

	@RequestMapping(path = { "", "/", "/infos", "/infos/" }, method = RequestMethod.PUT)
	public ResponseEntity<Cursus> update(@RequestBody Cursus cursus) {
		Optional<Cursus> optCrs = cursusRepository.findById(cursus.getId());
		if (optCrs.isPresent()) {
			Cursus crs = optCrs.get();
			if (cursus.getNom() != null) {
				crs.setNom(cursus.getNom());
			}
			if (cursus.getDates() != null) {
				crs.setDates(cursus.getDates());
			}
			if (cursus.getGestionnaire() != null) {
				if (cursus.getGestionnaire().getId() != null) {
					Optional<User> optUser = userRepository.findById(cursus.getGestionnaire().getId());
					if (optUser.isPresent()) {
						if (optUser.get() instanceof Gestionnaire) {
							crs.setGestionnaire((Gestionnaire) optUser.get());
						} else {
							return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
						}
					} else {
						return new ResponseEntity<>(HttpStatus.NO_CONTENT);
					}
				}
			}
			if (cursus.getReferent() != null) {
				if (cursus.getReferent().getId() != null) {
					Optional<User> optUser = userRepository.findById(cursus.getReferent().getId());
					if (optUser.isPresent()) {
						if (optUser.get() instanceof Formateur) {
							crs.setReferent((Formateur) optUser.get());
						} else {
							return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
						}
					} else {
						return new ResponseEntity<>(HttpStatus.NO_CONTENT);
					}
				}
			}
			if (cursus.getModules() != null) {
				Set<Module> setMods = cursus.getModules();
				cursus.getModules().clear();
				while (setMods.iterator().hasNext()) {
					Module mod = setMods.iterator().next();
					if (mod.getId() != null) {
						Optional<Module> optMod = moduleRepository.findById(mod.getId());
						if (optMod.isPresent()) {
							cursus.getModules().add(optMod.get());
						} else {
							return new ResponseEntity<>(HttpStatus.NO_CONTENT);
						}
					}
				}
			}
			if (cursus.getProjecteur() != null) {
				if (cursus.getProjecteur().getCode() != null) {
					Optional<Materiel> optMat = materielRepository.findById(cursus.getProjecteur().getCode());
					if (optMat.isPresent()) {
						if (optMat.get() instanceof Projecteur) {
							cursus.setProjecteur((Projecteur) optMat.get());
						} else {
							return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
						}
					} else {
						return new ResponseEntity<>(HttpStatus.NO_CONTENT);
					}
				}
			}
			if (cursus.getStagiaires() != null) {
				Set<Stagiaire> setStags = cursus.getStagiaires();
				cursus.getStagiaires().clear();
				while (setStags.iterator().hasNext()) {
					Stagiaire stag = setStags.iterator().next();
					if (stag.getId() != null) {
						Optional<User> optUser = userRepository.findById(stag.getId());
						if (optUser.isPresent()) {
							if (optUser.get() instanceof Stagiaire) {
								cursus.getStagiaires().add((Stagiaire) optUser.get());
							} else {
								return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
							}
						} else {
							return new ResponseEntity<>(HttpStatus.NO_CONTENT);
						}
					}
				}
			}
			if (cursus.getSalle() != null) {
				if (cursus.getSalle().getId() != null) {
					Optional<Salle> optSalle = salleRepository.findById(cursus.getSalle().getId());
					if (optSalle.isPresent()) {
						crs.setSalle(optSalle.get());
					} else {
						return new ResponseEntity<>(HttpStatus.NO_CONTENT);
					}
				}
			}
			cursusRepository.save(crs);
			return new ResponseEntity<Cursus>(crs, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable(name = "id") Long numero) {
		Optional<Cursus> optCrs = cursusRepository.findById(numero);
		if (optCrs.isPresent()) {
			Cursus cursus = optCrs.get();
			if (cursus.getGestionnaire() != null) {
				Optional<User> optUser = userRepository.findById(cursus.getGestionnaire().getId());
				Gestionnaire gest = (Gestionnaire) optUser.get();
				gest.getCursus().remove(cursus);
				userRepository.save(gest);
				cursus.setGestionnaire(null);
			}
			if (cursus.getModules() != null) {
				while (cursus.getModules().iterator().hasNext()) {
					Module mod = cursus.getModules().iterator().next();
					Optional<Module> optMod = moduleRepository.findById(mod.getId());
					mod = optMod.get();
					mod.setCursus(null);
					moduleRepository.save(mod);
				}
				cursus.setModules(null);
			}
			if (cursus.getProjecteur() != null) {
				Optional<Materiel> optMat = materielRepository.findById(cursus.getProjecteur().getCode());
				Projecteur projo = (Projecteur) optMat.get();
				projo.setCursus(null);
				materielRepository.save(projo);
				cursus.setProjecteur(null);
			}
			if (cursus.getReferent() != null) {
				Optional<User> optUser = userRepository.findById(cursus.getReferent().getId());
				Formateur form = (Formateur) optUser.get();
				form.setCursus(null);
				userRepository.save(form);
				cursus.setReferent(null);
			}
			if (cursus.getSalle() != null) {
				Optional<Salle> optSalle = salleRepository.findById(cursus.getSalle().getId());
				Salle salle = optSalle.get();
				salle.getCursus().remove(cursus);
				salleRepository.save(salle);
				cursus.setSalle(null);
			}
			if (cursus.getStagiaires() != null) {
				while (cursus.getStagiaires().iterator().hasNext()) {
					Stagiaire stag = cursus.getStagiaires().iterator().next();
					Optional<User> optUser = userRepository.findById(stag.getId());
					stag = (Stagiaire) optUser.get();
					stag.setCursus(null);
					userRepository.save(stag);
				}
				cursus.setModules(null);
			}
			cursusRepository.deleteById(numero);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	private ResponseEntity<Void> cursusSetGest(Cursus cursus) {
		if (cursus.getGestionnaire() != null) {
			if (cursus.getGestionnaire().getId() != null) {
				Optional<User> opt = userRepository.findById(cursus.getGestionnaire().getId());
				if (opt.isPresent()) {
					if (opt.get() instanceof Gestionnaire) {
						cursus.setGestionnaire((Gestionnaire) opt.get());
					} else {
						return new ResponseEntity<Void>(HttpStatus.METHOD_NOT_ALLOWED);
					}
				} else {
					return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
				}
			} else {
				cursus.setGestionnaire(null);
			}
		}
		return null;
	}

	private ResponseEntity<Void> cursusSetModule(Cursus cursus) {
		if (cursus.getModules() != null) {
			Set<Module> setModules = cursus.getModules();
			cursus.getModules().clear();
			while (setModules.iterator().hasNext()) {
				Module module = setModules.iterator().next();
				if (module.getId() != null) {
					Optional<Module> opt = moduleRepository.findById(module.getId());
					if (opt.isPresent()) {
						cursus.getModules().add(opt.get());
					} else {
						return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
					}
				}
			}
		}
		return null;
	}

	private ResponseEntity<Void> cursusSetProjo(Cursus cursus) {
		if (cursus.getProjecteur() != null) {
			if (cursus.getProjecteur().getCode() != null) {
				Optional<Materiel> opt = materielRepository.findById(cursus.getProjecteur().getCode());
				if (opt.isPresent()) {
					if (opt.get() instanceof Projecteur) {
						cursus.setProjecteur((Projecteur) opt.get());
					} else {
						return new ResponseEntity<Void>(HttpStatus.METHOD_NOT_ALLOWED);
					}
				} else {
					return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
				}
			} else {
				cursus.setProjecteur(null);
			}
		}
		return null;
	}

	private ResponseEntity<Void> cursusSetRef(Cursus cursus) {
		if (cursus.getReferent() != null) {
			if (cursus.getReferent().getId() != null) {
				Optional<User> opt = userRepository.findById(cursus.getReferent().getId());
				if (opt.isPresent()) {
					if (opt.get() instanceof Formateur) {
						cursus.setReferent((Formateur) opt.get());
					} else {
						return new ResponseEntity<Void>(HttpStatus.METHOD_NOT_ALLOWED);
					}
				} else {
					return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
				}
			} else {
				cursus.setReferent(null);
			}
		}
		return null;
	}

	private ResponseEntity<Void> cursusSetSalle(Cursus cursus) {
		if (cursus.getSalle() != null) {
			if (cursus.getSalle().getId() != null) {
				Optional<Salle> opt = salleRepository.findById(cursus.getSalle().getId());
				if (opt.isPresent()) {
					cursus.setSalle(opt.get());
				} else {
					return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
				}
			} else {
				cursus.setSalle(null);
			}
		}
		return null;
	}

	private ResponseEntity<Void> cursusSetStag(Cursus cursus) {
		if (cursus.getStagiaires() != null) {
			Set<Stagiaire> setStags = cursus.getStagiaires();
			cursus.getStagiaires().clear();
			while (setStags.iterator().hasNext()) {
				Stagiaire stag = setStags.iterator().next();
				if (stag.getId() != null) {
					Optional<User> opt = userRepository.findById(stag.getId());
					if (opt.isPresent()) {
						if (opt.get() instanceof Stagiaire) {
							cursus.getStagiaires().add((Stagiaire) opt.get());
						} else {
							return new ResponseEntity<Void>(HttpStatus.METHOD_NOT_ALLOWED);
						}
					} else {
						return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
					}
				}
			}
		}
		return null;
	}
}