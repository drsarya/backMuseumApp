package museum.domen;


import lombok.Data;

import javax.persistence.*;

@Entity(name = "exhibitions" )
@Data
public class ExhibitionModel {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  public Integer id;

  public String name;

  public String imageUrl;

  public String description;

  public String firstDate;

  public String lastDate;
  @ManyToOne (optional=false, cascade= CascadeType.ALL)
  @JoinColumn(name="museum_id")
  public MuseumModel museum;

}
