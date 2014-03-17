package models;

import java.math.BigDecimal;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import play.data.validation.Constraints.Required;
import play.db.ebean.Model;

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

}
