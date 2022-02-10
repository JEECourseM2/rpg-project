package junia.rpg.core.service;

import junia.rpg.core.dao.UserDAO;
import junia.rpg.core.entity.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService{
    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void deleteAll(){
        userDAO.deleteAll();
    }

    public void save(User user){
        userDAO.save(user);
    }

    public User findByName(String username){
        return userDAO.findByName(username);
    }

    public List<User> findAll() { return  userDAO.findAll(); }

    public List<User> findAllWithCharacters() { return  userDAO.findAllWithCharacters(); }

}
