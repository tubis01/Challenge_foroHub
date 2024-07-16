package com.alura.Challenge_foroHub.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicoRespository  extends JpaRepository<Topico, Long> {
    @Query("SELECT t FROM Topico t WHERE t.status = 'SOLUCIONADO' OR t.status = 'NO_SOLUCIONADO'")
    Page<Topico> findByStatusSolucionadoNoSolucionado(Pageable pageable);


}
