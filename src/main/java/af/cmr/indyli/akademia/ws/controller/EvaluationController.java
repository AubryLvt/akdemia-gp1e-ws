package af.cmr.indyli.akademia.ws.controller;

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

import af.cmr.indyli.akademia.business.dto.basic.EvaluationBasicDTO;
import af.cmr.indyli.akademia.business.dto.full.EvaluationFullDTO;
import af.cmr.indyli.akademia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akademia.business.service.IEvaluationService;
import af.cmr.indyli.akademia.business.utils.ConstsValues;
import jakarta.annotation.Resource;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/evaluations")
public class EvaluationController {

	@Resource(name = ConstsValues.ServiceKeys.EVALUATION_SERVICE_KEY)
	private IEvaluationService evaluationService;

	@GetMapping
	public ResponseEntity<List<EvaluationBasicDTO>> getAll() {
		return ResponseEntity.ok(ResponseEntity.ok(evaluationService.findAll()).getBody());
	}

	@GetMapping("/{id}")
	public ResponseEntity<EvaluationFullDTO> getOne(@PathVariable int id) throws AkdemiaBusinessException {
		return ResponseEntity.ok(evaluationService.findById(id));
	}

	@PostMapping
	public ResponseEntity<EvaluationFullDTO> create(@RequestBody EvaluationFullDTO dto)
			throws AkdemiaBusinessException {
		return ResponseEntity.ok(evaluationService.create(dto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<EvaluationFullDTO> update(@PathVariable int id, @RequestBody EvaluationFullDTO dto)
			throws AccessDeniedException, AkdemiaBusinessException {
		return ResponseEntity.ok(evaluationService.update(dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) throws AkdemiaBusinessException, AccessDeniedException {
		evaluationService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
