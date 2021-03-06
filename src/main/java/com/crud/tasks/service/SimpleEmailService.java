package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;


@Service
public class SimpleEmailService {


    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleEmailService.class);

    @Autowired
    private MailCreatorService mailCreatorService;

    @Autowired
    private JavaMailSender javaMailSender;

    public void send(final Mail mail) {
        LOGGER.info("Starting email preparation");
        try {
            javaMailSender.send(createMimeMessage(mail));
            LOGGER.info("Email has been sent");
        } catch (MailSendException e) {
            LOGGER.info("Failed to process email sending ", e.getMessage(), e);
        }
    }

    public void sendDaily(final Mail mail) {
        LOGGER.info("Starting email preparation");
        try {
            javaMailSender.send(createMimeMessageDaily(mail));
            LOGGER.info("Email has been sent");
        } catch (MailSendException e) {
            LOGGER.info("Failed to process email sending ", e.getMessage(), e);
        }
    }

    private MimeMessagePreparator createMimeMessage(Mail mail){
        return mimMessage-> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mailCreatorService.buildTrelloCardEmail(mail.getMessage()), true);
        };
    }

    private MimeMessagePreparator createMimeMessageDaily(Mail mail){
        return mimMessage-> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            messageHelper.setText(mailCreatorService.buildTrelloDailyEmail(mail.getMessage()), true);
        };
    }

//    private SimpleMailMessage createMailMessage(final Mail mail){
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setText(mail.getMailTo());
//        mailMessage.setSubject(mail.getSubject());
//        mailMessage.setText(mailCreatorService.buildTrelloCardEmail(mail.getMessage()));
//        return mailMessage;
//    }
}
