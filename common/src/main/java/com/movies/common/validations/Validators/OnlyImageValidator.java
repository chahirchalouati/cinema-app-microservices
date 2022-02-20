package com.movies.common.validations.Validators;

import com.movies.common.utils.FileStorageUtils;
import com.movies.common.validations.annotations.OnlyImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Objects;

/**
 * @author Chahir Chalouati
 */
public class OnlyImageValidator implements ConstraintValidator<OnlyImage, MultipartFile> {

    @Value("${file.images.extensions}")
    private List<String> extensions;
    @Autowired
    private FileStorageUtils fileStorageUtils;

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext constraintValidatorContext) {
        final String extension = this.fileStorageUtils.extractExtension(Objects.requireNonNull(file.getOriginalFilename()));
        return this.fileStorageUtils.validateExtension(extension, extensions);
    }
}
