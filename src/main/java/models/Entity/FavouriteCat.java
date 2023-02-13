package models.Entity;

public class FavouriteCat {
    private String id;
    private String image_id;
    private String apiKey = "live_E7Rn2AAngnBESQ3SxO74getTdX2Tfo4yUWkhWFN7NTTrkHoe5WYtrlh2UOSRdhGQ";
    private ImageCat image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public ImageCat getImage() {
        return image;
    }

    public void setImage(ImageCat image) {
        this.image = image;
    }
}
