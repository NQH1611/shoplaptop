package vn.edu.stu.shop_laptop.response;

import lombok.Data;

@Data
public class ImageMessage {
    private String message;

    public ImageMessage(String message) {
        this.message = message;
    }
}
