package Integration.schemas;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UploadFile {
    @JsonProperty("imageUrl")
    private String imageUrl;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
