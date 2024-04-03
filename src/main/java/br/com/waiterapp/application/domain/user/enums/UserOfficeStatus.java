package br.com.waiterapp.application.domain.user.enums;

import lombok.Getter;

@Getter
public enum UserOfficeStatus {

    WAITER(0),
    ADMIN(1);
    private final int currentStatus;

    UserOfficeStatus(int value) {
        this.currentStatus = value;
    }
}
