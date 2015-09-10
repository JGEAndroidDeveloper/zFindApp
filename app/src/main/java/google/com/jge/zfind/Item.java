package google.com.jge.zfind;

/**
 * Created by Gaming Dethklok on 8/11/2015.
 */
public class Item {
    String title;
    String description;
    String businessUrl;
    String phoneNumber;
    int image;

    Item(String title, String desc, String bUrl, String pNumber, int image) {
        this.title = title;
        this.description = desc;
        this.businessUrl = bUrl;
        this.phoneNumber = pNumber;
        this.image = image;
    }
}
