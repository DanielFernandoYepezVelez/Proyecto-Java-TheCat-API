package models.Entity;

public class Cat {
    private String id;
    private String url;
    private String apiKey = "live_E7Rn2AAngnBESQ3SxO74getTdX2Tfo4yUWkhWFN7NTTrkHoe5WYtrlh2UOSRdhGQ";
    private String image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getApiKey() {
        return apiKey;
    }

    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}