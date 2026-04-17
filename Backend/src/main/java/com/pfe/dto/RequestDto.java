package com.pfe.dto;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

import com.pfe.model.Announcement;
import com.pfe.model.ImageModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestDto {
	
	    private Integer id ;
	    private LocalDate  dateCreation;  
	    private String nom;
	    private String prenom ; 
	    private String cin ; 
	    private String adresse ; 
	    private String status ;
     	private String candidateId;
     	private Set<ImageModel> document ;
	    private Announcement announcement;
}
