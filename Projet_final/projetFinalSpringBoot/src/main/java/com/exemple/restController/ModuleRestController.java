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
import com.exemple.model.Module;
import com.exemple.repository.ModuleRepository;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/module")
@CrossOrigin(origins = "*")
public class ModuleRestController {
	@Autowired
	private ModuleRepository moduleRepository;

	@JsonView(JsonViews.Common.class)
	@RequestMapping(path = { "", "/" }, method = RequestMethod.GET)
	public ResponseEntity<List<Module>> findAll() {
		return new ResponseEntity<List<Module>>(moduleRepository.findAll(), HttpStatus.OK);
	}
}