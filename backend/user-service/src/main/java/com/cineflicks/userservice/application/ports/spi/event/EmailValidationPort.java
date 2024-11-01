package com.cineflicks.userservice.application.ports.spi.event;

import com.cineflicks.userservice.domain.model.User;
import com.cineflicks.userservice.utils.EmailTemplateName;

public interface EmailValidationPort {
    void sendValidationEmail(User user);
}
