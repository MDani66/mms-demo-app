package com.tsystems.mms.demoapp.orgunit;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tsystems.mms.demoapp.user.OrganisationalUnit;

@Service
@Transactional
public class OrgUnitService {
	
	private OrgUnitRepository orgUnitRepository;

	@Autowired
	public OrgUnitService(OrgUnitRepository orgUnitRepository) {
		super();
		this.orgUnitRepository = orgUnitRepository;
	}

	public Optional<OrganisationalUnit> getOrgUnit(Long id) {
		return orgUnitRepository.findById(id);
	}

	public OrganisationalUnit saveOrgUnit(OrganisationalUnit organisationalUnit) {
		OrganisationalUnit saved = orgUnitRepository.save(organisationalUnit);
		return saved;
	}
		
}
