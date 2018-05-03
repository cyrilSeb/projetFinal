package com.exemple.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.exemple.model.Cursus;
import com.exemple.model.JsonViews;
import com.exemple.repository.CursusRepository;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/cursus")
@CrossOrigin(origins = "*")
public class CursusRestController {
	@Autowired
	private CursusRepository cursusRepository;

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
}