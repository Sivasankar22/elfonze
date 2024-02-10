package com.example.ELFONZENEW.Respository;

import com.example.ELFONZENEW.Entity.AuditRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AuditRecordsRespository extends JpaRepository<AuditRecords,Integer> {
}
