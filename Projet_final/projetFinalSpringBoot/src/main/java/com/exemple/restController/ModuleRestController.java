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

import com.exemple.model.Cursus;
import com.exemple.model.Formateur;
import com.exemple.model.Gestionnaire;
import com.exemple.model.JsonViews;
import com.exemple.model.Materiel;
import com.exemple.model.Matiere;
import com.exemple.model.Module;
import com.exemple.model.Projecteur;
import com.exemple.model.Salle;
import com.exemple.model.Stagiaire;
import com.exemple.model.User;
import com.exemple.repository.CursusRepository;
import com.exemple.repository.MatiereRepository;
import com.exemple.repository.ModuleRepository;
import com.exemple.repository.UserRepository;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/module")
@CrossOrigin(origins = "*")
public class ModuleRestController {
	@Autowired
	private ModuleRepository moduleRepository;

	@Autowired
	private CursusRepository cursusRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MatiereRepository matiereRepository;

	@JsonView(JsonViews.Common.class)
	@RequestMapping(path = { "", "/" }, method = RequestMethod.GET)
	public ResponseEntity<List<Module>> findAll() {
		return new ResponseEntity<List<Module>>(moduleRepository.findAll(), HttpStatus.OK);
	}

	@JsonView(JsonViews.Module.class)
	@RequestMapping(value = "/infos", method = RequestMethod.GET)
	public ResponseEntity<List<Module>> findAllWithLinks() {
		return new ResponseEntity<List<Module>>(moduleRepository.findAll(), HttpStatus.OK);
	}

	@JsonView(JsonViews.Common.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Module> findById(@PathVariable(name = "id") Long numero) {
		Optional<Module> opt = moduleRepository.findById(numero);
		if (opt.isPresent()) {
			return new ResponseEntity<Module>(opt.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@JsonView(JsonViews.Module.class)
	@RequestMapping(value = "/{id}/infos", method = RequestMethod.GET)
	public ResponseEntity<Module> findByIdWithLinks(@PathVariable(name = "id") Long numero) {
		Optional<Module> opt = moduleRepository.findById(numero);
		if (opt.isPresent()) {
			return new ResponseEntity<Module>(opt.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(path = { "", "/", "/infos", "/infos/" }, method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody Module module, BindingResult rs, UriComponentsBuilder ucb) {
		if (module.getId() != null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			if (module.getCursus() != null) {
				Optional<Cursus> opt = cursusRepository.findById(module.getCursus().getId());
				if (opt.isPresent()) {
					Cursus cursus = opt.get();
					module.setCursus(cursus);
				} else {
					module.setCursus(null);
				}
			}
			if (module.getFormateur() != null) {
				Optional<User> opt = userRepository.findById(module.getFormateur().getId());
				if (opt.isPresent()) {
					if (opt.get() instanceof Formateur) {
						Formateur formateur = (Formateur) opt.get();
						module.setFormateur(formateur);
					} else {
						return new ResponseEntity<Void>(HttpStatus.METHOD_NOT_ALLOWED);
					}
				} else {
					module.setCursus(null);
				}
			}
			if (module.getMatiere() != null) {
				Optional<Matiere> opt = matiereRepository.findById(module.getMatiere().getId());
				if (opt.isPresent()) {
					Matiere mat = opt.get();
					module.setMatiere(mat);
				} else {
					module.setCursus(null);
				}
			}
			moduleRepository.save(module);
			HttpHeaders header = new HttpHeaders();
			header.setLocation(ucb.path("/cursus/{id}").buildAndExpand(module.getId()).toUri());
			return new ResponseEntity<>(header, HttpStatus.OK);
		}
	}

	@RequestMapping(path = { "", "/", "/infos", "/infos/" }, method = RequestMethod.PUT)
	public ResponseEntity<Module> update(@RequestBody Module module) {
		Optional<Module> opt = moduleRepository.findById(module.getId());
		if (opt.isPresent()) {
			Module mod = opt.get();
			if (module.getCursus() != null) {
				mod.setCursus(module.getCursus());
			}
			if (module.getDates() != null) {
				mod.setDates(module.getDates());
			}
			if (module.getFormateur() != null) {
				mod.setFormateur(module.getFormateur());
			}
			if (module.getMatiere() != null) {
				mod.setMatiere(module.getMatiere());
			}
			moduleRepository.save(mod);
			return new ResponseEntity<Module>(mod, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable(name = "id") Long numero) {
		Optional<Module> opt = moduleRepository.findById(numero);
		if (opt.isPresent()) {
			moduleRepository.deleteById(numero);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}