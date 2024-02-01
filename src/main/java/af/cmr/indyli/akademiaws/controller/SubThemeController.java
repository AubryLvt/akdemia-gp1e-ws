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

import af.cmr.indyli.akademia.business.dto.basic.SubThemeBasicDTO;
import af.cmr.indyli.akademia.business.dto.full.SubThemeFullDTO;
import af.cmr.indyli.akademia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akademia.business.service.ISubThemeService;
import af.cmr.indyli.akademia.business.utils.ConstsValues;
import jakarta.annotation.Resource;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/subthemes")
public class SubThemeController {

	@Resource(name = ConstsValues.ServiceKeys.SUB_THEME_SERVICE_KEY)
	private ISubThemeService subThemeService;

	@GetMapping
	public ResponseEntity<List<SubThemeBasicDTO>> getAll() {
		return ResponseEntity.ok(ResponseEntity.ok(subThemeService.findAll()).getBody());
	}

	@GetMapping("/{id}")
	public ResponseEntity<SubThemeFullDTO> getOne(@PathVariable int id) throws AkdemiaBusinessException {
		return ResponseEntity.ok(subThemeService.findById(id));
	}

	@PostMapping
	public ResponseEntity<SubThemeFullDTO> create(@RequestBody SubThemeFullDTO dto) throws AkdemiaBusinessException {
		return ResponseEntity.ok(subThemeService.create(dto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<SubThemeFullDTO> update(@PathVariable int id, @RequestBody SubThemeFullDTO dto)
			throws AkdemiaBusinessException, AccessDeniedException {
		return ResponseEntity.ok(subThemeService.update(dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) throws AkdemiaBusinessException, AccessDeniedException {
		subThemeService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
