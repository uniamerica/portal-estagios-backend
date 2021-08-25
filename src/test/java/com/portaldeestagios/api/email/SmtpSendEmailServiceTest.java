package com.portaldeestagios.api.email;

import com.portaldeestagios.api.configs.EmailProperties;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.internet.MimeMessage;

import static com.portaldeestagios.api.email.SendEmailService.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SmtpSendEmailServiceTest {

        @InjectMocks
        private SmtpSendEmailService smtpSendEmailService;

        @Mock
        private JavaMailSender mailSender;

        @Mock
        private EmailProperties emailProperties;

        @Mock
        private MimeMessage mimeMessage;

        @Mock
        private MimeMessageHelper mimeMessageHelper;

        public Mensagem mensagem() {
                Mensagem mensagem = Mensagem.builder()
                        .destinatario("destinatario@gmail.com")
                        .corpo("teste de envio de email")
                        .assunto("email a ser enviado.")
                        .build();
                return mensagem;
        }

        public EmailProperties emailProperties() {
                EmailProperties emailProperties = EmailProperties.builder()
                        .remetente("remetente@gmail.com")
                        .build();
                return emailProperties;
        }

        @Test
        public void deveEnviarEmail(){
                when(mailSender.createMimeMessage()).thenReturn(mimeMessage);
                when(emailProperties.getRemetente()).thenReturn("remetente@gmail.com");
                doNothing().when(mailSender).send(mimeMessage);

                smtpSendEmailService.send(mensagem());

                verify(mailSender, times(1)).send(mimeMessage);
        }

        @Test
        public void naoDeveEnviarEmailQuandoORemetenteForVazio(){
                when(mailSender.createMimeMessage()).thenReturn(mimeMessage);
                when(emailProperties.getRemetente()).thenReturn("");

                Assertions.assertThrows(EmailException.class, () -> smtpSendEmailService.send(mensagem()));
        }
}
