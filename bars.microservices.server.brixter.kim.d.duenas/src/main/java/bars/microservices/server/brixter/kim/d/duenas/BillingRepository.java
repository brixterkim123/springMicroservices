package bars.microservices.server.brixter.kim.d.duenas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public interface BillingRepository extends JpaRepository<Billing, Integer>{

	public List<Billing> findByBillingCycle(int billingCycle);
	
}
