package bogomonuments.com.repository;

import bogomonuments.com.entity.Monument;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonumentsRepository extends JpaRepository<Monument,Long> {
}
