package models;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;
import play.db.ebean.Model.Finder;

import com.avaje.ebean.annotation.CreatedTimestamp;

@Entity
@Table(name="exchange_rates")
public class ExchangeRate extends Model {

	private static final long serialVersionUID = 1L;
	
	@Id
	public Long id;
  
	@Required
	public String currFrom;
	
	@Required
	public String currTo;
	
	@Required
	@Column(precision = 38, scale = 2)
	public BigDecimal amount;
	
	@ManyToOne
	public ExchangeOffice eo; 
	
	@CreatedTimestamp
	public Timestamp created_on;
 
    @Version
    public Timestamp last_updated_on;
    
    public static Finder<Long,ExchangeRate> find = new Finder<Long, ExchangeRate>(Long.class, ExchangeRate.class);

    public static BigDecimal getExchangeRateAmount(long eoId, String currencyFrom, String currencyTo) {
    	ExchangeRate er = find.where()
				    		.eq("currFrom", currencyFrom)
				    		.eq("currTo", currencyTo)
				    		.eq("eo_id", eoId)
				    		.findUnique();
    	if(er!=null)
    		return er.amount;
    	else
    		return new BigDecimal(-1);
    }
    
    public static void create(ExchangeRate er) {
		er.save();
	}
    
    public static List<String> currencies() {
    	List currencies = new ArrayList<String>();
    	currencies.add("EUR");
    	currencies.add("USD");
    	currencies.add("GBP");
    	return currencies;
    }
}
