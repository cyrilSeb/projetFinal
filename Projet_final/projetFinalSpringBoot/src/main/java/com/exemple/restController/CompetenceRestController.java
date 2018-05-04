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
import com.exemple.model.FormateurMatierePK;
import com.exemple.model.JsonViews;
import com.exemple.model.Salle;
import com.exemple.repository.CompetenceRepository;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/competence")
@CrossOrigin(origins = "*")
public class CompetenceRestController {

	@Autowired
	private CompetenceRepository competenceRepository;

	@JsonView(JsonViews.Common.class)
	@RequestMapping(path = { "", "/" }, method = RequestMethod.GET)
	public ResponseEntity<List<Competence>> findAll() {
		return new ResponseEntity<List<Competence>>(competenceRepository.findAll(), HttpStatus.OK);
	}

	@JsonView(JsonViews.Competence.class)
	@RequestMapping(value = "/infos", method = RequestMethod.GET)
	public ResponseEntity<List<Competence>> findAllWithLinks() {
		return new ResponseEntity<List<Competence>>(competenceRepository.findAll(), HttpStatus.OK);
	}

	@JsonView(JsonViews.Common.class)
	@RequestMapping(value = "/{key.formateur.id}/{key.matiere.id}", method = RequestMethod.GET)
	public ResponseEntity<Competence> findById(@PathVariable(name = "key") FormateurMatierePK clef) {
		Optional<Competence> opt = competenceRepository.findById(clef);
		if (opt.isPresent()) {
			return new ResponseEntity<Competence>(opt.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(path = { "", "/", "/infos", "/infos/" }, method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody Competence competence, BindingResult rs, UriComponentsBuilder ucb) {
		if (competence.getKey() == null) {
			return new ResponseEntity<Void>(HttpStatus.FAILED_DEPENDENCY);
		}
		competenceRepository.save(competence);
		HttpHeaders header = new HttpHeaders();
		header.setLocation(ucb.path("/cursus/{id}").buildAndExpand(competence.getKey()).toUri());
		return new ResponseEntity<>(header, HttpStatus.OK);
	}

	@RequestMapping(path = { "", "/", "/infos", "/infos/" }, method = RequestMethod.PUT)
	public ResponseEntity<Competence> update(@RequestBody Competence competence) {
		Optional<Competence> opt = competenceRepository.findById(competence.getKey());
		if (opt.isPresent()) {
			if (competence.getKey() != null) {

			}
			Competence cpt = opt.get();
			competenceRepository.save(cpt);
			return new ResponseEntity<Competence>(cpt, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable(name = "id") FormateurMatierePK numero) {
		Optional<Competence> opt = competenceRepository.findById(numero);
		if (opt.isPresent()) {
			competenceRepository.deleteById(numero);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
