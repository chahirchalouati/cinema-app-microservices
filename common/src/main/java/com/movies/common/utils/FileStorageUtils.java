package com.movies.common.utils;
import com.movies.common.excpetions.FileFormatNotAcceptedException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class FileStorageUtils {

    @Value("${file.extensions}")
    private List<String> extensions;
    private final Map<String, ImageDimension> dimensionMap = new HashMap<>();
    private String extension = ".ext";

    public String getFileName() {
        return UUID.randomUUID().toString().concat(".").concat(this.extension);
    }

    public Path getPath(String storeLocation) {
        return Paths.get(storeLocation).toAbsolutePath().normalize();
    }

    public FileStorageUtils getExtension(@NotNull String originalFilename) {
        String[] strings = originalFilename.split("\\.");
        final String extension = strings[strings.length - 1];
        validateExtension(extension);
        this.extension = extension;
        return this;
    }

    public String extractExtension(@NotNull String originalFilename) {
        String[] strings = originalFilename.split("\\.");
        return strings[strings.length - 1];
    }

    public FileStorageUtils then() {
        return this;
    }

    public String getContentLength(Resource resource) {
        try {
            return String.valueOf(resource.contentLength());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public String getContentType(HttpServletRequest request, Resource resource) {
        try {
            return request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return MediaType.APPLICATION_OCTET_STREAM_VALUE;
        }
    }

    public Resource getResource(Path path) {
        try {
            return new UrlResource(path.toUri());
        } catch (MalformedURLException e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public void validateExtension(String extension) {
        final boolean contains = extensions.stream()
                .map(String::trim)
                .map(String::toLowerCase)
                .collect(Collectors.toList()).contains(extension);
        if (!contains) {
            throw new FileFormatNotAcceptedException("file extension not accepted");
        }
    }

    public boolean validateExtension(String extension, List<String> extensions) {
        return extensions.stream()
                .map(String::trim)
                .map(String::toLowerCase)
                .collect(Collectors.toList()).contains(extension);
    }


    @Builder
    @Data
    @Accessors(fluent = true)
    public static class ImageDimension {
        private Integer width;
        private Integer height;
    }
}
