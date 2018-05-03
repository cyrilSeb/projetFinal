package restController;

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

import com.fasterxml.jackson.annotation.JsonView;

import model.JsonViews;
import model.Competence;
import repository.CompetenceRepository;

@RestController
@RequestMapping("/rest/competence")
@CrossOrigin(origins={"http://localhost:4200"})
public class CompetenceRestController {

	@Autowired
	private CompetenceRepository competenceRepository;
	
	@JsonView(JsonViews.Common.class) // permet de spécifier que l'on veut recup JSON spécifique, la vue
	@RequestMapping(path={"","/"}, method=RequestMethod.GET)	//pour avoir une adresse commune
	public ResponseEntity<List<Competence>> findAll(){
		
		return new ResponseEntity<List<Competence>>(competenceRepository.findAll(), HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@JsonView(JsonViews.Common.class)
	public ResponseEntity<Competence> findByKey(@PathVariable(name="id") Long id){
		Optional<Competence> opt=competenceRepository.findById(id);
		if(opt.isPresent()){
			return new ResponseEntity<Competence>(opt.get(), HttpStatus.OK);
		}else{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete (@PathVariable(name="id") Long id){
		Optional<Competence> opt=competenceRepository.findById(id);
		if(opt.isPresent()){
			competenceRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(path={"","/"}, method=RequestMethod.POST)	//necessaire de mettre post pour acceder au body, get ne marche pas
	public ResponseEntity<Void> create(@RequestBody Competence competence, BindingResult br, UriComponentsBuilder ucb){	//requestbody permet de dire, je cree/instancie adherent je vais recup info dans json et avec methode affectation a l'adherent
		if (competence.getKey()!=null){
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
			
		}else{
			competenceRepository.save(competence);
		}
		HttpHeaders header=new HttpHeaders();
		header.setLocation(ucb.path("/rest/competence/{id}").buildAndExpand(competence.getKey()).toUri()); //buildand expand est la pour indiquer l'id, peut avoir plusieur param a mettre dans l'ordre
		return new ResponseEntity<>(header,HttpStatus.OK);
	}
	
//	@RequestMapping(path={"","/"}, method=RequestMethod.PUT)
//	public ResponseEntity<Competence>update(@RequestBody Competence competence){	//on prefere renvoyer adherent car on peut refaire des changements directement derriere
//		
//		Optional<Competence> opt=competenceRepository.findById(competence.getKey());
//		if(opt.isPresent()){
//			Competence competenceEnBase=opt.get();
//			if (competence.getNiveau()!=null){
//				competenceEnBase.setNiveau(competence.getNiveau());
//			}
//			competenceRepository.save(competenceEnBase);
//			return new ResponseEntity<Competence>(competenceEnBase, HttpStatus.OK);
//		}else{
//			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//			
//		}
//	}
}
