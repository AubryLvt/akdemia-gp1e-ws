package af.cmr.indyli.akademiaws.controller;

import java.nio.file.AccessDeniedException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import af.cmr.indyli.akademia.business.dto.basic.TrainerBasicDTO;
import af.cmr.indyli.akademia.business.dto.full.TrainerFullDTO;
import af.cmr.indyli.akademia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akademia.business.service.ITrainerService;
import af.cmr.indyli.akademia.business.utils.ConstsValues;
import jakarta.annotation.Resource;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/trainers")
public class TrainerController {

	@Resource(name = ConstsValues.ServiceKeys.TRAINER_SERVICE_KEY)
	private ITrainerService trainerService;

	@GetMapping
	public ResponseEntity<List<TrainerBasicDTO>> getAll() {
		return ResponseEntity.ok(ResponseEntity.ok(trainerService.findAll()).getBody());
	}

	@GetMapping("/{id}")
	public ResponseEntity<TrainerFullDTO> getOne(@PathVariable int id) throws AkdemiaBusinessException {
		return ResponseEntity.ok(trainerService.findById(id));
	}

	@PostMapping
	public ResponseEntity<TrainerFullDTO> create(@RequestBody TrainerFullDTO dto) throws AkdemiaBusinessException {
		System.out.println("ici");
		return ResponseEntity.ok(trainerService.create(dto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<TrainerFullDTO> update(@PathVariable int id, @RequestBody TrainerFullDTO dto)
			throws AccessDeniedException, AkdemiaBusinessException {
		return ResponseEntity.ok(trainerService.update(dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) throws AkdemiaBusinessException, AccessDeniedException {
		trainerService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
