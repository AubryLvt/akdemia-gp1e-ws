package af.cmr.indyli.akademiaws.controller;

import af.cmr.indyli.akademia.business.dto.basic.EmployeeSubscriptionBasicDTO;
import af.cmr.indyli.akademia.business.dto.full.EmployeeSubscriptionFullDTO;
import af.cmr.indyli.akademia.business.dto.full.IntraSessionFullDTO;
import af.cmr.indyli.akademia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akademia.business.service.IEmployeeSubscriptionService;
import af.cmr.indyli.akademia.business.utils.ConstsValues;
import jakarta.annotation.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/emplsubscriptions")
public class EmplSubscriptionController {

    @Resource(name = ConstsValues.ServiceKeys.EMPLOYEE_SOUSCRIPTION_SERVICE_KEY)
    private IEmployeeSubscriptionService souscriptionService;

    @GetMapping
    public ResponseEntity<List<EmployeeSubscriptionBasicDTO>> getAll() {
        return ResponseEntity.ok(ResponseEntity.ok(souscriptionService.findAll()).getBody());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeSubscriptionFullDTO> getOne(@PathVariable int id) throws AkdemiaBusinessException {
        return ResponseEntity.ok(souscriptionService.findById(id));
    }

    @PostMapping
    public ResponseEntity<EmployeeSubscriptionFullDTO> create(@RequestBody EmployeeSubscriptionFullDTO dto) throws AkdemiaBusinessException {
        return ResponseEntity.ok(souscriptionService.create(dto));
    }

    @GetMapping("/attach/{intraSessionId}")
    public ResponseEntity<IntraSessionFullDTO> attachUser(@PathVariable int intraSessionId, @RequestParam List<Integer> particularsId) throws AkdemiaBusinessException {
        return ResponseEntity.ok(souscriptionService.create(intraSessionId, particularsId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeSubscriptionFullDTO> update(@PathVariable int id, @RequestBody EmployeeSubscriptionFullDTO dto) throws AccessDeniedException, AkdemiaBusinessException {
        return ResponseEntity.ok(souscriptionService.update(dto));
    }

/*	@GetMapping("/detach")
	public ResponseEntity<?> detachUser(@RequestParam Integer interSessionId, @RequestParam Integer particularId)
			throws AkdemiaBusinessException, AccessDeniedException {
		souscriptionService.deleteById(interSessionId, particularId);
		return ResponseEntity.ok().build();
	}*/

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) throws AkdemiaBusinessException, AccessDeniedException {
        souscriptionService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
