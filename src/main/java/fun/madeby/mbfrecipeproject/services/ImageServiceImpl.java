package fun.madeby.mbfrecipeproject.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

/**
 * Created by Gra_m on 2022 04 25
 */
@Service
public class ImageServiceImpl implements ImageService{
    @Override
    public Set<MultipartFile> getImages() {
        return null;
    }

    @Override
    public MultipartFile getImageById(Long aLong) {
        return null;
    }

    @Override
    public MultipartFile saveImage(MultipartFile image, Long id) {
        return null;
    }

    @Override
    public void deleteImageById(Long id) {

    }
}
