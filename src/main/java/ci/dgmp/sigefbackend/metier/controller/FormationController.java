package ci.dgmp.sigefbackend.metier.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/formations")
@RequiredArgsConstructor
@ResponseStatus(HttpStatus.OK)
public class FormationController {
}
