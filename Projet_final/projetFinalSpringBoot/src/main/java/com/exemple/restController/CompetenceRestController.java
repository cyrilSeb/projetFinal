package com.exemple.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.exemple.model.Competence;
import com.exemple.model.JsonViews;
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
	@RequestMapping(value="/infos", method = RequestMethod.GET)
	public ResponseEntity<List<Competence>> findAllWithLinks() {
		return new ResponseEntity<List<Competence>>(competenceRepository.findAll(), HttpStatus.OK);
	}
}
