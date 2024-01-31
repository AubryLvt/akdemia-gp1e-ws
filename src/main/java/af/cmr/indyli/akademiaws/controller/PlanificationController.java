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

import af.cmr.indyli.akademia.business.dto.basic.PlanificationBasicDTO;
import af.cmr.indyli.akademia.business.dto.full.PlanificationFullDTO;
import af.cmr.indyli.akademia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akademia.business.service.IPlanificationService;
import af.cmr.indyli.akademia.business.utils.ConstsValues;
import jakarta.annotation.Resource;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/planifications")
public class PlanificationController {

	@Resource(name = ConstsValues.ServiceKeys.PLANIFICATION_SERVICE_KEY)
	private IPlanificationService planificationService;

	@GetMapping
	public ResponseEntity<List<PlanificationBasicDTO>> getAll() {
		return ResponseEntity.ok(ResponseEntity.ok(planificationService.findAll()).getBody());
	}

	@GetMapping("/{id}")
	public ResponseEntity<PlanificationFullDTO> getOne(@PathVariable int id) throws AkdemiaBusinessException {
		return ResponseEntity.ok(planificationService.findById(id));
	}

	@PostMapping
	public ResponseEntity<PlanificationFullDTO> create(@RequestBody PlanificationFullDTO dto)
			throws AkdemiaBusinessException {
		return ResponseEntity.ok(planificationService.create(dto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<PlanificationFullDTO> update(@PathVariable int id, @RequestBody PlanificationFullDTO dto)
			throws AccessDeniedException, AkdemiaBusinessException {
		return ResponseEntity.ok(planificationService.update(dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) throws AkdemiaBusinessException, AccessDeniedException {
		planificationService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
