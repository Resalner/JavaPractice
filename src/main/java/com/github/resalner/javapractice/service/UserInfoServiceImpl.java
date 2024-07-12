package com.github.resalner.javapractice.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.resalner.javapractice.exception.EntityNotFoundException;
import com.github.resalner.javapractice.model.UserInfo;
import com.github.resalner.javapractice.repository.UserInfoRepository;
import com.github.resalner.javapractice.request.UserInfoRequest;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserInfoServiceImpl {
    private final UserInfoRepository userInfoRepository;

    public List<UserInfo> getUsers() {
        return userInfoRepository.findAll();
    }

    public void addUserInfo(UserInfoRequest userInfoRequest) {
        UserInfo userInfo = new UserInfo();
        userInfo.setname(userInfoRequest.name());
        userInfo.setsurname(userInfoRequest.surname());
        userInfo.setphonenumber(userInfoRequest.phonenumber());
        userInfo.setbirthDate(userInfoRequest.birthDate());
        userInfo.setgender(userInfoRequest.gender());
        userInfo.setemail(userInfoRequest.email());
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

    public UserInfo updateUserInfo(long id, UserInfoRequest userInfoRequest) {
        UserInfo ui = userInfoRepository.findById(id)
                .orElseThrow(() -> EntityNotFoundException("не найден пользователь с id = " + id));
        if (Objects.nonNull(userInfoRequest.name())
                && !"".equals(userInfoRequest.name())) {

            ui.setname(userInfoRequest.name());
        }
        if (Objects.nonNull(userInfoRequest.surname())
                && !"".equals(userInfoRequest.surname())) {

            ui.setsurname(userInfoRequest.surname());
        }
        if (Objects.nonNull(userInfoRequest.phonenumber())
                && !"".equals(userInfoRequest.phonenumber())) {

            ui.setphonenumber(userInfoRequest.phonenumber());
        }
        if (Objects.nonNull(userInfoRequest.birthDate())
                && !"".equals(userInfoRequest.birthDate())) {

            ui.setbirthDate(userInfoRequest.birthDate());
        }
        if (Objects.nonNull(userInfoRequest.gender())
                && !"".equals(userInfoRequest.gender())) {

            ui.setgender(userInfoRequest.gender());
        }
        if (Objects.nonNull(userInfoRequest.email())
                && !"".equals(userInfoRequest.email())) {

            ui.setemail(userInfoRequest.email());
        }
        userInfoRepository.save(ui);
    }
}