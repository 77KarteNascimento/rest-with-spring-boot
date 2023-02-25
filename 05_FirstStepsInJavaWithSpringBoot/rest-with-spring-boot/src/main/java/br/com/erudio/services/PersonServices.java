package br.com.erudio.services;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.erudio.exceptions.ResourceNotFoundException;
import br.com.erudio.model.Person;
import br.com.erudio.repositories.PersonRepository;

@Service
public class PersonServices {
	
	
	private Logger logger = Logger.getLogger(PersonServices.class.getName());
	
	@Autowired
	private PersonRepository personRepository;
	
	public List<Person> findAll() {
		
		logger.info("Finding all people");
		
		return personRepository.findAll() ;
	}
	
		

	public Person findById(Long id) {
		return personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID")); // or Else throw e usado se a busca pelo nosso id não retornar nada
	}
		
		
		public Person create(Person person) {
			
			logger.info("Create one person!");
			
			return personRepository.save(person);
		}
		
		
		public Person update(Person person) {
			
			logger.info("Updating one person!");
			
			Person entity = personRepository.findById(person.getId())
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
			
			entity.setFirstName(person.getFirstName());
			entity.setLastName(person.getLastName());
			entity.setAddress(person.getAddress());
			entity.setGender(person.getGender());
			
			return  personRepository.save(person);
		}
		
		public void delete(Long id) {
			
			logger.info("deleting one person!");
			
			Person entity = personRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID!"));
			
			personRepository.delete(entity);
			
		}
		
}
