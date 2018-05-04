package com.exemple.restController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.exemple.repository.MatiereRepository;
import com.fasterxml.jackson.annotation.JsonView;

import model.JsonViews;
import model.Matiere;

@RestController
@RequestMapping("/matiere")
@CrossOrigin(origins = "*")
public class MatiereRestController {
	@Autowired
	private MatiereRepository matiereRepository;

	@JsonView(JsonViews.Common.class)
	@RequestMapping(path = { "", "/" }, method = RequestMethod.GET)
	public ResponseEntity<List<Matiere>> findAll() {
		return new ResponseEntity<List<Matiere>>(matiereRepository.findAll(), HttpStatus.OK);
	}
}