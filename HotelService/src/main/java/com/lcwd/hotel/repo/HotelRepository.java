package com.lcwd.hotel.repo;

import com.lcwd.hotel.entity.Hotel;
import org.hibernate.boot.jaxb.internal.stax.JpaOrmXmlEventReader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel,String> {
}
