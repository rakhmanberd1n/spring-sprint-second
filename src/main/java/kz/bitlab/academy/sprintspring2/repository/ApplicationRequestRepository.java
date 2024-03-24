package kz.bitlab.academy.sprintspring2.repository;

import kz.bitlab.academy.sprintspring2.entity.ApplicationRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationRequestRepository extends JpaRepository<ApplicationRequest, Long> {
}
