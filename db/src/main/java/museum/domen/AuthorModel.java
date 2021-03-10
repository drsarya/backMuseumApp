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
  public Integer id;
  @Column(name = "full_name")
  public String fullName;
}
