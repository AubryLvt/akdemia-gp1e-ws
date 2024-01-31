package af.cmr.indyli.akademiaws.service;

import af.cmr.indyli.akademia.business.dao.UserRepository;
import af.cmr.indyli.akademia.business.dto.UserRegistrationDTO;
import af.cmr.indyli.akademia.business.dto.UserRegistrationResponseDTO;
import af.cmr.indyli.akademia.business.dto.basic.UserBasicDTO;
import af.cmr.indyli.akademia.business.dto.full.UserFullDTO;
import af.cmr.indyli.akademia.business.entity.User;
import af.cmr.indyli.akademia.business.exception.AkdemiaBusinessException;
import af.cmr.indyli.akademia.business.service.IAbstractAkdemiaService;

public interface IUserService extends IAbstractAkdemiaService<User, UserBasicDTO, UserFullDTO, UserRepository> {

    public UserRegistrationResponseDTO registerUser(UserRegistrationDTO dto) throws AkdemiaBusinessException;

    public boolean isUserExistByEmail(String email);

    public UserFullDTO findUserByEmail(String email) throws AkdemiaBusinessException;


    public UserFullDTO findUserById(Integer userId) throws AkdemiaBusinessException;

}
