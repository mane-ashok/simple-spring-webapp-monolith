package org.ashok.dto;

import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchInvoiceDto
{
    //@Pattern(regexp = "jan|feb|mar|apr|may|jun|jul|aug|sep|oct|nov|dec|all",message = "Invalid month")
	private String month;
    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;
    
   private List<String> monthOptions = List.of("jan","feb","mar","apr","may","jun","jul","aug","sep","oct","nov","dec");
      
}
