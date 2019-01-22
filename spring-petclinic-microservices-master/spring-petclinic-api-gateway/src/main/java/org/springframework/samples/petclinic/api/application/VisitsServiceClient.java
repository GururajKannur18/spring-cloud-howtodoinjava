package org.springframework.samples.petclinic.api.application;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.samples.petclinic.api.dto.VisitDetails;
import org.springframework.samples.petclinic.api.dto.Visits;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;


import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static org.springframework.web.util.UriComponentsBuilder.fromHttpUrl;


@Component
@RequiredArgsConstructor
public class VisitsServiceClient {

    private final RestTemplate loadBalancedRestTemplate;

    @HystrixCommand(fallbackMethod = "emptyVisitsForPets")
    public Map<Integer, List<VisitDetails>> getVisitsForPets(final List<Integer> petIds) {
        UriComponentsBuilder builder = fromHttpUrl("http://visits-service/pets/visits")
            .queryParam("petId", joinIds(petIds));

        return loadBalancedRestTemplate.getForObject(builder.toUriString(), Visits.class)
            .getItems()
            .stream()
            .collect(groupingBy(VisitDetails::getPetId));
    }

    private String joinIds(List<Integer> petIds) {
        return petIds.stream().map(Object::toString).collect(joining(","));
    }

    private Map<Integer, List<VisitDetails>> emptyVisitsForPets(List<Integer> petIds) {
        return Collections.emptyMap();
    }
}
