package org.solent.com504.oodd.cart.dao.impl;


import org.solent.com504.oodd.cart.model.dto.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository  extends JpaRepository<Invoice,Long>{
    
}