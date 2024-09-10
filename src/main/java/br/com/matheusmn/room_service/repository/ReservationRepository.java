package br.com.matheusmn.room_service.repository;

import br.com.matheusmn.room_service.domain.entity.Reservation;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    // Consulta para verificar se um quarto está disponível para o período solicitado
    List<Reservation> findByRoomIdAndCheckInDateLessThanEqualAndCheckOutDateGreaterThanEqual(
            Long roomId, LocalDate checkInDate, LocalDate checkOutDate);
}
