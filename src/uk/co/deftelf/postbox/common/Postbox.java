package uk.co.deftelf.postbox.common;

import java.sql.Time;
import java.util.Calendar;

public class Postbox implements Comparable<Postbox>, java.io.Serializable {
    
    
    protected double latitude;
    protected double longitude;
    protected String ref;
    protected String locationInfo1;
    protected String locationInfo2;
    protected int lastWeekdayCollection;
    protected int lastSaturdayCollection;
    
    protected double distance;
    
    protected Postbox() {
        
    }

    public Postbox( String ref,
            String locationInfo1, String locationInfo2,
            double latitude, double longitude,
            Time lastWeekdayCollection, Time lastSaturdayCollection) {
        super();
        this.latitude = latitude;
        this.longitude = longitude;
        this.ref = ref;
        this.locationInfo1 = locationInfo1;
        this.locationInfo2 = locationInfo2;
        this.lastWeekdayCollection = 0;
        if (lastWeekdayCollection != null)
            this.lastWeekdayCollection = lastWeekdayCollection.getHours()*60 + lastWeekdayCollection.getMinutes();
        this.lastSaturdayCollection = 0;
        if (lastSaturdayCollection != null)
            this.lastSaturdayCollection = lastSaturdayCollection.getHours()*60 + lastSaturdayCollection.getMinutes();
    }
    
    public void calculateDistance(double sourceLat, double sourceLon) {
        distance = toKilometres(sourceLat, sourceLon, latitude, longitude);
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getRef() {
        return ref;
    }

    public String getLocationInfo1() {
        return locationInfo1;
    }

    public String getLocationInfo2() {
        return locationInfo2;
    }

    public int getLastWeekdayCollection() {
        return lastWeekdayCollection;
    }

    public int getLastSaturdayCollection() {
        return lastSaturdayCollection;
    }

    public double getDistance() {
        return distance;
    }

    // Sort order ascending distance from source 
    @Override
    public int compareTo(Postbox o) {
        return (int)((distance - o.getDistance()) * 1000);
    }
    
    public static double toKilometres(double lat1, double lon1, double lat2, double lon2) {
        int R = 6371; // km 
        lat1 = Math.toRadians(lat1);
        lon1 = Math.toRadians(lon1);
        lat2 = Math.toRadians(lat2);
        lon2 = Math.toRadians(lon2);
        double d = Math.acos(Math.sin(lat1)*Math.sin(lat2) + 
                Math.cos(lat1)*Math.cos(lat2) *
                Math.cos(lon2-lon1)) * R;
        return d;
    }

    public boolean isStillToCollect(Calendar now) {
        int nowMins = now.get(Calendar.MINUTE) + (now.get(Calendar.HOUR_OF_DAY) * 60);
        if (now.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            return nowMins < getLastSaturdayCollection();
        } else if (now.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            return false;
        } else {
            return nowMins < getLastWeekdayCollection();
        }
    }
    
   
}
