package org.example.exam24hours.repository;

import org.example.exam24hours.model.Sensor;
import org.example.exam24hours.model.SensorReading;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorReadingRepository extends JpaRepository<SensorReading, Long> {
}
