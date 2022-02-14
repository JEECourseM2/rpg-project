package junia.rpg.web.controller;

import junia.rpg.core.entity.Party;
import junia.rpg.core.service.PartyService;
import junia.rpg.web.dto.PartyDTO;
import org.springframework.http.MediaType;

import javax.inject.Named;
import javax.ws.rs.*;
import java.awt.print.Book;
import java.util.Map;
import java.util.stream.Collectors;

@Named
@Path("/parties")
@Produces(MediaType.APPLICATION_JSON_VALUE)
@Consumes(MediaType.APPLICATION_JSON_VALUE)
public class PartyAPIController implements RestController{

    private PartyService partyService;

    public PartyAPIController(PartyService partyService) {
        this.partyService = partyService;
    }

    @GET
    @Path("/")
    public Map<Long,String> getAllParties(){
        return partyService.findAll().stream().collect(Collectors.toMap(Party::getId, Party::getName));
    }



}
