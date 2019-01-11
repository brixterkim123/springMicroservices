package bars.microservices.server.brixter.kim.d.duenas;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Integer> {

	public List<Account> findByAccountId(int accountId);
	
}
