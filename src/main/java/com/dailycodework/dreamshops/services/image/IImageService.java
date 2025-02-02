package com.dailycodework.dreamshops.services.image;

import com.dailycodework.dreamshops.dto.ImageDto;
import com.dailycodework.dreamshops.models.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {
    Image getImageById(Long id);
    List<ImageDto> saveImages(List<MultipartFile> files, Long productId);
    void updateImage(MultipartFile file, Long id);
    void deleteImageById(Long id);
}
