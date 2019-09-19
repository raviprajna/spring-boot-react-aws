package ravi.labs.springbootreactaws.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ravi.labs.springbootreactaws.enums.AddressType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Address extends BaseEntity {

    private String city;
    private String pinCode;
    private String streetName;
    @Enumerated(EnumType.STRING)
    private AddressType addressType;

}
