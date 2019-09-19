package ravi.labs.springbootreactaws.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@Table( name = "PERSON",
//        uniqueConstraints = { @UniqueConstraint( columnNames = { "FIRST_NAME", "LAST_NAME" } ) } )
public class Person extends BaseEntity{

    private String firstName;
    private String lastName;

    @JoinColumn(name="PERSON_ID")
    @OneToMany(cascade = CascadeType.ALL)
    private Set<Address> addresses;

    public String getFullName(){
        return this.firstName + "," + this.lastName;
    }
}
