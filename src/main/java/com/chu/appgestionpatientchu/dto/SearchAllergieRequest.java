package com.chu.appgestionpatientchu.dto;

import com.chu.appgestionpatientchu.exceptions.InvalidSearchCriteriaException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Optional;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class SearchAllergieRequest {
    private Optional<String> name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Optional<LocalDate> creationDate;

    public void validate() {
        if (!name.isPresent() && !creationDate.isPresent()) {
            throw new InvalidSearchCriteriaException("At least one search criterion (name or creation date) must be provided.");
        }
    }
}
