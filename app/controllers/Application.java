package controllers;

import models.ExchangeOffice;
import models.ExchangeRate;
import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import views.html.*;

public class Application extends Controller {

    public static Result index() {
    	return redirect(routes.Application.eos());
    }
    
    public static Result eos() {
    	return ok(views.html.list.render(ExchangeOffice.all()));
    }
  
    public static Result createEO() {
    	Form<ExchangeOffice> eoForm = form(ExchangeOffice.class);
        return ok(
        		createEO.render(eoForm)
        );  
    }
    
    public static Result createER(Long eoId) {
    	Form<ExchangeRate> erForm = form(ExchangeRate.class);
        return ok(
        		addER.render(erForm, eoId)
        );  
    }
    
    
    public static Result detailsEO(Long id) {
    	ExchangeOffice eo = ExchangeOffice.get(id);
    	if(eo==null) {
    		return badRequest();
    	}
    	
    	return ok(detailsEO.render(eo));  
    }
    
    public static Result saveEO() {
    	Form<ExchangeOffice> eoForm = form(ExchangeOffice.class).bindFromRequest();
        if(eoForm.hasErrors()) {
            return badRequest(createEO.render(eoForm));
        }
        ExchangeOffice.create(eoForm.get());
    	return redirect(routes.Application.eos());  
    }
  
    public static Result deleteEO(Long id) {
    	ExchangeOffice.delete(id);
    	return redirect(routes.Application.eos());
    }
    
    public static Result saveER(Long id) {
    	Form<ExchangeRate> erForm = form(ExchangeRate.class).bindFromRequest();
        if(erForm.hasErrors()) {
            return badRequest(addER.render(erForm, id));
        }
        ExchangeRate.create(erForm.get());
    	return redirect(routes.Application.detailsEO(id));  
    }
    
    
}
