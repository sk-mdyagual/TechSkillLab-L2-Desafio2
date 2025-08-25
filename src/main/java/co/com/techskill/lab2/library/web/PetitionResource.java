package co.com.techskill.lab2.library.web;

import co.com.techskill.lab2.library.domain.dto.PetitionDTO;
import co.com.techskill.lab2.library.service.IPetitionService;
import co.com.techskill.lab2.library.service.dummy.PetitionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/petitions")
public class PetitionResource {
    private final IPetitionService petitionService;
    private final PetitionService dummyPetitionService;

    public PetitionResource(IPetitionService petitionService,PetitionService dummyPetitionService){
        this.petitionService = petitionService;
        this.dummyPetitionService = dummyPetitionService;
    }



    @GetMapping("/all")
    public Flux<PetitionDTO> getAllPetitions(){
        return petitionService.findALl();
    }

    @PostMapping("/id")
    public Mono<ResponseEntity<PetitionDTO>> findByPetitionId(@RequestBody PetitionDTO petitionDTO){
        return petitionService.findById(petitionDTO.getPetitionId())
                .map(ResponseEntity::ok);
    }

    @PostMapping("/id/intermitence")
    public Mono<ResponseEntity<String>> findByPetitionIdItermitence(@RequestBody PetitionDTO petitionDTO){
        return petitionService.simulateIntermittency(petitionDTO)
                .map(ResponseEntity::ok);

    }

    @PostMapping("/save")
    public Mono<ResponseEntity<PetitionDTO>> savePetition(@RequestBody PetitionDTO petitionDTO){
        return petitionService.save(petitionDTO)
                .map(ResponseEntity::ok);

    }

    @PostMapping("/revisar")
    public Flux<String> checkPetitions(@RequestBody PetitionDTO petitionDTO) {
        return petitionService.checkPriorities(petitionDTO);
    }

    @PostMapping("/dummy/intermitence")
    public Mono<ResponseEntity<String>> findByPetitionIdItermitenceReto(@RequestBody PetitionDTO petitionDTO){
        return dummyPetitionService.simulateIntermittency(petitionDTO)
                .map(ResponseEntity::ok);

    }

    @PostMapping("/dummy/proccess")
    public Flux<String> processPetitionReto(@RequestBody PetitionDTO petitionDTO){
        return dummyPetitionService.processPetition(petitionDTO);

    }
}
