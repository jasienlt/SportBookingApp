package com.developer.sportbooking.repository;

import com.developer.sportbooking.chartDto.BookingCount;
import com.developer.sportbooking.entity.Booking;
import com.developer.sportbooking.entity.Court;
import com.developer.sportbooking.entity.Payment;
import com.developer.sportbooking.enumType.BookingStatus;
import com.developer.sportbooking.enumType.PaymentStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
@Transactional
public interface BookingRepo extends JpaRepository<Booking, Long>{
    Booking findBookingBySessionId(String sessionId);

    @Query("SELECT new com.developer.sportbooking.chartDto.BookingCount(b.createdDate, b.status, count(*)) "
            + "FROM Booking b JOIN b.reservedFieldTimeslots rs JOIN rs.fieldTimeslot fts JOIN fts.field f JOIN f.court c "
            + "WHERE b.createdDate >= :cutoffDate AND c.id = :courtId "
            + "group by b.createdDate, b.status")
    List<BookingCount> findByCourtAndStatus(Long courtId, Date cutoffDate);

    @Query("SELECT b FROM Booking b JOIN b.reservedFieldTimeslots rs JOIN rs.fieldTimeslot fts JOIN fts.field f JOIN f.court c "
            + "WHERE c.id = :courtId ")
    List<Booking> findBookingsByCourt(Long courtId);

    @Query("SELECT b FROM Booking b "
            + "WHERE b.payment = :payment ")
    Booking findBookingByPayment(Payment payment);

    @Modifying
    @Query("UPDATE Booking b set b.status = :status where b.payment = :payment")
    void updateBookingByPayment(Payment payment, BookingStatus status);
}