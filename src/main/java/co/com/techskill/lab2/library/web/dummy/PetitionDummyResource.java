package co.com.techskill.lab2.library.web.dummy;

import co.com.techskill.lab2.library.domain.dto.PetitionDTO;
import co.com.techskill.lab2.library.service.dummy.PetitionService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/dummy/petitions")
public class PetitionDummyResource {
    private PetitionService petitionService;

    public PetitionDummyResource(PetitionService petitionService) {
        this.petitionService = petitionService;
    }

    @GetMapping("/filter")
    public Flux<String> filterByType() {
        return petitionService.findPetitionReturn();
    }
}
