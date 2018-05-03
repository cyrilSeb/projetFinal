package com.exemple.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.exemple.model.JsonViews;
import com.exemple.model.Salle;
import com.exemple.repository.SalleRepository;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/salle")
@CrossOrigin(origins = "*")
public class SalleRestController {
	@Autowired
	private SalleRepository salleRepository;

	@JsonView(JsonViews.Common.class)
	@RequestMapping(path = { "", "/" }, method = RequestMethod.GET)
	public ResponseEntity<List<Salle>> findAll() {
		return new ResponseEntity<List<Salle>>(salleRepository.findAll(), HttpStatus.OK);
	}
}
