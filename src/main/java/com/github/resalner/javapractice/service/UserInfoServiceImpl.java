package com.github.resalner.javapractice.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.resalner.javapractice.exception.EntityNotFoundException;
import com.github.resalner.javapractice.model.UserInfo;
import com.github.resalner.javapractice.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl implement UserInfoService {
    private final UserInfoRepository userInfoRepository;

    @Ovveride
    public List<UserInfo> getUsers() {
        return userInfoRepository.findAll();
    }

    @Ovveride
    public UserInfo saveUserInfo(UserInfo userInfo) {
        userInfo = userInfoRepository.save(userInfo);
        return userInfo;
    }

    @Ovveride
    public UserInfo getUserInfo(long id) {
        return userInfoRepository.findById(id)
                .orElseThrow(() -> EntityNotFoundException("не найден пользователь с id = " + id));
    }

    @Ovveride
    public void deleteUserInfo(long id) {
        userInfoRepository.deleteById(id)
                .orElseThrow(() -> EntityNotFoundException("не найден пользователь с id = " + id));
    }

    @Ovveride
    public UserInfo updateUserInfo(long id, UserInfo userInfoForUpdate) {
        UserInfo userInfo = userInfoRepository.findById(id)
                .orElseThrow(() -> EntityNotFoundException("не найден пользователь с id = " + id));
        if (Objects.nonNull(userInfoForUpdate.name())
                && !"".equals(userInfoForUpdate.name())) {

            userInfo.setname(userInfoForUpdate.name());
        }
        if (Objects.nonNull(userInfoForUpdate.surname())
                && !"".equals(userInfoForUpdate.surname())) {

            userInfo.setsurname(userInfoForUpdate.surname());
        }
        if (Objects.nonNull(userInfoForUpdate.phonenumber())
                && !"".equals(userInfoForUpdate.phonenumber())) {

            userInfo.setphonenumber(userInfoForUpdate.phonenumber());
        }
        if (Objects.nonNull(userInfoForUpdate.birthDate())
                && !"".equals(userInfoForUpdate.birthDate())) {

            userInfo.setbirthDate(userInfoForUpdate.birthDate());
        }
        if (Objects.nonNull(userInfoForUpdate.gender())
                && !"".equals(userInfoForUpdate.gender())) {

            userInfo.setgender(userInfoForUpdate.gender());
        }
        if (Objects.nonNull(userInfoForUpdate.email())
                && !"".equals(userInfoForUpdate.email())) {

            userInfo.setemail(userInfoForUpdate.email());
        }
        userInfoRepository.save(userInfo);
        return userInfo;
    }
}