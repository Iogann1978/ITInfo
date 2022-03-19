package ru.home.itinfo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.home.itinfo.dto.BookDTO;
import ru.home.itinfo.dto.RateDTO;
import ru.home.itinfo.dto.StateDTO;
import ru.home.itinfo.dto.google.VolumeDTO;
import ru.home.itinfo.exception.NotFoundException;
import ru.home.itinfo.mapper.VolumeMapper;

import java.net.URI;

@Service
public class GoogleService {
    @Value("${google.api.host}")
    private String googleApiHost;
    @Value("${google.api.path}")
    private String googleApiPath;
    private final RestTemplate restTemplate;
    private final VolumeMapper volumeMapper;

    @Autowired
    public  GoogleService(
            RestTemplate restTemplate,
            VolumeMapper volumeMapper
    ) {
        this.restTemplate = restTemplate;
        this.volumeMapper = volumeMapper;
    }

    public BookDTO get(String isbn) throws NotFoundException {
        URI uri = UriComponentsBuilder.newInstance().scheme("https")
                .host(googleApiHost).path(googleApiPath).query("q=isbn:{isbn}").build(isbn);
        VolumeDTO volumeDTO = restTemplate.getForObject(uri, VolumeDTO.class);
        if (volumeDTO == null || CollectionUtils.isEmpty(volumeDTO.getItems())) {
            throw new NotFoundException(String.format("Книга по isbn %s не найдена", isbn));
        }
        volumeDTO.getItems().get(0).getVolumeInfo().setIsbn(isbn);
        BookDTO bookDTO = volumeMapper.volumeToBook(volumeDTO.getItems().get(0).getVolumeInfo());
        bookDTO.setRate(RateDTO.GOOD);
        bookDTO.setState(StateDTO.PLANNED);
        return bookDTO;
    }
}
