package junia.rpg.core.service;

import junia.rpg.core.dao.PartyDAO;
import junia.rpg.core.entity.Party;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PartyService {
    private PartyDAO partyDAO;

    public PartyService(final PartyDAO partyDAO) {
        this.partyDAO = partyDAO;
    }

    public Party save(Party party) { return partyDAO.save(party); }

    public Party findById(long id) { return partyDAO.findById(id); }

    public Party findByName(final String name) {
        return partyDAO.findByName(name);
    }

    public List<Party> findAll() {
        return partyDAO.findAll();
    }

    public List<Party> findAllWithCharacterSheets() {
        return partyDAO.findAllWithCharacterSheets();
    }

    public Party findOneByIdWithCharacterSheets(long partyId) { return partyDAO.findOneByIdWithCharacterSheets(partyId); }

    public List<Party> findUserPartiesWithCharacterSheetsAndUser(long userId) { return partyDAO.findUserPartiesWithCharacterSheetsAndUser(userId); }

    public List<Party> findUserPartiesWithCharacterSheetsAndUser(String userName) { return partyDAO.findUserPartiesWithCharacterSheetsAndUser(userName); }

    public List<Party> findUserPartiesAsPCWithCharacterSheets(String userName) { return partyDAO.findUserPartiesAsPCWithCharacterSheets(userName); }

    public List<Party> findPartiesById(long userId){
        return partyDAO.findPartiesById(userId);
    }

    public List<Party> findPartiesByGmUserId(long gmUserId){
        return partyDAO.findPartiesByGmUserId(gmUserId);
    }
}
