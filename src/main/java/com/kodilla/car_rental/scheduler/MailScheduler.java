package com.kodilla.car_rental.scheduler;

import com.kodilla.car_rental.config.AdminConfig;
import com.kodilla.car_rental.domain.Mail;
import com.kodilla.car_rental.service.CreateDailyMailMessageService;
import com.kodilla.car_rental.service.MailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailScheduler {
    private static final String SUBJECT = "Car rental: Your daily email!";

    private AdminConfig adminConfig;
    private MailSenderService mailSenderService;
    private CreateDailyMailMessageService createDailyMailMessageService;

    //@Scheduled(cron = "*/30 * * * * *")
    @Scheduled(cron = "0 0 6 * * *")
    public void sendDailyEmail() {
        mailSenderService.send(new Mail(
                adminConfig.getAdminMail(),
                SUBJECT,
                createDailyMailMessageService.emailBodyCreate()));
    }
}
