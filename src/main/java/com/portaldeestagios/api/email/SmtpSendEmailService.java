package com.portaldeestagios.api.email;

import com.portaldeestagios.api.configs.EmailProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

@Service
public class SmtpSendEmailService implements SendEmailService {

  @Autowired
  private JavaMailSender mailSender;

  @Autowired
  private EmailProperties emailProperties;

  @Override
  public void send(Mensagem mensagem) {
    try {
      MimeMessage mimeMessage = mailSender.createMimeMessage();

      MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

      helper.setFrom(emailProperties.getRemetente());
      helper.setTo(mensagem.getDestinatarios().toArray(new String[0]));
      helper.setSubject(mensagem.getAssunto());
      helper.setText(mensagem.getCorpo(), true);
      mailSender.send(mimeMessage);

    } catch (Exception e) {
      throw new EmailException("Não foi possível enviar e-mail", e);
    }
  }
}