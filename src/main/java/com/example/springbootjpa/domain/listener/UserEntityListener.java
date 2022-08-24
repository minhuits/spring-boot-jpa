package com.example.springbootjpa.domain.listener;

import com.example.springbootjpa.domain.User;
import com.example.springbootjpa.domain.UserHistory;
import com.example.springbootjpa.repository.UserHistoryRepository;
import com.example.springbootjpa.support.BeanUtils;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;

public class UserEntityListener {

    @PostPersist
    @PostUpdate
    public void prePersistAndPreUpdate(Object object) {
        User user = (User) object;
        UserHistoryRepository userHistoryRepository = BeanUtils.getBean(UserHistoryRepository.class);

        UserHistory userHistory = new UserHistory();
        userHistory.setUserId(user.getId());
        userHistory.setName(user.getName());
        userHistory.setEmail(user.getEmail());

        userHistoryRepository.save(userHistory);
    }
}
