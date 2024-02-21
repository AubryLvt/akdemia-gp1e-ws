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

import af.cmr.indyli.akademia.business.dto.basic.TestBasicDTO;
import af.cmr.indyli.akademia.business.dto.full.TestFullDTO;
import af.cmr.indyli.akademia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akademia.business.service.ITestService;
import af.cmr.indyli.akademia.business.utils.ConstsValues;
import jakarta.annotation.Resource;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/tests")
public class TestController {

	@Resource(name = ConstsValues.ServiceKeys.TEST_SERVICE_KEY)
	private ITestService testService;

	@GetMapping
	public ResponseEntity<List<TestBasicDTO>> getAll() {
		return ResponseEntity.ok(ResponseEntity.ok(testService.findAll()).getBody());
	}

	@GetMapping("/{id}")
	public ResponseEntity<TestBasicDTO> getOne(@PathVariable int id) throws AkdemiaBusinessException {
		return ResponseEntity.ok(testService.findById(id));
	}

	@PostMapping
	public ResponseEntity<TestFullDTO> create(@RequestBody TestFullDTO dto) throws AkdemiaBusinessException {
		return ResponseEntity.ok(testService.create(dto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<TestFullDTO> update(@PathVariable int id, @RequestBody TestFullDTO dto)
			throws AccessDeniedException, AkdemiaBusinessException {
		return ResponseEntity.ok(testService.update(dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) throws AkdemiaBusinessException, AccessDeniedException {
		testService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}
