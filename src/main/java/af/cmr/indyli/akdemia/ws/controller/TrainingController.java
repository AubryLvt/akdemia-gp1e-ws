package af.cmr.indyli.akdemia.ws.controller;

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

import af.cmr.indyli.akdemia.business.dto.basic.TrainingBasicDTO;
import af.cmr.indyli.akdemia.business.dto.full.TrainingFullDTO;
import af.cmr.indyli.akdemia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akdemia.business.service.ITrainingService;
import af.cmr.indyli.akdemia.business.utils.ConstsValues;
import af.cmr.indyli.akdemia.ws.utils.AkdemiaUrlBase;
import jakarta.annotation.Resource;

@RestController
@CrossOrigin(origins = AkdemiaUrlBase.url, maxAge = AkdemiaUrlBase.maxAge)
@RequestMapping("/trainings")
public class TrainingController {
	
	@Resource(name = ConstsValues.ServiceKeys.TRAINING_SERVICE_KEY)
    private ITrainingService trainingService;
	
	/**
	 * Retrieve a list of all trainings.
	 *
	 * @return ResponseEntity containing the list of trainings.
	 */
    @GetMapping
    public ResponseEntity<List<TrainingBasicDTO>> getAll() {
        return ResponseEntity.ok(ResponseEntity.ok(trainingService.findAll()).getBody());
    }
    
    /**
     * Retrieve information about a training by its identifier.
     *
     * @param id The identifier of the training.
     * @return ResponseEntity containing information about the training.
     * @throws AkdemiaBusinessException If a business exception occurs.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TrainingFullDTO> getOne(@PathVariable("id") int id) throws AkdemiaBusinessException {
        return ResponseEntity.ok(trainingService.findById(id));
    }

    /**
     * Create a new training.
     *
     * @param dto Information about the training to create.
     * @return ResponseEntity containing information about the newly created training.
     * @throws AkdemiaBusinessException If a business exception occurs.
     */
    @PostMapping
    public ResponseEntity<TrainingFullDTO> create(@RequestBody TrainingFullDTO trainingDTO) throws AkdemiaBusinessException {
        return ResponseEntity.ok(trainingService.create(trainingDTO));
    }

    /**
     * Update information about a training.
     *
     * @param id  The identifier of the training to update.
     * @param dto Information about the updated training.
     * @return ResponseEntity containing information about the updated training.
     * @throws AccessDeniedException    If access is denied.
     * @throws AkdemiaBusinessException If a business exception occurs.
     */
    @PutMapping("/{id}")
    public ResponseEntity<TrainingFullDTO> update(@PathVariable("id") int id, @RequestBody TrainingFullDTO trainingDTO) 
            throws AccessDeniedException, AkdemiaBusinessException {
        return ResponseEntity.ok(trainingService.update(trainingDTO));
    }

    /**
     * Delete a training by its identifier.
     *
     * @param id The identifier of the training to delete.
     * @return ResponseEntity indicating the success of the deletion.
     * @throws AkdemiaBusinessException If a business exception occurs.
     * @throws AccessDeniedException    If access is denied.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) throws AkdemiaBusinessException, AccessDeniedException {
        trainingService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
