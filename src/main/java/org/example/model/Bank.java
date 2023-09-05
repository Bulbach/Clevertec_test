package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Bank implements Model {
    private Long id;
    private String brand;

    @Override
    public String toString() {
        return String.format("Bank ID = %-3d | brand = %s%n",
                id, brand);
    }
}
