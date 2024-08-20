package br.com.matheusmn.room_service.repository;

import br.com.matheusmn.room_service.domain.entity.Room;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findAllByAvailableTrue();
}
