package com.example.demo.domain.customer.repo;

import com.example.demo.domain.customer.entity.MobilityWeakInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MobilityWeakInfoRepository extends JpaRepository<MobilityWeakInfo, Long> {

}
