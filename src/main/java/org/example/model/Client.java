package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Client implements Model{
    private Long id;
    private String personalIdentifier;
    private String first_name;
    private String surname;

    @Override
    public String toString() {
        return String.format("Client ID = %-3d | personalIdentifier = %-10s | first_name = %-10s | surname =  %s%n",
                id, personalIdentifier, first_name, surname);
    }
}
