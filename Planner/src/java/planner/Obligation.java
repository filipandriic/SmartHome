/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package planner;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 *
 * @author Filip
 */
public class Obligation {
    private String Name;
    private LocalDateTime StartTime;
    private LocalDateTime EndTime;
    private String Location;
    private boolean ReminderSet;

    
    public Obligation(String Name, LocalDateTime StartTime, LocalDateTime EndTime) {
        this.Name = Name;
        this.StartTime = StartTime;
        this.EndTime = EndTime;
        this.Location = Main.getHome();
        this.ReminderSet = false;
    }
    
    public Obligation(String Name, LocalDateTime StartTime, LocalDateTime EndTime, String Location) {
        this.Name = Name;
        this.StartTime = StartTime;
        this.EndTime = EndTime;
        this.Location = Location;
        this.ReminderSet = false;
    }

    public boolean isReminderSet() {
        return ReminderSet;
    }

    public void setReminderSet(boolean ReminderSet) {
        this.ReminderSet = ReminderSet;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public LocalDateTime getStartTime() {
        return StartTime;
    }

    public void setStartTime(LocalDateTime StartTime) {
        this.StartTime = StartTime;
    }

    public LocalDateTime getEndTime() {
        return EndTime;
    }

    public void setEndTime(LocalDateTime EndTime) {
        this.EndTime = EndTime;
    }
    
    public boolean SameTime(Obligation o) {
        if (StartTime.isBefore(o.getStartTime()) && (EndTime.isBefore(o.getStartTime()) || EndTime.isEqual(o.getStartTime())))
            return false;
        
        if (StartTime.isAfter(o.getEndTime()) || StartTime.isEqual(o.getEndTime()))
            return false;
        
        return true;
    }
    
    public long TimeDifference(Obligation o) {
        return o.getEndTime().until(StartTime, ChronoUnit.SECONDS);
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String Location) {
        this.Location = Location;
    }

    

    @Override
    public String toString() {
        return "Obligation{" + "StartTime=" + StartTime + ", EndTime=" + EndTime + ", Location=" + Location + '}';
    }

}
