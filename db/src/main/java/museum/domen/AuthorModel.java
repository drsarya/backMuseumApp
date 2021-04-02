package museum.domen;


import lombok.Data;

import javax.persistence.*;

@Entity(name = "authors" )
@Table(uniqueConstraints = {
  @UniqueConstraint(columnNames = "full_name" )}
)
@Data
public class AuthorModel {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;
  @Column(name = "full_name")
  private String fullName;
}
