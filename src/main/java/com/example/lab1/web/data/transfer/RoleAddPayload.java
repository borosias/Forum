package com.example.lab1.web.data.transfer;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * A DTO for the {@link com.example.lab1.domain.Role} entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleAddPayload {
    @NotNull @Size(min = 3, max = 255) @Pattern(regexp = "^\\S+$") String name;
    @Min(1) Integer rank = 1;
}
