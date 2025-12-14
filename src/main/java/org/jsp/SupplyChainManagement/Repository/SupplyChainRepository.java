package org.jsp.SupplyChainManagement.Repository;

import org.jsp.SupplyChainManagement.Entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplyChainRepository extends JpaRepository<Supplier, Integer>
{
	
}
