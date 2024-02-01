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

import af.cmr.indyli.akademia.business.dto.basic.TrainingBasicDTO;
import af.cmr.indyli.akademia.business.dto.full.TrainingFullDTO;
import af.cmr.indyli.akademia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akademia.business.service.ITrainingService;
import af.cmr.indyli.akademia.business.utils.ConstsValues;
import jakarta.annotation.Resource;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/training")
public class TrainingController {

	@Resource(name = ConstsValues.ServiceKeys.TRAINING_SERVICE_KEY)
	private ITrainingService trainingService;

	@GetMapping
	public ResponseEntity<List<TrainingBasicDTO>> getAll() {
		return ResponseEntity.ok(ResponseEntity.ok(trainingService.findAll()).getBody());
	}

	@GetMapping("/{id}")
	public ResponseEntity<TrainingFullDTO> getOne(@PathVariable int id) throws AkdemiaBusinessException {
		return ResponseEntity.ok(trainingService.findById(id));
	}

	@PostMapping
	public ResponseEntity<TrainingFullDTO> create(@RequestBody TrainingFullDTO dto) throws AkdemiaBusinessException {
		return ResponseEntity.ok(trainingService.create(dto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<TrainingFullDTO> update(@PathVariable int id, @RequestBody TrainingFullDTO dto)
			throws AccessDeniedException, AkdemiaBusinessException {
		return ResponseEntity.ok(trainingService.update(dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) throws AkdemiaBusinessException, AccessDeniedException {
		trainingService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
