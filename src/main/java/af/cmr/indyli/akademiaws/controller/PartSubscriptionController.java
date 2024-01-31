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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import af.cmr.indyli.akademia.business.dto.basic.ParticularSubscriptionBasicDTO;
import af.cmr.indyli.akademia.business.dto.full.InterSessionFullDTO;
import af.cmr.indyli.akademia.business.dto.full.ParticularSubscriptionFullDTO;
import af.cmr.indyli.akademia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akademia.business.service.IParticularSubscriptionService;
import af.cmr.indyli.akademia.business.utils.ConstsValues;
import jakarta.annotation.Resource;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/partsubscriptions")
public class PartSubscriptionController {

	@Resource(name = ConstsValues.ServiceKeys.PARTICULAR_SOUSCRIPTION_SERVICE_KEY)
	private IParticularSubscriptionService souscriptionService;

	@GetMapping
	public ResponseEntity<List<ParticularSubscriptionBasicDTO>> getAll() {
		return ResponseEntity.ok(ResponseEntity.ok(souscriptionService.findAll()).getBody());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ParticularSubscriptionFullDTO> getOne(@PathVariable int id) throws AkdemiaBusinessException {
		return ResponseEntity.ok(souscriptionService.findById(id));
	}

	@PostMapping
	public ResponseEntity<ParticularSubscriptionFullDTO> create(@RequestBody ParticularSubscriptionFullDTO dto)
			throws AkdemiaBusinessException {
		return ResponseEntity.ok(souscriptionService.create(dto));
	}

	@GetMapping("/attach/{interSessionId}")
	public ResponseEntity<InterSessionFullDTO> attachUser(@PathVariable int interSessionId,
			@RequestParam List<Integer> particularsId) throws AkdemiaBusinessException {
		return ResponseEntity.ok(souscriptionService.create(interSessionId, particularsId));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ParticularSubscriptionFullDTO> update(@PathVariable int id,
			@RequestBody ParticularSubscriptionFullDTO dto) throws AccessDeniedException, AkdemiaBusinessException {
		return ResponseEntity.ok(souscriptionService.update(dto));
	}

	@GetMapping("/detach")
	public ResponseEntity<?> detachUser(@RequestParam Integer interSessionId, @RequestParam Integer particularId)
			throws AkdemiaBusinessException, AccessDeniedException {
		souscriptionService.delete(interSessionId, particularId);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) throws AkdemiaBusinessException, AccessDeniedException {
		souscriptionService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
