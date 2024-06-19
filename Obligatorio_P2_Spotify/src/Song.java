import ADTs.src.uy.edu.um.prog2.adt.List.MyList;

public class Song implements Comparable<Song> {
    private String name;
    private String spotifyId;
    private String[] artists;
    private float tempo;
    private int temp_counter = 0;


    public Song(String name, String spotifyId, String[] artists, float tempo) {
        this.name = name;
        this.spotifyId = spotifyId;
        this.artists = artists;
        this.tempo = tempo;
    }


    public int compareTo(Song SongToCompare) {
        return (this.getTemp_counter() - SongToCompare.getTemp_counter());
    }

    public void incCounter() {
        this.temp_counter ++;
    }
    public void decCounter() {
        this.temp_counter --;
    }
    public void resetCounter() {
        this.temp_counter = 0;
    }

    public int getTemp_counter() {
        return temp_counter;
    }

    public void setTemp_counter(int temp_counter) {
        this.temp_counter = temp_counter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpotifyId() {
        return spotifyId;
    }

    public void setSpotifyId(String spotifyId) {
        this.spotifyId = spotifyId;
    }

    public String[] getArtists() {
        return artists;
    }

    public void setArtists(String[] artists) {
        this.artists = artists;
    }

    public float getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }
}
