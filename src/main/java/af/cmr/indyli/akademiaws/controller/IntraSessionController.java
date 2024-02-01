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

import af.cmr.indyli.akademia.business.dto.basic.IntraSessionBasicDTO;
import af.cmr.indyli.akademia.business.dto.full.IntraSessionFullDTO;
import af.cmr.indyli.akademia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akademia.business.service.IntraSessionService;
import af.cmr.indyli.akademia.business.utils.ConstsValues;
import jakarta.annotation.Resource;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/intrasessions")
public class IntraSessionController {

	@Resource(name = ConstsValues.ServiceKeys.INTRA_SESSION_SERVICE_KEY)
	private IntraSessionService sessionService;

	@GetMapping
	public ResponseEntity<List<IntraSessionBasicDTO>> getAll() {
		return ResponseEntity.ok(ResponseEntity.ok(sessionService.findAll()).getBody());
	}

	@GetMapping("/{id}")
	public ResponseEntity<IntraSessionFullDTO> getOne(@PathVariable int id) throws AkdemiaBusinessException {
		return ResponseEntity.ok(sessionService.findById(id));
	}

	@PostMapping
	public ResponseEntity<IntraSessionFullDTO> create(@RequestBody IntraSessionFullDTO dto)
			throws AkdemiaBusinessException {
		return ResponseEntity.ok(sessionService.create(dto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<IntraSessionFullDTO> update(@PathVariable int id, @RequestBody IntraSessionFullDTO dto)
			throws AccessDeniedException, AkdemiaBusinessException {
		return ResponseEntity.ok(sessionService.update(dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) throws AkdemiaBusinessException, AccessDeniedException {
		sessionService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
