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

    public List<Party> findAllWithCharacters() {
        return partyDAO.findAllWithCharacters();
    }
}
