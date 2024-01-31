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

import af.cmr.indyli.akademia.business.dto.basic.CompanyBasicDTO;
import af.cmr.indyli.akademia.business.dto.full.CompanyFullDTO;
import af.cmr.indyli.akademia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akademia.business.service.ICompanyService;
import af.cmr.indyli.akademia.business.utils.ConstsValues;
import jakarta.annotation.Resource;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/companies")
public class CompagnyController {

    @Resource(name = ConstsValues.ServiceKeys.COMPANY_SERVICE_KEY)
    private ICompanyService companyService;

    @GetMapping
    public ResponseEntity<List<CompanyBasicDTO>> getAll(){
        return ResponseEntity.ok(ResponseEntity.ok(companyService.findAll()).getBody());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyFullDTO> getOne(@PathVariable int id) throws AkdemiaBusinessException {
        return ResponseEntity.ok(companyService.findById(id));
    }

    @PostMapping
    public  ResponseEntity<CompanyFullDTO> create(@RequestBody CompanyFullDTO dto) throws AkdemiaBusinessException {
        return ResponseEntity.ok(companyService.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyFullDTO> update(@PathVariable int id, @RequestBody CompanyFullDTO dto) throws AccessDeniedException, AkdemiaBusinessException {
        return ResponseEntity.ok(companyService.update(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) throws AkdemiaBusinessException, AccessDeniedException {
        companyService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
