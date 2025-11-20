package ci.dgmp.sigefbackend.metier.controller;

import ci.dgmp.sigefbackend.admin.utilities.validatorgroups.CreateGroup;
import ci.dgmp.sigefbackend.admin.utilities.validatorgroups.UpdateGroup;
import ci.dgmp.sigefbackend.metier.service.interfaces.IGestionService;
import ci.dgmp.sigefbackend.metier.model.dtos.GestionDTO;
import jakarta.validation.Valid;
import jakarta.validation.groups.ConvertGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/Plan-formations")
@RequiredArgsConstructor
public class PlanFormationController
{
    private final IGestionService gestionService;

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public GestionDTO create(@Valid @ConvertGroup(to = CreateGroup.class) @RequestBody GestionDTO dto)
    {
        return gestionService.create(dto);
    }

    @PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public GestionDTO update(@Valid @ConvertGroup(to = UpdateGroup.class) @RequestBody GestionDTO dto) {
        return gestionService.update(dto);
    }

    @PutMapping(value = "/set-as-courant/{gesCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GestionDTO setAsCourant(@PathVariable("gesCode") Long gesCode)
    {
        return gestionService.setAsCourant(gesCode);
    }

    @GetMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<GestionDTO> search(
            @RequestParam(value = "key", required = false) String key,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size)
    {
        return gestionService.search(key, PageRequest.of(page, size));
    }
}
