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

import af.cmr.indyli.akademia.business.dto.basic.RequirementBasicDTO;
import af.cmr.indyli.akademia.business.dto.full.RequirementFullDTO;
import af.cmr.indyli.akademia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akademia.business.service.IRequirementService;
import af.cmr.indyli.akademia.business.utils.ConstsValues;
import jakarta.annotation.Resource;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/requirements")
public class RequirementController {
	@Resource(name = ConstsValues.ServiceKeys.REQUIREMENT_SERVICE_KEY)
	private IRequirementService requirementService;

	@GetMapping
	public ResponseEntity<List<RequirementBasicDTO>> getAll() {
		return ResponseEntity.ok(ResponseEntity.ok(requirementService.findAll()).getBody());
	}

	@GetMapping("/{id}")
	public ResponseEntity<RequirementBasicDTO> getOne(@PathVariable int id) throws AkdemiaBusinessException {
		return ResponseEntity.ok(requirementService.findById(id));
	}

	@PostMapping
	public ResponseEntity<RequirementFullDTO> create(@RequestBody RequirementFullDTO dto)
			throws AkdemiaBusinessException {
		return ResponseEntity.ok(requirementService.create(dto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<RequirementFullDTO> update(@PathVariable int id, @RequestBody RequirementFullDTO dto)
			throws AccessDeniedException, AkdemiaBusinessException {
		return ResponseEntity.ok(requirementService.update(dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) throws AkdemiaBusinessException, AccessDeniedException {
		requirementService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
