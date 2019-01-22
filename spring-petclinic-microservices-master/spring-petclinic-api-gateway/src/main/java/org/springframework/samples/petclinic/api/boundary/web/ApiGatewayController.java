package org.springframework.samples.petclinic.api.boundary.web;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.samples.petclinic.api.application.CustomersServiceClient;
import org.springframework.samples.petclinic.api.dto.OwnerDetails;
import org.springframework.samples.petclinic.api.application.VisitsServiceClient;
import org.springframework.samples.petclinic.api.dto.VisitDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import static java.util.Collections.emptyList;


@RestController
@RequiredArgsConstructor
public class ApiGatewayController {

    private final CustomersServiceClient customersServiceClient;

    private final VisitsServiceClient visitsServiceClient;

    @GetMapping(value = "owners/{ownerId}")
    public OwnerDetails getOwnerDetails(final @PathVariable int ownerId) {
        final OwnerDetails owner = customersServiceClient.getOwner(ownerId);
        supplyVisits(owner, visitsServiceClient.getVisitsForPets(owner.getPetIds()));
        return owner;
    }

    private void supplyVisits(final OwnerDetails owner, final Map<Integer, List<VisitDetails>> visitsMapping) {
        owner.getPets().forEach(pet ->
            pet.getVisits().addAll(Optional.ofNullable(visitsMapping.get(pet.getId())).orElse(emptyList())));
    }
}
