package af.cmr.indyli.akademiaws.jwtservice;

import af.cmr.indyli.akademia.business.dao.UserRepository;
import af.cmr.indyli.akademia.business.entity.User;
import af.cmr.indyli.akademia.business.utils.ConstsValues;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {
    @Resource(name = ConstsValues.ConstsDAO.USER_DAO_KEY)
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userDetail = repository.findUserByEmail(username);
        return userDetail.map(UserInfoDetails::new).orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
    }
}
