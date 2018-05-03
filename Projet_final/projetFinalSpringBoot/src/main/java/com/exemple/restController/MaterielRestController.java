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

import com.exemple.model.JsonViews;
import com.exemple.model.Materiel;
import com.exemple.repository.MaterielRepository;
import com.fasterxml.jackson.annotation.JsonView;

	@RestController
	@RequestMapping("/rest/materiel")
	@CrossOrigin(origins={"http://localhost:4200"})
	public class MaterielRestController {

		@Autowired
		private MaterielRepository materielRepository;
		
		@JsonView(JsonViews.Common.class) // permet de sp�cifier que l'on veut recup JSON sp�cifique, la vue
		@RequestMapping(path={"","/"}, method=RequestMethod.GET)	//pour avoir une adresse commune
		public ResponseEntity<List<Materiel>> findAll(){
			
			return new ResponseEntity<List<Materiel>>(materielRepository.findAll(), HttpStatus.OK);
			
		}
		
		@RequestMapping(value="/{id}", method=RequestMethod.GET)
		@JsonView(JsonViews.Common.class)
		public ResponseEntity<Materiel> findByKey(@PathVariable(name="id") Long id){
			Optional<Materiel> opt=materielRepository.findById(id);
			if(opt.isPresent()){
				return new ResponseEntity<Materiel>(opt.get(), HttpStatus.OK);
			}else{
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
		}
		
		@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
		public ResponseEntity<Void> delete (@PathVariable(name="id") Long id){
			Optional<Materiel> opt=materielRepository.findById(id);
			if(opt.isPresent()){
				materielRepository.deleteById(id);
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}else{
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
		
		@RequestMapping(path={"","/"}, method=RequestMethod.POST)	//necessaire de mettre post pour acceder au body, get ne marche pas
		public ResponseEntity<Void> create(@RequestBody Materiel materiel, BindingResult br, UriComponentsBuilder ucb){	//requestbody permet de dire, je cree/instancie adherent je vais recup info dans json et avec methode affectation a l'adherent
			if (materiel.getCode()!=null){
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
				
			}else{
				materielRepository.save(materiel);
			}
			HttpHeaders header=new HttpHeaders();
			header.setLocation(ucb.path("/rest/materiel/{id}").buildAndExpand(materiel.getCode()).toUri()); //buildand expand est la pour indiquer l'id, peut avoir plusieur param a mettre dans l'ordre
			return new ResponseEntity<>(header,HttpStatus.OK);
		}
		
		@RequestMapping(path={"","/"}, method=RequestMethod.PUT)
		public ResponseEntity<Materiel>update(@RequestBody Materiel materiel){	//on prefere renvoyer adherent car on peut refaire des changements directement derriere
			
			Optional<Materiel> opt=materielRepository.findById(materiel.getCode());
			if(opt.isPresent()){
				Materiel materielEnBase=opt.get();
				if (materiel.getCout()!=null){
					materielEnBase.setCout(materiel.getCout());
				}
				if (materiel.getDisponible()!=null){
					materielEnBase.setDisponible(materiel.getDisponible());
				}
				materielRepository.save(materielEnBase);
				return new ResponseEntity<Materiel>(materielEnBase, HttpStatus.OK);
			}else{
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
				
			}
		}
	}


