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

import af.cmr.indyli.akademia.business.dto.basic.ValidationTestBasicDTO;
import af.cmr.indyli.akademia.business.dto.full.ValidationTestFullDTO;
import af.cmr.indyli.akademia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akademia.business.service.IValidationTestService;
import af.cmr.indyli.akademia.business.utils.ConstsValues;
import jakarta.annotation.Resource;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/validationtests")
public class ValidationTestController {

	@Resource(name = ConstsValues.ServiceKeys.VALIDATION_TEST_SERVICE_KEY)
	private IValidationTestService validationTestService;

	@GetMapping
	public ResponseEntity<List<ValidationTestBasicDTO>> getAll() {
		return ResponseEntity.ok(ResponseEntity.ok(validationTestService.findAll()).getBody());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ValidationTestBasicDTO> getOne(@PathVariable int id) throws AkdemiaBusinessException {
		return ResponseEntity.ok(validationTestService.findById(id));
	}

	@PostMapping
	public ResponseEntity<ValidationTestFullDTO> create(@RequestBody ValidationTestFullDTO dto)
			throws AkdemiaBusinessException {
		return ResponseEntity.ok(validationTestService.create(dto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ValidationTestFullDTO> update(@PathVariable int id, @RequestBody ValidationTestFullDTO dto)
			throws AccessDeniedException, AkdemiaBusinessException {
		return ResponseEntity.ok(validationTestService.update(dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) throws AkdemiaBusinessException, AccessDeniedException {
		validationTestService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
