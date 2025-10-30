package ci.dgmp.sigefbackend.admin.structures.controller.web;

import jakarta.annotation.security.RolesAllowed;
import ci.dgmp.sigefbackend.admin.structures.controller.service.IStrService;
import ci.dgmp.sigefbackend.admin.structures.model.dtos.ChangeAnchorDTO;
import ci.dgmp.sigefbackend.admin.structures.model.dtos.CreateOrUpdateStrDTO;
import ci.dgmp.sigefbackend.admin.structures.model.dtos.ReadStrDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @RequestMapping("/structures")
@RequiredArgsConstructor
public class StrController
{
    private final IStrService strService;

    @GetMapping("/search")
    @RolesAllowed("GET_STR")
    public Page<ReadStrDTO> search(@RequestParam("key") String key,
                                   @RequestParam("typeCode") String typeCode,
                                   @RequestParam("page") @DefaultValue("0") int page,
                                   @RequestParam("size") @DefaultValue("10") int size)
    {
        return strService.searchStrs(key, typeCode, PageRequest.of(page, size));
    }

    @PostMapping("/create")
    public ReadStrDTO createStr(CreateOrUpdateStrDTO dto)
    {
        return strService.createStr(dto);
    }

    @PutMapping("/update")
    public ReadStrDTO updateStr(CreateOrUpdateStrDTO dto)
    {
        return strService.updateStr(dto);
    }

    @PutMapping("/change-anchor")
    public ReadStrDTO changeAnchor(ChangeAnchorDTO dto)
    {
        return strService.changeAnchor(dto);
    }

    @GetMapping("/root-structures")
    public List<ReadStrDTO> getRootStructures()
    {
        return strService.getRootStructures();
    }

    @GetMapping("/possible-parents")
    public List<ReadStrDTO> getPossibleParentStructures(@RequestParam("childTypeCode") String childTypeCode)
    {
        return strService.getPossibleParentStructures(childTypeCode);
    }

    @GetMapping("/update-dto/{strId}")
    public CreateOrUpdateStrDTO getUpdateDto(@PathVariable("strId") Long strId)
    {
        return strService.getUpdateDto(strId);
    }

    @GetMapping("/change-anchor-dto/{strId}")
    public ChangeAnchorDTO getChangeAnchorDto(@PathVariable("strId") Long strId)
    {
        return strService.getChangeAnchorDto(strId);
    }

    /**
     * Returns all structures under supervision of the current user's profile structure.
     *
     * @return List of structures under supervision
     */
    @GetMapping("/user-visible-structures")
    @RolesAllowed("GET_STR")
    public List<ReadStrDTO> getUserVisibleStructures()
    {
        return strService.getUserVisibleStructures();
    }
}
