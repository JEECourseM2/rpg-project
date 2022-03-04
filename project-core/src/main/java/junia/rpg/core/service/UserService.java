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

    public void delete(User user) { userDAO.delete(user); }

    public void deleteById(Long id) { userDAO.deleteById(id); }

    public void save(User user){
        userDAO.save(user);
    }

    public User findById(long userId) { return userDAO.findById(userId); }

    public User findByName(String username){
        return userDAO.findByName(username);
    }

    public List<User> findAll() { return  userDAO.findAll(); }

    public List<User> findAllWithCharacterSheets() { return  userDAO.findAllWithCharacterSheets(); }

    public User updateUser(long id) { return userDAO.findById(id); }

}
