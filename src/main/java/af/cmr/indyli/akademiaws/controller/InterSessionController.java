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

import af.cmr.indyli.akademia.business.dto.basic.InterSessionBasicDTO;
import af.cmr.indyli.akademia.business.dto.full.InterSessionFullDTO;
import af.cmr.indyli.akademia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akademia.business.service.InterSessionService;
import af.cmr.indyli.akademia.business.utils.ConstsValues;
import jakarta.annotation.Resource;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/intersessions")
public class InterSessionController {

	@Resource(name = ConstsValues.ServiceKeys.INTER_SESSION_SERVICE_KEY)
	private InterSessionService sessionService;

	@GetMapping
	public ResponseEntity<List<InterSessionBasicDTO>> getAll() {
		return ResponseEntity.ok(ResponseEntity.ok(sessionService.findAll()).getBody());
	}

	@GetMapping("/{id}")
	public ResponseEntity<InterSessionFullDTO> getOne(@PathVariable int id) throws AkdemiaBusinessException {
		return ResponseEntity.ok(sessionService.findById(id));
	}

	@PostMapping
	public ResponseEntity<InterSessionFullDTO> create(@RequestBody InterSessionFullDTO dto)
			throws AkdemiaBusinessException {
		return ResponseEntity.ok(sessionService.create(dto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<InterSessionFullDTO> update(@PathVariable int id, @RequestBody InterSessionFullDTO dto)
			throws AccessDeniedException, AkdemiaBusinessException {
		return ResponseEntity.ok(sessionService.update(dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) throws AkdemiaBusinessException, AccessDeniedException {
		sessionService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
