package junia.rpg.web.utils;

import junia.rpg.core.entity.Party;
import junia.rpg.core.service.UserService;
import junia.rpg.web.dto.PartyDTO;

import java.util.ArrayList;
import java.util.Arrays;

public final class MappingUtils {

    public static PartyDTO mapPartyToDTO(Party party, UserService userService) {
        PartyDTO dto = new PartyDTO();
        dto.setId(party.getId());
        dto.setName(party.getName());
        dto.setSessionNumber(party.getSessionNumber());
        dto.setPC1(party.getPC1());
        dto.setPC2(party.getPC2());
        dto.setPC3(party.getPC3());
        dto.setPC4(party.getPC4());
        dto.setCharacterSheets(party.getCharacterSheets());
        dto.setPcUsers(new ArrayList<>(Arrays.asList(
                userService.findByName(party.getPC1()),
                userService.findByName(party.getPC2()),
                userService.findByName(party.getPC3()),
                userService.findByName(party.getPC4()))));
        dto.setGmUser(party.getGmUser());
        return dto;
    }

}
