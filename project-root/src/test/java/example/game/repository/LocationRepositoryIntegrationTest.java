package com.example.game.repository;

import com.example.game.model.Location;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@Transactional
public class LocationRepositoryIntegrationTest {

    @Autowired
    private LocationRepository locationRepository;

    @Test
    public void testSaveAndFindByName() {
        Location location = new Location();
        location.setName("Test Location");
        location.setLat(10.0);
        location.setLng(20.0);
        locationRepository.save(location);

        List<Location> found = locationRepository.findByName("Test Location");
        assertThat(found).isNotEmpty();
        assertThat(found.get(0).getName()).isEqualTo("Test Location");
    }

    @Test
    public void testFindDistinctNames() {
        Location loc1 = new Location();
        loc1.setName("Location A");
        loc1.setLat(1.0);
        loc1.setLng(1.0);

        Location loc2 = new Location();
        loc2.setName("Location A");
        loc2.setLat(2.0);
        loc2.setLng(2.0);

        Location loc3 = new Location();
        loc3.setName("Location B");
        loc3.setLat(3.0);
        loc3.setLng(3.0);

        locationRepository.save(loc1);
        locationRepository.save(loc2);
        locationRepository.save(loc3);

        List<String> distinctNames = locationRepository.findDistinctNames();
        assertThat(distinctNames).hasSize(2);
        assertThat(distinctNames).contains("Location A", "Location B");
    }
}
