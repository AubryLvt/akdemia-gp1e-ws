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

import af.cmr.indyli.akademia.business.dto.basic.ManagerBasicDTO;
import af.cmr.indyli.akademia.business.dto.full.ManagerFullDTO;
import af.cmr.indyli.akademia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akademia.business.service.IManagerService;
import af.cmr.indyli.akademia.business.utils.ConstsValues;
import jakarta.annotation.Resource;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/managers")
public class ManagerController {

	@Resource(name = ConstsValues.ServiceKeys.MANAGER_SERVICE_KEY)
	private IManagerService managerService;

	@GetMapping
	public ResponseEntity<List<ManagerBasicDTO>> getAll() {
		return ResponseEntity.ok(ResponseEntity.ok(managerService.findAll()).getBody());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ManagerBasicDTO> getOne(@PathVariable int id) throws AkdemiaBusinessException {
		return ResponseEntity.ok(managerService.findById(id));
	}

	@PostMapping
	public ResponseEntity<ManagerFullDTO> create(@RequestBody ManagerFullDTO dto) throws AkdemiaBusinessException {
		return ResponseEntity.ok(managerService.create(dto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ManagerFullDTO> update(@PathVariable int id, @RequestBody ManagerFullDTO dto)
			throws AccessDeniedException, AkdemiaBusinessException {
		return ResponseEntity.ok(managerService.update(dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) throws AkdemiaBusinessException, AccessDeniedException {
		managerService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
