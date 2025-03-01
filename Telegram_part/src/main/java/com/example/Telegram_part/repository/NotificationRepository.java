package com.example.Telegram_part.repository;

import com.example.Telegram_part.model.NotificationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

public interface NotificationRepository extends JpaRepository<NotificationModel, Long> {

    @Query(value = "SELECT * FROM public.notifications n WHERE n.datetime >= :start AND n.datetime < :end", nativeQuery = true)
//    @Query("SELECT n FROM public.notifications n WHERE n.datetime >= :start AND n.datetime < :end")
//    @Query(value = "SELECT * FROM public.notifications", nativeQuery = true)
    List<NotificationModel> findNotificationsInTimeWindow(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

}
