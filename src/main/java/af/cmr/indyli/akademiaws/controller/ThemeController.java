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

import af.cmr.indyli.akademia.business.dto.basic.ThemeBasicDTO;
import af.cmr.indyli.akademia.business.dto.full.ThemeFullDTO;
import af.cmr.indyli.akademia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akademia.business.service.IThemeService;
import af.cmr.indyli.akademia.business.utils.ConstsValues;
import jakarta.annotation.Resource;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/themes")
public class ThemeController {

	@Resource(name = ConstsValues.ServiceKeys.THEME_SERVICE_KEY)
	private IThemeService iThemeService;

	@GetMapping
	public ResponseEntity<List<ThemeBasicDTO>> getAll() {
		return ResponseEntity.ok(ResponseEntity.ok(iThemeService.findAll()).getBody());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ThemeFullDTO> getOne(@PathVariable int id) throws AkdemiaBusinessException {
		return ResponseEntity.ok(iThemeService.findById(id));
	}

	@PostMapping
	public ResponseEntity<ThemeFullDTO> create(@RequestBody ThemeFullDTO dto) throws AkdemiaBusinessException {
		return ResponseEntity.ok(iThemeService.create(dto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ThemeFullDTO> update(@PathVariable int id, @RequestBody ThemeFullDTO dto)
			throws AkdemiaBusinessException, AccessDeniedException {
		return ResponseEntity.ok(iThemeService.update(dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) throws AkdemiaBusinessException, AccessDeniedException {
		iThemeService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
