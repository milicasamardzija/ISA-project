package com.example.demo.repository.business;

import com.example.demo.model.business.Reservation;
import com.example.demo.model.entities.EntityClass;
import com.example.demo.model.users.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    @Query(value = "select r from Reservation r join fetch r.client where r.client.id = ?1 and r.dateStart > ?2")
    List<Reservation> fetchAllFutureReservationsForClient(int clientId, Date date);

    @Query(value = "select r from Reservation r join fetch r.client where r.client.id = ?1 and r.dateStart < ?2 and r.entityType = 0")
    List<Reservation> fetchAllHistoryReservationsForClientBoats(int clientId, Date date);

    @Query(value = "select r from Reservation r join fetch r.client where r.client.id = ?1 and r.dateStart < ?2 and r.entityType = 1")
    List<Reservation> fetchAllHistoryReservationsForClientCottages(int clientId, Date date);

    @Query(value = "select r from Reservation r join fetch r.client where r.client.id = ?1 and r.dateStart < ?2 and r.entityType = 2")
    List<Reservation> fetchAllHistoryReservationsForClientAdventures(int clientId, Date date);

    @Query(value = "select e from EntityClass e left join fetch e.reservations r where r.id= ?1")
    EntityClass findEntityByReservation(int idReservation);

    Reservation findById(int id);

    @Query(value = "select r from Reservation r join fetch r.entity e where e.id = ?1 and r.action = true and r.client is null")
    List<Reservation> getActionsForEntity(int id);

    @Query(value = "select r from Reservation r left join fetch r.entity e join fetch r.client c where c.id = ?1 and r.isCanceled = true and e.id = ?2 and r.dateStart = ?3 and r.dateEnd = ?4")
    Reservation getCanceledReservation(int idClient, int idEntity, Date dateStart, Date dateEnd);

}
