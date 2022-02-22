package junia.rpg.web.controller;

import junia.rpg.core.entity.Spell;
import junia.rpg.core.service.SpellService;
import org.springframework.http.MediaType;

import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Map;
import java.util.stream.Collectors;

@Named
@Path("/spells")
@Produces(MediaType.APPLICATION_JSON_VALUE)
@Consumes(MediaType.APPLICATION_JSON_VALUE)
public class SpellAPIController implements RestController {

    private SpellService spellService;

    public SpellAPIController(SpellService spellService) {
        this.spellService = spellService;
    }

    @GET
    @Path("/")
    public Map<Long, String> getAllSpells() {
        return spellService.findAll().stream().collect(Collectors.toMap(Spell::getId, Spell::getName));
    }

}
