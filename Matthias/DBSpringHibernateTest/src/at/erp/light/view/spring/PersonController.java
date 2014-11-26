package at.erp.light.view.spring;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import at.erp.light.view.model.Address;
import at.erp.light.view.model.Person;
import at.erp.light.view.services.DataBaseService;
import at.erp.light.view.services.IDataBase;

@Controller
public class PersonController {

	@Autowired
	private IDataBase dataBaseService;
	
	@RequestMapping(value="/Person/all")
	public ModelAndView home() {
		List<Person> allPersons = dataBaseService.getAllPersons();
		ModelAndView model = new ModelAndView("PersonList");
		model.addObject("allPersons", allPersons);
		return model;
	}
	
	@RequestMapping(value="/Person/", params = {"id"})
	public ModelAndView personById( @RequestParam(value="id") int Id ){		
		List<Person> personList = new ArrayList<Person>();
		Person person = dataBaseService.getPersonById(Id);
		personList.add(person);
		ModelAndView model = new ModelAndView("PersonList");
		model.addObject("allPersons", personList);
		return model;
	}
	
	@RequestMapping(value="/Person/ID", params = {"id"}, method = RequestMethod.GET, headers="Accept=application/json")
	public @ResponseBody Person getPersonById( @RequestParam(value="id") int Id ){		
		Person p = dataBaseService.getPersonById(Id,Person.FETCH_ALL);
		
		Person mPerson = new Person(p.getPersonId(), p.getSalutation(), p.getTitle(), p.getFirstName(),
				p.getLastName(), p.getComment(), p.getUpdateTimestamp(), p.getActive());
		mPerson.setAddress(new Address(p.getAddress().getAddressId(), p.getAddress().getAddress()));
		
		return mPerson;
	}
	
	@RequestMapping(value="/Person/set/", params = {"id", "salutation", "title", "firstname", "lastname", "comment", "active"})
	public ModelAndView setPerson(
			@RequestParam(value="id") int id,
			@RequestParam(value="salutation") String salutation,
			@RequestParam(value="title") String title,
			@RequestParam(value="firstname") String firstname,
			@RequestParam(value="lastname") String lastname,
			@RequestParam(value="comment") String comment,
			@RequestParam(value="active") int active
			){		
		
		Person newPerson = new Person(id, salutation, title, firstname, lastname, comment, new Date(System.currentTimeMillis()), active);
		
		dataBaseService.setPerson(newPerson);
		
		List<Person> personList = dataBaseService.getAllPersons();
		ModelAndView model = new ModelAndView("PersonList");
		model.addObject("allPersons", personList);
		return model;
	}
	
}
