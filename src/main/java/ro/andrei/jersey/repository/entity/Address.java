package ro.andrei.jersey.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Address {
    @Id
    @SequenceGenerator(name = "SEQ",sequenceName = "idseq",allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "SEQ")
    private Long id;
    @Column
    private String address;
}
