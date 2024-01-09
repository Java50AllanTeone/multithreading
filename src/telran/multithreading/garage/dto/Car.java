package telran.multithreading.garage.dto;

import java.time.Instant;

public record Car(Instant timestamp, long repairTime) {

}
