import ADTs.src.uy.edu.um.prog2.adt.List.MyList;

public class Song {
    private String name;
    private String spotifyId;
    private String[] artists;
    private int tempo;


    public Song(String name, String spotifyId, String[] artists, int tempo) {
        this.name = name;
        this.spotifyId = spotifyId;
        this.artists = artists;
        this.tempo = tempo;
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

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }
}
