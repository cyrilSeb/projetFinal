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
import com.exemple.model.Ordinateur;
import com.exemple.model.Projecteur;
import com.exemple.model.Salle;
import com.exemple.model.Stagiaire;
import com.exemple.model.User;
import com.exemple.repository.CursusRepository;
import com.exemple.repository.MaterielRepository;
import com.exemple.repository.UserRepository;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/materiel")
@CrossOrigin(origins = "*")
public class MaterielRestController {
	@Autowired
	private MaterielRepository materielRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CursusRepository cursusRepository;

	@JsonView(JsonViews.Common.class)
	@RequestMapping(path = { "", "/" }, method = RequestMethod.GET)
	public ResponseEntity<List<Materiel>> findAll() {
		return new ResponseEntity<List<Materiel>>(materielRepository.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/ordinateur", method = RequestMethod.POST)
	public ResponseEntity<Void> createOrdi(@RequestBody Ordinateur ordi, BindingResult rs, UriComponentsBuilder ucb) {
		if (ordi.getCode() != null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			if (ordi.getStagiaire() != null) {
				if (ordi.getStagiaire().getId() != null) {
					Optional<User> opt = userRepository.findById(ordi.getStagiaire().getId());
					if (opt.isPresent()) {
						if (opt.get() instanceof Stagiaire) {
							ordi.setStagiaire((Stagiaire) opt.get());
						} else {
							return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
						}
					} else {
						return new ResponseEntity<>(HttpStatus.NO_CONTENT);
					}
				} else {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
			}
			return create(ordi, rs, ucb);
		}
	}

	@RequestMapping(value = "/projecteur", method = RequestMethod.POST)
	public ResponseEntity<Void> createOrdi(@RequestBody Projecteur projo, BindingResult rs, UriComponentsBuilder ucb) {
		if (projo.getCode() != null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			if (projo.getCursus() != null) {
				if (projo.getCursus().getId() != null) {
					Optional<Cursus> opt = cursusRepository.findById(projo.getCursus().getId());
					if (opt.isPresent()) {
						projo.setCursus(opt.get());
					} else {
						return new ResponseEntity<>(HttpStatus.NO_CONTENT);
					}
				} else {
					return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				}
			}
		}
		return create(projo, rs, ucb);
	}

	@RequestMapping(value = "/ordinateur", method = RequestMethod.PUT)
	public ResponseEntity<Materiel> updateOrdi(@RequestBody Ordinateur ordi) {
		Optional<Materiel> opt = materielRepository.findById(ordi.getCode());
		if (opt.isPresent()) {
			if (opt.get() instanceof Ordinateur) {
				Ordinateur matos = (Ordinateur) opt.get();
				if (ordi.getDisponible() != null) {
					matos.setDisponible(ordi.getDisponible());
				}
				return update(ordi, matos);
			} else {
				return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	private ResponseEntity<Materiel> update(Materiel matosNew, Materiel matosOld) {
		materielRepository.save(matosOld);
		return new ResponseEntity<Materiel>(matosOld, HttpStatus.OK);
	}

	private ResponseEntity<Void> create(Materiel materiel, BindingResult rs, UriComponentsBuilder ucb) {
		materielRepository.save(materiel);
		HttpHeaders header = new HttpHeaders();
		header.setLocation(ucb.path("/materiel/{id}").buildAndExpand(materiel.getCode()).toUri());
		return new ResponseEntity<>(header, HttpStatus.OK);
	}
}
