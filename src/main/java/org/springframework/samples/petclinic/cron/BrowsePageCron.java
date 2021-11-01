package org.springframework.samples.petclinic.cron;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Random;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Simple cron that simulates a user browsing the web 100 times per second and randomly
 * hitting the second page
 */
@Component
public class BrowsePageCron {

	@Scheduled(fixedRate = 10, initialDelay = 10000)
	public void onBrowse() {
		// one in 100 chance of showing page 2
		int page = new Random().nextInt(100) == 0 ? 1 : 0;

		// Need to request via web to trigger elastic
		try (InputStream is = new URL("http://localhost:8080/vetDetails?page=" + page).openStream()) {
			is.read();
		}
		catch (IOException err) {
			err.printStackTrace();
		}
	}

}
