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

import af.cmr.indyli.akademia.business.dto.basic.RoleBasicDTO;
import af.cmr.indyli.akademia.business.dto.full.RoleFullDTO;
import af.cmr.indyli.akademia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akademia.business.service.IRoleService;
import af.cmr.indyli.akademia.business.utils.ConstsValues;
import jakarta.annotation.Resource;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/roles")
public class RoleController {

	@Resource(name = ConstsValues.ServiceKeys.ROLE_SERVICE_KEY)
	private IRoleService roleService;

	@GetMapping
	public ResponseEntity<List<RoleBasicDTO>> getAll() {
		return ResponseEntity.ok(ResponseEntity.ok(roleService.findAll()).getBody());
	}

	@GetMapping("/{id}")
	public ResponseEntity<RoleBasicDTO> getOne(@PathVariable int id) throws AkdemiaBusinessException {
		return ResponseEntity.ok(roleService.findById(id));
	}

	@PostMapping
	public ResponseEntity<RoleFullDTO> create(@RequestBody RoleFullDTO dto) throws AkdemiaBusinessException {
		return ResponseEntity.ok(roleService.create(dto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<RoleFullDTO> update(@PathVariable int id, @RequestBody RoleFullDTO dto)
			throws AccessDeniedException, AkdemiaBusinessException {
		return ResponseEntity.ok(roleService.update(dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) throws AkdemiaBusinessException, AccessDeniedException {
		roleService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
