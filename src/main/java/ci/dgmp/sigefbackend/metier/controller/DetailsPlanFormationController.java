package ci.dgmp.sigefbackend.metier.controller;

import ci.dgmp.sigefbackend.admin.utilities.validatorgroups.CreateActionGroup;
import ci.dgmp.sigefbackend.admin.utilities.validatorgroups.UpdateActionGroup;
import ci.dgmp.sigefbackend.admin.utilities.validatorgroups.CreateActiviteGroup;
import ci.dgmp.sigefbackend.admin.utilities.validatorgroups.UpdateActiviteGroup;
import ci.dgmp.sigefbackend.metier.model.dtos.ActionActivteDTO;
import ci.dgmp.sigefbackend.metier.model.views.VDetailsPlanFormation;
import ci.dgmp.sigefbackend.metier.service.interfaces.IDetailsPlanFormationService;
import jakarta.validation.Valid;
import jakarta.validation.groups.ConvertGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/public/Plan-formations")
@RequiredArgsConstructor
public class DetailsPlanFormationController
{
    private final IDetailsPlanFormationService dpfService;

    // Actions
    @PostMapping(value = "/actions/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ActionActivteDTO createAction(@Valid @ConvertGroup(to = CreateActionGroup.class) @RequestBody ActionActivteDTO dto)
    {
        return dpfService.createAction(dto);
    }

    @PutMapping(value = "/actions/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ActionActivteDTO updateAction(@Valid @ConvertGroup(to = UpdateActionGroup.class) @RequestBody ActionActivteDTO dto)
    {
        return dpfService.updateAction(dto);
    }

    // Activités
    @PostMapping(value = "/activites/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ActionActivteDTO createActivite(@Valid @ConvertGroup(to = CreateActiviteGroup.class) @RequestBody ActionActivteDTO dto)
    {
        return dpfService.createActivie(dto);
    }

    @PutMapping(value = "/activites/update", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ActionActivteDTO updateActivite(@Valid @ConvertGroup(to = UpdateActiviteGroup.class) @RequestBody ActionActivteDTO dto)
    {
        return dpfService.updateActivite(dto);
    }

    // Search
    @GetMapping(value = "/details/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public Page<VDetailsPlanFormation> search(
            @RequestParam(value = "key", required = false) String key,
            @RequestParam(value = "gesCode", required = false)Long gesCode,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size)
    {
        return dpfService.search(key, gesCode, PageRequest.of(page, size));
    }

    // get-dto
    @GetMapping(value = "/actions/get-dto/{gesCode}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ActionActivteDTO getActionDTO(@PathVariable Long gesCode)
    {
        return dpfService.getActionDTO(gesCode);
    }

    @GetMapping(value = "/activites/get-dto/{parentActionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ActionActivteDTO getActiviteDTO(@PathVariable Long parentActionId)
    {
        return dpfService.getActiviteDTO(parentActionId);
    }
}