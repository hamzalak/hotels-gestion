package test.rest;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import test.dto.MyContact;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/contact")

public class ContactRest {
	
	@Autowired
    private JavaMailSender sender;
	
	@PostMapping("/sendMail")
	public ResponseEntity<?> sendingMail(@RequestBody MyContact contact) throws MessagingException{
		
       MimeMessage message =  sender.createMimeMessage();

       MimeMessageHelper helper = new MimeMessageHelper( message);
        
        try {
           
        	helper.setTo("lakhal1hamza@tutanota.com");
            helper.setText(contact.getText());
            helper.setSubject("Mail de l' appilcation  gestion des hotels "+"de la part de " + contact.getEmail());
            
        } catch (MailException e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Error while sending mail ..", HttpStatus.CONFLICT);
        }
        sender.send(message);
        
        return new ResponseEntity<String>("message envoyé avec succée", HttpStatus.OK );
		
	}

}
