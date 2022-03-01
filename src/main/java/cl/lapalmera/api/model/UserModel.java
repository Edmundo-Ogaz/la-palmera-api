package cl.lapalmera.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name="USER")
@DiscriminatorValue("USER")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserModel implements Serializable {

    @Column(name="ID", nullable=false, unique=true)
    private long id;

    @Column(name="PASSWORD", nullable=false)
    private String password;

    @Column(name="FIRST_NAME", nullable=false)
    private String firstName;

    @Column(name="LAST_NAME", nullable=false)
    private String lastName;

    @Column(name="EMAIL", nullable=false, unique=true)
    private String email;


}
