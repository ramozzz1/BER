package models;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import javax.persistence.Table;

import com.avaje.ebean.Page;
import com.avaje.ebean.annotation.CreatedTimestamp;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

@Entity
@Table(name="exchange_offices")
public class ExchangeOffice extends Model {

	private static final long serialVersionUID = 1L;

	@Id
	public Long id;
  
	@Required
	public String name;
	
	@Required
	public String address;
	
	@Required
	public String city;
	
	@Required
	public String country;
	
	public String phone;
	
	@Required
	public float longitude;
	
	@Required
	public float latitude;
	
	public int likes;
	
	public int dislikes;
	
	@OneToMany(mappedBy = "eo", cascade = CascadeType.ALL)
    public List<ExchangeRate> exchangeRates;
	
	@CreatedTimestamp
	public Timestamp created_on;
 
    @Version
    public Timestamp last_updated_on;
	
	public static Finder<Long,ExchangeOffice> find = new Finder<Long, ExchangeOffice>(Long.class, ExchangeOffice.class);
	
	public static List<ExchangeOffice> all() {
		return find.where().ne("id", -1).findList();
	}
	
	public static List<ExchangeOffice> search(int page, int limit, float lat, float lon) {
		return find.where().ne("id", -1).findList();
	}

	public static void create(ExchangeOffice eo) {
		eo.save();
	}

	public static ExchangeOffice get(Long id) {
		return find.byId(id);
	}
	
	public static void delete(Long id) {
		find.ref(id).delete();
	}

	public static Page<ExchangeOffice> page(int page, int pageSize, float lat, float lon) {
		return find.where()
                .orderBy("id DESC")
                .findPagingList(pageSize)
                .setFetchAhead(false)
                .getPage(page);
	}
}
