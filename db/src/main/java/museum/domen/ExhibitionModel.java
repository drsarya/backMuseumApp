package museum.domen;


import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;

@Entity(name = "exhibitions" )
@Data
public class ExhibitionModel {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  private String name;

  private String imageUrl;

  private String description;

  private String firstDate;

  private String lastDate;
  @ManyToOne
  @OnDelete(action= OnDeleteAction.NO_ACTION)
  @JoinColumn(name="museum_id")
  private MuseumModel museum;

  @OneToMany(mappedBy = "exhibition" , orphanRemoval = true )
  @OnDelete(action= OnDeleteAction.CASCADE)
  private List<ExhibitModel> exhibitModels;

}
