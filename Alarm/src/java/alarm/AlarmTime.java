/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package alarm;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
/**
 *
 * @author Filip
 */
public class AlarmTime {
    private LocalTime Time;
    private boolean Periodic;
    private LocalTime Period;

    public AlarmTime(LocalTime Time, boolean Periodic, LocalTime Period) {
        this.Time = Time;
        this.Periodic = Periodic;
        this.Period = Period;
    }

    public LocalTime getTime() {
        return Time;
    }

    public void setTime(LocalTime Time) {
        this.Time = Time;
    }

    public boolean isPeriodic() {
        return Periodic;
    }

    public void setPeriodic(boolean Periodic) {
        this.Periodic = Periodic;
    }

    public LocalTime getPeriod() {
        return Period;
    }

    public void setPeriod(LocalTime Period) {
        this.Period = Period;
    }
}
