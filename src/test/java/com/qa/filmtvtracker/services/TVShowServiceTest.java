package com.qa.filmtvtracker.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import com.qa.filmtvtracker.config.AppConfig;
import com.qa.filmtvtracker.entities.TVShow;
import com.qa.filmtvtracker.repo.TVShowRepo;

@SpringBootTest(classes= {TVShowService.class, TVShowRepo.class, AppConfig.class})
@ActiveProfiles("test")
public class TVShowServiceTest {
	@Autowired
	TVShowService service;
	
	@MockBean
	TVShowRepo repo;
	
	@Test
	public void testCreate() {
		TVShow show = new TVShow(1L, "Gintama", (short) 2006, (short) 2021, "Comedy/Action", 367, 4);
		Mockito.when(repo.save(show)).thenReturn(show);
		assertEquals(show, service.addShow(show));
		Mockito.verify(this.repo, Mockito.times(1)).save(show);
	}
}
