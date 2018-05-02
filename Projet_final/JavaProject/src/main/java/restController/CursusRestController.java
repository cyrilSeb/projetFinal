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
import model.Cursus;
import repository.CursusRepository;

@RestController
@RequestMapping("/rest/cursus")
@CrossOrigin(origins={"http://localhost:4200"})
public class CursusRestController {

	@Autowired
	private CursusRepository cursusRepository;
	
	@JsonView(JsonViews.Common.class) // permet de spécifier que l'on veut recup JSON spécifique, la vue
	@RequestMapping(path={"","/"}, method=RequestMethod.GET)	//pour avoir une adresse commune
	public ResponseEntity<List<Cursus>> findAll(){
		
		return new ResponseEntity<List<Cursus>>(cursusRepository.findAll(), HttpStatus.OK);
		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	@JsonView(JsonViews.Common.class)
	public ResponseEntity<Cursus> findByKey(@PathVariable(name="id") String nom){
		Optional<Cursus> opt=cursusRepository.findById(nom);
		if(opt.isPresent()){
			return new ResponseEntity<Cursus>(opt.get(), HttpStatus.OK);
		}else{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete (@PathVariable(name="id") String nom){
		Optional<Cursus> opt=cursusRepository.findById(nom);
		if(opt.isPresent()){
			cursusRepository.deleteById(nom);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(path={"","/"}, method=RequestMethod.POST)	//necessaire de mettre post pour acceder au body, get ne marche pas
	public ResponseEntity<Void> create(@RequestBody Cursus cursus, BindingResult br, UriComponentsBuilder ucb){	//requestbody permet de dire, je cree/instancie adherent je vais recup info dans json et avec methode affectation a l'adherent
		if (cursus.getNom()!=null){
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
			
		}else{
			cursusRepository.save(cursus);
		}
		HttpHeaders header=new HttpHeaders();
		header.setLocation(ucb.path("/rest/cursus/{id}").buildAndExpand(cursus.getNom()).toUri()); //buildand expand est la pour indiquer l'id, peut avoir plusieur param a mettre dans l'ordre
		return new ResponseEntity<>(header,HttpStatus.OK);
	}
	
	@RequestMapping(path={"","/"}, method=RequestMethod.PUT)
	public ResponseEntity<Cursus>update(@RequestBody Cursus cursus){	//on prefere renvoyer adherent car on peut refaire des changements directement derriere
		
		Optional<Cursus> opt=cursusRepository.findById(cursus.getNom());
		if(opt.isPresent()){
			Cursus cursusEnBase=opt.get();
			if (cursus.getDates()!=null){
				cursusEnBase.setDates(cursus.getDates());
			}
			if (cursus.getGestionnaire()!=null){
				cursusEnBase.setGestionnaire(cursus.getGestionnaire());
			}
			cursusRepository.save(cursusEnBase);
			return new ResponseEntity<Cursus>(cursusEnBase, HttpStatus.OK);
		}else{
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			
		}
	}
}
