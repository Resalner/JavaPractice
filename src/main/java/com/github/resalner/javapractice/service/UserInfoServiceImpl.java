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

    public List<UserInfo> getUsers() {
        return userInfoRepository.findAll();
    }

    public void saveUserInfo(UserInfo userInfo) {
        userInfoRepository.save(userInfo);
    }

    public UserInfo getUserInfo(long id) {
        return userInfoRepository.findById(id)
                .orElseThrow(() -> EntityNotFoundException("не найден пользователь с id = " + id));
    }

    public void deleteUserInfo(long id) {
        userInfoRepository.deleteById(id)
                .orElseThrow(() -> EntityNotFoundException("не найден пользователь с id = " + id));
    }

    public UserInfo updateUserInfo(long id, UserInfo userInfo) {
        UserInfo ui = userInfoRepository.findById(id)
                .orElseThrow(() -> EntityNotFoundException("не найден пользователь с id = " + id));
        if (Objects.nonNull(userInfo.name())
                && !"".equals(userInfo.name())) {

            ui.setname(userInfo.name());
        }
        if (Objects.nonNull(userInfo.surname())
                && !"".equals(userInfo.surname())) {

            ui.setsurname(userInfo.surname());
        }
        if (Objects.nonNull(userInfo.phonenumber())
                && !"".equals(userInfo.phonenumber())) {

            ui.setphonenumber(userInfo.phonenumber());
        }
        if (Objects.nonNull(userInfo.birthDate())
                && !"".equals(userInfo.birthDate())) {

            ui.setbirthDate(userInfo.birthDate());
        }
        if (Objects.nonNull(userInfo.gender())
                && !"".equals(userInfo.gender())) {

            ui.setgender(userInfo.gender());
        }
        if (Objects.nonNull(userInfo.email())
                && !"".equals(userInfo.email())) {

            ui.setemail(userInfo.email());
        }
        userInfoRepository.save(ui);
        return ui;
    }
}