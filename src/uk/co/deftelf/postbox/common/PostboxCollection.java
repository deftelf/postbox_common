package uk.co.deftelf.postbox.common;



public class PostboxCollection {
    protected Postbox[] postboxes;
    protected double centreLat;
    protected double centreLon;
    

    protected PostboxCollection() {
        
    }

    public PostboxCollection(Postbox[] postboxes, double lat, double lon) {
        this.postboxes = postboxes;
        this.centreLat = lat;
        this.centreLon = lon;
    }
    

    
    public Postbox[] getPostboxes() {
        return postboxes;
    }

    public double getCentreLat() {
        return centreLat;
    }

    public double getCentreLon() {
        return centreLon;
    }



    
}
