package com.exemple.restController;

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

import com.exemple.model.Competence;
import com.exemple.model.FormateurMatierePK;
import com.exemple.model.JsonViews;
import com.exemple.model.Matiere;
import com.exemple.model.Module;
import com.exemple.repository.MatiereRepository;
import com.exemple.repository.ModuleRepository;
import com.fasterxml.jackson.annotation.JsonView;

@RestController
@RequestMapping("/matiere")
@CrossOrigin(origins = "*")
public class MatiereRestController {
	@Autowired
	private MatiereRepository matiereRepository;

	@Autowired
	private ModuleRepository moduleRepository;

	@JsonView(JsonViews.Common.class)
	@RequestMapping(path = { "", "/" }, method = RequestMethod.GET)
	public ResponseEntity<List<Matiere>> findAll() {
		return new ResponseEntity<List<Matiere>>(matiereRepository.findAll(), HttpStatus.OK);
	}

	@JsonView(JsonViews.Common.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Matiere> findById(@PathVariable(name = "id") Long numero) {
		Optional<Matiere> opt = matiereRepository.findById(numero);
		if (opt.isPresent()) {
			return new ResponseEntity<Matiere>(opt.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(path = { "", "/" }, method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody Matiere matiere, BindingResult rs, UriComponentsBuilder ucb) {
		if (matiere.getId() != null) {
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		} else {
			if (matiere.getPrerequis() != null) {
				Set<Matiere> prerequis = matiere.getPrerequis();
				matiere.getPrerequis().clear();
				while (prerequis.iterator().hasNext()) {
					Matiere req = prerequis.iterator().next();
					Optional<Matiere> opt = matiereRepository.findById(req.getId());
					if (opt.isPresent()) {
						matiere.getPrerequis().add(opt.get());
					}
				}
			}
			if (matiere.getSetModules() != null) {
				Set<Module> setMods = matiere.getSetModules();
				matiere.getSetModules().clear();
				while (setMods.iterator().hasNext()) {
					Module mod = setMods.iterator().next();
					Optional<Module> opt = moduleRepository.findById(mod.getId());
					if (opt.isPresent()) {
						matiere.getSetModules().add(opt.get());
					}
				}
			}
			matiereRepository.save(matiere);
			HttpHeaders header = new HttpHeaders();
			header.setLocation(ucb.path("/cursus/{id}").buildAndExpand(matiere.getId()).toUri());
			return new ResponseEntity<>(header, HttpStatus.OK);
		}
	}

	@RequestMapping(path = { "", "/" }, method = RequestMethod.PUT)
	public ResponseEntity<Matiere> update(@RequestBody Matiere matiere) {
		Optional<Matiere> opt = matiereRepository.findById(matiere.getId());
		if (opt.isPresent()) {
			Matiere mat = opt.get();
			if (matiere.getContenu() != null) {
				mat.setContenu(matiere.getContenu());
			}
			if (matiere.getNbHeure() != null) {
				mat.setNbHeure(matiere.getNbHeure());
			}
			if (matiere.getObjectifs() != null) {
				mat.setObjectifs(matiere.getObjectifs());
			}
			if (matiere.getPrerequis() != null) {
				Set<Matiere> prerequis = matiere.getPrerequis();
				matiere.getPrerequis().clear();
				while (prerequis.iterator().hasNext()) {
					Matiere req = prerequis.iterator().next();
					if (req.getId() != null) {
						Optional<Matiere> optMat = matiereRepository.findById(req.getId());
						if (optMat.isPresent()) {
							mat.getPrerequis().add(optMat.get());
						}
					}
				}
			}
			if (matiere.getSetModules() != null) {
				Set<Module> setMods = matiere.getSetModules();
				matiere.getSetModules().clear();
				while (setMods.iterator().hasNext()) {
					Module mod = setMods.iterator().next();
					if (mod.getId() != null) {
						Optional<Module> optMod = moduleRepository.findById(mod.getId());
						if (optMod.isPresent()) {
							mod = optMod.get();
							mod.setMatiere(mat);
							moduleRepository.save(mod);
							mat.getSetModules().add(optMod.get());
						}
					}
				}
			}
			if (matiere.getTitre() != null) {
				mat.setTitre(matiere.getTitre());
			}
			matiereRepository.save(mat);
			return new ResponseEntity<Matiere>(mat, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable(name = "id") Long numero) {
		Optional<Matiere> opt = matiereRepository.findById(numero);
		if (opt.isPresent()) {
			Matiere matiere = opt.get();
			if (matiere.getPrerequis() != null) {
				matiere.getPrerequis().clear();
				matiereRepository.save(matiere);
			}
			if (matiere.getSetModules() != null) {
				Set<Module> setMods = matiere.getSetModules();
				matiere.getSetModules().clear();
				while (setMods.iterator().hasNext()) {
					Module mod = setMods.iterator().next();
					Optional<Module> optMod = moduleRepository.findById(mod.getId());
					mod = optMod.get();
					mod.setMatiere(null);
					moduleRepository.save(mod);
				}
			}
			matiereRepository.deleteById(numero);
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}