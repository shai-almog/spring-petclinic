package org.springframework.samples.petclinic.cron;

import java.time.LocalDate;
import java.util.Random;
import org.springframework.samples.petclinic.visit.Visit;
import org.springframework.samples.petclinic.visit.VisitRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * A simple cron process that simulates a very busy pet clinic that sees a new pet visit
 * every 30 seconds
 */
@Component
public class VetVisitCron {

	private final VisitRepository visitRepository;

	public VetVisitCron(VisitRepository visitRepository) {
		this.visitRepository = visitRepository;
	}

	@Scheduled(fixedRate = 30000, initialDelay = 10000)
	public void onVisit() {
		Visit visit = new Visit();
		visit.setDate(LocalDate.now());
		visit.setDescription("New visit at " + visit.getDate());

		// all visits go to the last vet
		visit.setVetId(10);
		visit.setPetId(new Random().nextInt(10) + 1);
		visitRepository.save(visit);
	}

}
