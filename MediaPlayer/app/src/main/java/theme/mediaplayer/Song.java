package theme.mediaplayer;

/**
 * Created by Parsa on 06/08/2015.
 */
public class Song {

    private int id;
    private String path;
    private String name;
    private String albumName;

    public void Song( String name , String Path) {

    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setPath(String path)
    {
            this.path = path;
    }


    public void setName(String name)
    {
             this.name = name;
    }

    public void setAlbumName(String albumName)
    {
               this.albumName = albumName;
    }

    public int getId ()
    {
        return id;
    }

    public String getName ()
    {
        return name;
    }

    public String getPath ()
    {
        return path;
    }

    public String getAlbumName ()
    {
        return albumName;
    }

}
