package com.example.springwatermark.services;

import com.example.springwatermark.dtos.ReqImageBase64DTO;
import com.example.springwatermark.exceptions.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class UploadService {

    private static final List<String> IMAGE_TYPES = new ArrayList<>(List.of("image/jpeg", "image/jpg", "image/png"));

    public void uploadImageFile(MultipartFile imageFile) {
        System.out.println("Filename : " + imageFile.getOriginalFilename());
        if (!IMAGE_TYPES.contains(imageFile.getContentType())) {
            throw new BadRequestException("File format should be - image/jpeg, image/jpg, image/png");
        }

        try {
            BufferedImage sourceImage= ImageIO.read(imageFile.getInputStream());
            waterMarkText(sourceImage);

            System.out.println("The tex watermark is added to the image.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void uploadImageBase64(ReqImageBase64DTO dto) {
        try {
            byte[] base64Decode = Base64.getDecoder().decode(dto.getImageBase64());
            InputStream fileInputStream = new ByteArrayInputStream(base64Decode);

            BufferedImage sourceImage = ImageIO.read(fileInputStream);

            if (dto.getIsWatermarkImage()) {
                waterMarkImage(sourceImage);

            } else {
                waterMarkText(sourceImage);
            }

            System.out.println("Watermark is added to the image.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void waterMarkImage(BufferedImage sourceImage) throws IOException {
        File imageFile = new File("images/test.png");
        BufferedImage watermarkImage = ImageIO.read(imageFile);

        // initializes necessary graphic properties
        Graphics2D g2d = (Graphics2D) sourceImage.getGraphics();
        AlphaComposite alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f);
        g2d.setComposite(alphaChannel);

        // calculates the coordinate where the image is painted
        int topLeftX = (sourceImage.getWidth() - watermarkImage.getWidth()) / 2;
        int topLeftY = (sourceImage.getHeight() - watermarkImage.getHeight()) / 2;

        // paints the image watermark
        g2d.drawImage(watermarkImage, topLeftX, topLeftY, null);

        File destImageFile = new File("images/test_image_watermarked_base_64.png");
        ImageIO.write(sourceImage, "png", destImageFile);
        g2d.dispose();
    }

    private void waterMarkText(BufferedImage sourceImage) throws IOException {
        String watermarkTxt = "TEST WATERMARK";

        // initializes necessary graphic properties
        Graphics2D g2d = (Graphics2D) sourceImage.getGraphics();
        AlphaComposite alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f);
        g2d.setComposite(alphaChannel);
        g2d.setColor(Color.BLUE);
        g2d.setFont(new Font("Arial", Font.BOLD, 200));
        FontMetrics fontMetrics = g2d.getFontMetrics();
        Rectangle2D rect = fontMetrics.getStringBounds(watermarkTxt, g2d);

        // calculates the coordinate where the String is painted
        int centerX = (sourceImage.getWidth() - (int) rect.getWidth()) / 2;
        int centerY = sourceImage.getHeight() / 2;

        // paints the textual watermark
        g2d.drawString(watermarkTxt, centerX, centerY);

        File destImageFile = new File("images/test_text_watermarked_base_64.png");
        ImageIO.write(sourceImage, "png", destImageFile);
        g2d.dispose();
    }

}
