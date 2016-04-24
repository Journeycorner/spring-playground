package de.medhelfer.web;

import de.medhelfer.data.SensorDataDto;
import de.medhelfer.data.SensorDataService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.Collection;

@RestController
public class SensorReadingsController {

    private final SensorDataService sensorDataService;

    @Inject
    public SensorReadingsController(SensorDataService sensorDataService) {
        this.sensorDataService = sensorDataService;
    }

    @RequestMapping("/sensorReadings")
    public Collection<SensorDataDto> findAllSensorReadings() {
        return sensorDataService.findAllSensorData();
    }

    // TODO replace String by LocalDateTime and fix mapping
    @RequestMapping("/findSensorDataBetweenDates")
    public Collection<SensorDataDto> findSensorDataBetweenDates(
            @RequestParam(value = "from", required = false) String f,
            @RequestParam(value = "to", required = false) String t) {
        LocalDateTime from = LocalDateTime.parse(f);
        LocalDateTime to = LocalDateTime.parse(t);
        return sensorDataService.findSensorDataBetweenDates(
                from != null ? from : LocalDateTime.MIN,
                to != null ? to : LocalDateTime.MAX
        );
    }

    @RequestMapping("/saveSensorReadings")
    public Collection<SensorDataDto> saveSensorReadings(
            @RequestBody() Collection<SensorDataDto> sensorReadings) {
        return sensorDataService.saveSensorReadings(sensorReadings);
    }
}
