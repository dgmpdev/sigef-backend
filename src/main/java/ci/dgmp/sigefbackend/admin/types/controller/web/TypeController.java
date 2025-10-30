
package ci.dgmp.sigefbackend.admin.types.controller.web;

import jakarta.validation.Valid;
import jakarta.validation.groups.ConvertGroup;
import ci.dgmp.sigefbackend.admin.types.controller.services.ITypeService;
import ci.dgmp.sigefbackend.admin.types.model.dtos.TypeDTO;
import ci.dgmp.sigefbackend.admin.types.model.dtos.TypeGroupDTO;
import ci.dgmp.sigefbackend.admin.utilities.validatorgroups.CreateGroup;
import ci.dgmp.sigefbackend.admin.utilities.validatorgroups.SetSousTypeGroup;
import ci.dgmp.sigefbackend.admin.utilities.validatorgroups.UpdateGroup;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/types")
@RequiredArgsConstructor
public class TypeController
{

    private final ITypeService typeService;

    @PostMapping
    public TypeDTO createType(@Valid @ConvertGroup(to = CreateGroup.class) TypeDTO dto) {
        return typeService.createType(dto);
    }

    @PutMapping
    public TypeDTO updateType(@Valid @ConvertGroup(to = UpdateGroup.class) TypeDTO dto) {
        return typeService.updateType(dto);
    }

    @GetMapping("/search")
    public Page<TypeDTO> searchTypes(
            @RequestParam(value = "key", required = false) String key,
            @RequestParam(value = "groupCodes", required = false) List<String> groupCodes,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        return typeService.searchTypes(key, groupCodes, PageRequest.of(page, size));
    }

    @PostMapping("/groups")
    public TypeGroupDTO createTypeGroup(@Valid @ConvertGroup(to = CreateGroup.class) TypeGroupDTO dto) {
        return typeService.createTypeGroup(dto);
    }

    @PutMapping("/groups")
    public TypeGroupDTO updateTypeGroup(@Valid @ConvertGroup(to = UpdateGroup.class) TypeGroupDTO dto) {
        return typeService.updateTypeGroup(dto);
    }

    @GetMapping("/groups/search")
    public Page<TypeGroupDTO> searchTypeGroups(
            @RequestParam(value = "key", required = false) String key,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        return typeService.searchTypeGroups(key, PageRequest.of(page, size));
    }

    @GetMapping("/groups/list")
    public List<TypeGroupDTO> getAllTypeGroups() {
        return typeService.getAllTypeGroups();
    }

    @GetMapping("/direct-sous-types")
    public List<TypeDTO> getDirectSousTypes(@RequestParam("parentCode") String parentCode) {
        return typeService.getDirectSousTypes(parentCode);
    }

    @PostMapping("/set-sous-types")
    public ResponseEntity<Void> setSousTypes(@Valid @ConvertGroup(to = SetSousTypeGroup.class) TypeDTO dto) {
        typeService.setSousTypes(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/possible-sous-types")
    public List<TypeDTO> getPossibleSousTypes(@RequestParam("parentCode") String parentCode) {
        return typeService.getPossibleSousTypes(parentCode);
    }

    @GetMapping("/by-group/{groupCode}")
    public List<TypeDTO> getTypesByGroupCode(@PathVariable("groupCode") String groupCode) {
        return typeService.getTypesByGroupCode(groupCode);
    }
}
