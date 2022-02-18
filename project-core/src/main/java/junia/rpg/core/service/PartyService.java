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

    public Party findByName(final String name) {
        return partyDAO.findByName(name);
    }

    public List<Party> findAllWithCharacterSheets() {
        return partyDAO.findAllWithCharacterSheets();
    }

    public Party findOneByIdWithCharacterSheets(long partyId) { return partyDAO.findOneByIdWithCharacterSheets(partyId); }

    public List<Party> findAll() {
        return partyDAO.findAll();
    }

    public List<Party> findUserPartiesWithCharacterSheetsAndUser(long userId) { return partyDAO.findUserPartiesWithCharacterSheetsAndUser(userId); }

    public List<Party> findUserPartiesWithCharacterSheetsAndUser(String userName) { return partyDAO.findUserPartiesWithCharacterSheetsAndUser(userName); }

}
