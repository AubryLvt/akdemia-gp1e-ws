package af.cmr.indyli.akademiaws.service.impl;

import af.cmr.indyli.akademia.business.dao.ManagerRepository;
import af.cmr.indyli.akademia.business.dao.PrivilegeRepository;
import af.cmr.indyli.akademia.business.dao.RoleRepository;
import af.cmr.indyli.akademia.business.dao.UserRepository;
import af.cmr.indyli.akademia.business.dto.UserRegistrationDTO;
import af.cmr.indyli.akademia.business.dto.UserRegistrationResponseDTO;
import af.cmr.indyli.akademia.business.dto.basic.UserBasicDTO;
import af.cmr.indyli.akademia.business.dto.full.UserFullDTO;
import af.cmr.indyli.akademia.business.entity.Manager;
import af.cmr.indyli.akademia.business.entity.Privilege;
import af.cmr.indyli.akademia.business.entity.Role;
import af.cmr.indyli.akademia.business.entity.User;
import af.cmr.indyli.akademia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akademia.business.service.impl.AbstractAkdemiaServiceImpl;
import af.cmr.indyli.akademia.business.utils.ConstsValues;
import af.cmr.indyli.akademiaws.service.IUserService;
import jakarta.annotation.Resource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service(value = ConstsValues.ServiceKeys.USER_SERVICE)
public class UserServiceImpl extends AbstractAkdemiaServiceImpl<User, UserBasicDTO, UserFullDTO, UserRepository> implements IUserService {

    @Resource(name = ConstsValues.ConstsDAO.USER_DAO_KEY)
    private UserRepository userRepository;

    @Resource(name = ConstsValues.ConstsDAO.MANAGER_DAO_KEY)
    private ManagerRepository managerRepository;

    @Resource(name = ConstsValues.ConstsDAO.ROLE_DAO_KEY)
    private RoleRepository roleRepository;

    @Resource(name = ConstsValues.ConstsDAO.PRIVILEGE_DAO_KEY)
    private PrivilegeRepository privilegeRepository;

    @Resource(name = "BCRYPT_ENCODER")
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl() {
        super(User.class, UserBasicDTO.class, UserFullDTO.class);
    }

    @Override
    public UserRepository getDAO() {
        return userRepository;
    }

    @Override
    public UserRegistrationResponseDTO registerUser(UserRegistrationDTO dto) throws AkdemiaBusinessException {
        if (isUserExistByEmail(dto.getEmail())) {
            throw new AkdemiaBusinessException("Un utilisateur existe déjà avec cet email " + dto.getEmail());
        }
        List<Privilege> privileges = new ArrayList<>();
        Role role = roleRepository.findByRoleName(dto.getRoleName());

        Manager manager = new Manager();
        manager.setFirstname(dto.getFirstName());
        manager.setLastname(dto.getLastName());
        manager.setGender(dto.getGender());
        manager.setPhoto(dto.getPhoto());
        manager.setEmail(dto.getEmail());
        manager.setAddress(dto.getAddress());
        manager.setPhone(dto.getPhone());
        manager.setCreationDate(new Date());
        manager.setPassword(passwordEncoder.encode(dto.getPassword()));
        managerRepository.save(manager);

        Privilege privilege = new Privilege();
        privilege.setUser(this.getDAO().findUserByEmail(dto.getEmail()).get());
        privilege.setRole(role);
        privilege.setCreationDate(new Date());

        privilegeRepository.save(privilege);

        return new UserRegistrationResponseDTO(this.getDAO().findUserByEmail(dto.getEmail()).get().getId(), dto.getEmail());
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        return userRepository.findUserByEmail(email).isPresent();
    }

    @Override
    public UserFullDTO findUserByEmail(String email) throws AkdemiaBusinessException {

        Optional<User> user = this.getDAO().findUserByEmail(email);
        if(user.isEmpty())
            throw new AkdemiaBusinessException("Aucun utilisateur ne correspond a cet email");

        return this.getModelMapper().map(user, UserFullDTO.class);
    }

    @Override
    public UserFullDTO findUserById(Integer userId) throws AkdemiaBusinessException {
        Optional<User> user = this.getDAO().findById(userId);
        if(user.isEmpty())
            throw new AkdemiaBusinessException("Aucun utilisateur ne correspond a cet identifiant");

        if(user.get() instanceof Manager)
            return this.getModelMapper().map(this.managerRepository.findById(userId), UserFullDTO.class);


        return this.getModelMapper().map(user, UserFullDTO.class);
    }
}
