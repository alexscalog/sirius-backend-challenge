package com.emailservice.CrudEmailService.Repository;



import com.emailservice.CrudEmailService.Models.Email;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends CrudRepository<Email, Integer> {



}
