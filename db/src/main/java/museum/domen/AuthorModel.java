package museum.domen;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity(name = "authors")
@Table(uniqueConstraints = {
  @UniqueConstraint(columnNames = "full_name")}
)
@Data
public class AuthorModel {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(name = "full_name")
  private String fullName;

}
