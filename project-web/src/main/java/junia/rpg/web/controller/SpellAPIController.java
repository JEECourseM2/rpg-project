package junia.rpg.web.controller;

import junia.rpg.core.entity.Spell;
import junia.rpg.core.service.SpellService;
import junia.rpg.web.dto.SpellDTO;
import junia.rpg.web.utils.MappingUtils;
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

    private SpellService spellService;

    public SpellAPIController(SpellService spellService) {
        this.spellService = spellService;
    }

    @GET
    @Path("/")
    public Map<Long, String> getAllSpells() {
        return spellService.findAll().stream().collect(Collectors.toMap(Spell::getId, Spell::getName));
    }

    @GET
    @Path("/{name}=name")
    public List<SpellDTO> getSpellsByName(@PathParam("name") String name) {
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
        List<SpellDTO> spells = getSpellsByName(name);
        Collections.shuffle(spells);
        spells.subList(limit, spells.size()).clear();
        return spells;
    }

    @GET
    @Path("/{type}=type/{limit}=limit")
    public List<SpellDTO> getSpellsByTypeWithLimit(@PathParam("type") String type, @PathParam("limit") int limit) {
        List<SpellDTO> spells = getSpellsByType(type);
        Collections.shuffle(spells);
        spells.subList(limit, spells.size()).clear();
        return spells;
    }

    @GET
    @Path("/{level}=level/{limit}=limit")
    public List<SpellDTO> getSpellsByLevelWithLimit(@PathParam("level") int level, @PathParam("limit") int limit) {
        List<SpellDTO> spells = getSpellsByLevel(level);
        Collections.shuffle(spells);
        spells.subList(limit, spells.size()).clear();
        return spells;
    }
}
