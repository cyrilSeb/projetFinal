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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.exemple.model.Cursus;
import com.exemple.model.Formateur;
import com.exemple.model.Gestionnaire;
import com.exemple.model.JsonViews;
import com.exemple.model.Materiel;
import com.exemple.model.Module;
import com.exemple.model.Projecteur;
import com.exemple.model.Salle;
import com.exemple.model.Stagiaire;
import com.exemple.model.User;
import com.exemple.repository.CursusRepository;
import com.exemple.repository.MaterielRepository;
import com.exemple.repository.ModuleRepository;
import com.exemple.repository.SalleRepository;
import com.exemple.repository.UserRepository;
import com.fasterxml.jackson.annotation.JsonView;

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

	@RequestMapping(path = { "", "/" }, method = RequestMethod.POST)
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