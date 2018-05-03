package com.exemple.restController;


import java.util.List;
import java.util.Optional;

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

import com.exemple.repository.UserRepository;
import com.fasterxml.jackson.annotation.JsonView;

import model.JsonViews;
import model.User;


@RestController
@RequestMapping("/rest/auth")
@CrossOrigin(origins={"http://localhost:4200"})
public class UserRestController {

	@Autowired
	private UserRepository userRepository;
	
	@JsonView(JsonViews.Common.class) // permet de sp�cifier que l'on veut recup JSON sp�cifique, la vue
	@RequestMapping(path={"","/"}, method=RequestMethod.GET)	//pour avoir une adresse commune
	public ResponseEntity<List<User>> findAll(){
		
		return new ResponseEntity<List<User>>(userRepository.findAll(), HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@JsonView(JsonViews.Common.class)
	public ResponseEntity<User> findByKey(@PathVariable(name="id") Long id){
		Optional<User> opt=userRepository.findById(id);
		if(opt.isPresent()){
			return new ResponseEntity<User>(opt.get(), HttpStatus.OK);
		}else{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete (@PathVariable(name="id") Long id){
		Optional<User> opt=userRepository.findById(id);
		if(opt.isPresent()){
			userRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(path={"","/"}, method=RequestMethod.POST)	//necessaire de mettre post pour acceder au body, get ne marche pas
	public ResponseEntity<Void> create(@RequestBody User user, BindingResult br, UriComponentsBuilder ucb){	//requestbody permet de dire, je cree/instancie adherent je vais recup info dans json et avec methode affectation a l'adherent
		if (user.getId()!=null){
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
			
		}else{
			userRepository.save(user);
		}
		HttpHeaders header=new HttpHeaders();
		header.setLocation(ucb.path("/rest/user/{id}").buildAndExpand(user.getId()).toUri()); //buildand expand est la pour indiquer l'id, peut avoir plusieur param a mettre dans l'ordre
		return new ResponseEntity<>(header,HttpStatus.OK);
	}
	
	@RequestMapping(path={"","/"}, method=RequestMethod.PUT)
	public ResponseEntity<User>update(@RequestBody User user){	//on prefere renvoyer adherent car on peut refaire des changements directement derriere
		
		Optional<User> opt=userRepository.findById(user.getId());
		if(opt.isPresent()){
			User userEnBase=opt.get();
			if (user.getPrenom()!=null){
				userEnBase.setPrenom(user.getPrenom());
			}
			if (user.getNom()!=null){
				userEnBase.setNom(user.getNom());
			}
			if (user.getAdresse()!=null){
				userEnBase.setAdresse(user.getAdresse());
			}
			if (user.getUsername()!=null){
				userEnBase.setUsername(user.getUsername());
			}
			if (user.getPassword()!=null){
				userEnBase.setPassword(user.getPassword());
			}
			if (user.getCoordonnees()!=null){
				userEnBase.setCoordonnees(user.getCoordonnees());
			}
			userRepository.save(userEnBase);
			return new ResponseEntity<User>(userEnBase, HttpStatus.OK);
		}else{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
		}
	}
}
