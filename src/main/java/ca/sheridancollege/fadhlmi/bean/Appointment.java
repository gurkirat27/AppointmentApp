package ca.sheridancollege.fadhlmi.bean;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long id;
	private String name;
	private String email;
}