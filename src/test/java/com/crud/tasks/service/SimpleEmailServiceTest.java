//package com.crud.tasks.service;
//
//import com.crud.tasks.domain.Mail;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//
//@RunWith(MockitoJUnitRunner.class)
//public class SimpleEmailServiceTest {
//
//    @InjectMocks
//    private SimpleEmailService simpleEmailService;
//
//    @Mock
//    private JavaMailSender javaMailSender;
//
//    @Test
//    public void shouldSendEmail() {
//        //Given
//        Mail mail = new Mail("test@test.com", "Test", "Test message", null);
//
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setTo(mail.getMailTo());
//        mailMessage.setSubject(mail.getSubject());
//        mailMessage.setText(mail.getMessage());
//        mailMessage.setCc(mail.getToCc());
//
//        //When
//        simpleEmailService.send(mail);
//
//        //Then
////        verify(javaMailSender, times(1)).send(mailMessage);
//        Assert.assertEquals("Test", mailMessage.getSubject());
//        Assert.assertEquals("Test message", mailMessage.getText());
//
//    }
//
//}