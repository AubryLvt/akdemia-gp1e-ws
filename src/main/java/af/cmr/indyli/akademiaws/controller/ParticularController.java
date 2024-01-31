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

import af.cmr.indyli.akademia.business.dto.basic.ParticularBasicDTO;
import af.cmr.indyli.akademia.business.dto.full.ParticularFullDTO;
import af.cmr.indyli.akademia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akademia.business.service.IParticularService;
import af.cmr.indyli.akademia.business.utils.ConstsValues;
import jakarta.annotation.Resource;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/particular")
public class ParticularController {

	@Resource(name = ConstsValues.ServiceKeys.PARTICULAR_SERVICE_KEY)
	private IParticularService particularService;

	@GetMapping
	public ResponseEntity<List<ParticularBasicDTO>> getAll() {
		return ResponseEntity.ok(ResponseEntity.ok(particularService.findAll()).getBody());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ParticularFullDTO> getOne(@PathVariable int id) throws AkdemiaBusinessException {
		return ResponseEntity.ok(particularService.findById(id));
	}

	@PostMapping
	public ResponseEntity<ParticularFullDTO> create(@RequestBody ParticularFullDTO dto)
			throws AkdemiaBusinessException {
		return ResponseEntity.ok(particularService.create(dto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ParticularFullDTO> update(@PathVariable int id, @RequestBody ParticularFullDTO dto)
			throws AccessDeniedException, AkdemiaBusinessException {
		return ResponseEntity.ok(particularService.update(dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) throws AkdemiaBusinessException, AccessDeniedException {
		particularService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
