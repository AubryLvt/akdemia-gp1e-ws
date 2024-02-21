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

import af.cmr.indyli.akademia.business.dto.full.EmployeeFullDTO;
import af.cmr.indyli.akademia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akademia.business.service.IEmployeeService;
import af.cmr.indyli.akademia.business.utils.ConstsValues;
import jakarta.annotation.Resource;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/employees")
public class EmployeeController {

	@Resource(name = ConstsValues.ServiceKeys.EMPLOYEE_SERVICE_KEY)
	private IEmployeeService employeeService;

	@GetMapping
	public ResponseEntity<List<EmployeeFullDTO>> getAll() {
		return ResponseEntity.ok(ResponseEntity.ok(employeeService.findAllByFull()).getBody());
	}

	@GetMapping("/{id}")
	public ResponseEntity<EmployeeFullDTO> getOne(@PathVariable int id) throws AkdemiaBusinessException {
		return ResponseEntity.ok(employeeService.findById(id));
	}

	@PostMapping
	public ResponseEntity<EmployeeFullDTO> create(@RequestBody EmployeeFullDTO dto) throws AkdemiaBusinessException {
		return ResponseEntity.ok(employeeService.create(dto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<EmployeeFullDTO> update(@PathVariable int id, @RequestBody EmployeeFullDTO dto)
			throws AccessDeniedException, AkdemiaBusinessException {
		return ResponseEntity.ok(employeeService.update(dto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) throws AkdemiaBusinessException, AccessDeniedException {
		employeeService.deleteById(id);
		return ResponseEntity.ok().build();
	}
}