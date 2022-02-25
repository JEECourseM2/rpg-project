package junia.rpg.web.controller;

import junia.rpg.core.entity.Spell;
import junia.rpg.core.service.SpellService;
import junia.rpg.web.dto.SpellDTO;
import junia.rpg.web.utils.MappingUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

import javax.inject.Named;
import javax.ws.rs.*;
import java.util.*;
import java.util.stream.Collectors;

@Named
@Path("/spells")
@Produces(MediaType.APPLICATION_JSON_VALUE)
@Consumes(MediaType.APPLICATION_JSON_VALUE)
public class SpellAPIController implements RestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpellAPIController.class);

    private SpellService spellService;

    public SpellAPIController(SpellService spellService) {
        this.spellService = spellService;
    }

    @GET
    @Path("/")
    public List<String> getAllSpells() {
        LOGGER.info("List all spells name");
        return spellService.findAll().stream().map(Spell::getName).collect(Collectors.toList());
    }

    @GET
    @Path("/{name}=name")
    public List<SpellDTO> getSpellsByName(@PathParam("name") String name) {
        LOGGER.info("List all spells with given name");
        List<Spell> spells = spellService.findAllByName(name);
        List<SpellDTO> spellsDTO = new ArrayList<>();
        for (Spell spell : spells) {
            spellsDTO.add(MappingUtils.mapSpellToDTO(spellService.findById(spell.getId())));
        }
        return spellsDTO;
    }

    @GET
    @Path("/{type}=type")
    public List<SpellDTO> getSpellsByType(@PathParam("type") String type) {
        LOGGER.info("List all spells with given type");
        List<Spell> spells = spellService.findAllByType(type);
        List<SpellDTO> spellsDTO = new ArrayList<>();
        for (Spell spell : spells) {
            spellsDTO.add(MappingUtils.mapSpellToDTO(spellService.findById(spell.getId())));
        }
        return spellsDTO;
    }

    @GET
    @Path("/{level}=level")
    public List<SpellDTO> getSpellsByLevel(@PathParam("level") int level) {
        LOGGER.info("List all spells with given level");
        List<Spell> spells = spellService.findAllByLevel(level);
        List<SpellDTO> spellsDTO = new ArrayList<>();
        for (Spell spell : spells) {
            spellsDTO.add(MappingUtils.mapSpellToDTO(spellService.findById(spell.getId())));
        }
        return spellsDTO;
    }

    @GET
    @Path("/{name}=name/{limit}=limit")
    public List<SpellDTO> getSpellsByNameWithLimit(@PathParam("name") String name, @PathParam("limit") int limit) {
        LOGGER.info("List within a given limit random spells with given name");
        List<SpellDTO> spells = getSpellsByName(name);
        Collections.shuffle(spells);
        spells.subList(limit, spells.size()).clear();
        return spells;
    }

    @GET
    @Path("/{type}=type/{limit}=limit")
    public List<SpellDTO> getSpellsByTypeWithLimit(@PathParam("type") String type, @PathParam("limit") int limit) {
        LOGGER.info("List within a given limit random spells with given type");
        List<SpellDTO> spells = getSpellsByType(type);
        Collections.shuffle(spells);
        spells.subList(limit, spells.size()).clear();
        return spells;
    }

    @GET
    @Path("/{level}=level/{limit}=limit")
    public List<SpellDTO> getSpellsByLevelWithLimit(@PathParam("level") int level, @PathParam("limit") int limit) {
        LOGGER.info("List within a given limit random spells with given level");
        List<SpellDTO> spells = getSpellsByLevel(level);
        Collections.shuffle(spells);
        spells.subList(limit, spells.size()).clear();
        return spells;
    }
}
