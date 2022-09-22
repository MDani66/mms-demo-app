package com.tsystems.mms.demoapp.orgunit;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tsystems.mms.demoapp.user.OrganisationalUnit;

@Repository
public interface OrgUnitRepository extends JpaRepository<OrganisationalUnit, Long> {

}
