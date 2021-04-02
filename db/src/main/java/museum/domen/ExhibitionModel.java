package museum.domen;


import lombok.Data;

import javax.persistence.*;

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
  @ManyToOne (cascade = CascadeType.ALL)
  @JoinColumn(name="museum_id")
  private MuseumModel museum;

}
