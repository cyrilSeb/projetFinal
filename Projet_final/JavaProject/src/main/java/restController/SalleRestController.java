package restController;

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

import com.fasterxml.jackson.annotation.JsonView;

import model.Cursus;
import model.JsonViews;
import model.Salle;
import repository.CursusRepository;
import repository.SalleRepository;

@RestController
@RequestMapping("/salle")
@CrossOrigin(origins = "*")
public class SalleRestController {
	@Autowired
	private SalleRepository salleRepository;

	@Autowired
	private CursusRepository cursusRepository;

	@JsonView(JsonViews.Common.class)
	@RequestMapping(path = { "", "/" }, method = RequestMethod.GET)
	public ResponseEntity<List<Salle>> findAll() {
		return new ResponseEntity<List<Salle>>(salleRepository.findAll(), HttpStatus.OK);
	}

	@RequestMapping(path = { "", "/", "/infos", "/infos/" }, method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody Salle salle, BindingResult rs, UriComponentsBuilder ucb) {
		if (salle.getId() != null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			if (salle.getCursus() != null) {
				Set<Cursus> setCursus = salle.getCursus();
				salle.getCursus().clear();
				while (setCursus.iterator().hasNext()) {
					Cursus cursus = setCursus.iterator().next();
					Optional<Cursus> opt = cursusRepository.findById(cursus.getId());
					if (opt.isPresent()) {
						cursus = opt.get();
						cursus.setSalle(salle);
						cursusRepository.save(cursus);
						salle.getCursus().add(cursus);
					} else {
						return new ResponseEntity<>(HttpStatus.NO_CONTENT);
					}
				}
			}
			salleRepository.save(salle);
			HttpHeaders header = new HttpHeaders();
			header.setLocation(ucb.path("/cursus/{id}").buildAndExpand(salle.getId()).toUri());
			return new ResponseEntity<>(header, HttpStatus.OK);
		}
	}

	@RequestMapping(path = { "", "/", "/infos", "/infos/" }, method = RequestMethod.PUT)
	public ResponseEntity<Salle> update(@RequestBody Salle salle) {
		Optional<Salle> opt = salleRepository.findById(salle.getId());
		if (opt.isPresent()) {
			Salle salleUp = opt.get();
			if (salle.getAdresse() != null) {
				salleUp.setAdresse(salle.getAdresse());
			}
			if (salle.getCapacite() != null) {
				salleUp.setCapacite(salle.getCapacite());
			}
			if (salle.getCursus() != null) {
				Set<Cursus> setCursus = salle.getCursus();
				salle.getCursus().clear();
				while (setCursus.iterator().hasNext()) {
					Cursus cursus = setCursus.iterator().next();
					Optional<Cursus> optCrs = cursusRepository.findById(cursus.getId());
					if (optCrs.isPresent()) {
						cursus = optCrs.get();
						cursus.setSalle(salle);
						cursusRepository.save(cursus);
						salle.getCursus().add(cursus);
					} else {
						return new ResponseEntity<>(HttpStatus.NO_CONTENT);
					}
				}
			}
			if (salle.getNumero() != null) {
				salleUp.setNumero(salle.getNumero());
			}
			salleRepository.save(salleUp);
			return new ResponseEntity<Salle>(salleUp, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable(name = "id") Long numero) {
		Optional<Salle> opt = salleRepository.findById(numero);
		if (opt.isPresent()) {
			Salle salle = opt.get();
			if (salle.getCursus() != null) {
				Set<Cursus> setCursus = salle.getCursus();
				salle.getCursus().clear();
				while (setCursus.iterator().hasNext()) {
					Cursus cursus = setCursus.iterator().next();
					Optional<Cursus> optCrs = cursusRepository.findById(cursus.getId());
					if (optCrs.isPresent()) {
						cursus = optCrs.get();
						cursus.setSalle(null);
						cursusRepository.save(cursus);
					} else {
						return new ResponseEntity<>(HttpStatus.NO_CONTENT);
					}
				}
			}
			salleRepository.deleteById(numero);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
