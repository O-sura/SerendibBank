package com.banking.observer;

import com.banking.domain.User;

public interface UserStatusObserver {
    void update(User user, String oldStatus, String newStatus);
}
