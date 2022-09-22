package com.tsystems.mms.demoapp.orgunit;

import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tsystems.mms.demoapp.user.OrganisationalUnit;
import com.tsystems.mms.demoapp.user.UserController;

@RestController
@RequestMapping("/api/v1.0/orgunit")
public class OrgUnitController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	private final OrgUnitService service;
	
	/**
	 * @param service
	 */
	public OrgUnitController(OrgUnitService service) {
		super();
		this.service = service;
	}

	@PostMapping
	public ResponseEntity<URI> saveOrgUnit(@RequestBody OrganisationalUnit organisationalUnit) {
		LOGGER.info("Creating new Unit");
		OrganisationalUnit unitSaved = service.saveOrgUnit(organisationalUnit);
		LOGGER.info("Created new unit: " + unitSaved.toString());
		return ResponseEntity.created(URI.create("/api/v1.0/orgunit/" + unitSaved.getId())).build();
	}
	
}
