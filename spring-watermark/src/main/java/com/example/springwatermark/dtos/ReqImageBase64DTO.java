package com.example.springwatermark.dtos;

public class ReqImageBase64DTO {
    private boolean isWatermarkImage;
    private String imageBase64;

    public boolean getIsWatermarkImage() {
        return isWatermarkImage;
    }

    public void setIsWatermarkImage(boolean watermarkImage) {
        isWatermarkImage = watermarkImage;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

}
