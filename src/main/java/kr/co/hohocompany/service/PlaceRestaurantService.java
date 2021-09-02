package kr.co.hohocompany.service;

import kr.co.hohocompany.dto.ResPlaceRestaurantDto;
import kr.co.hohocompany.dto.UserDto;
import kr.co.hohocompany.repository.mapper.UserMapperRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@Slf4j
@AllArgsConstructor
public class PlaceRestaurantService {

    private UserMapperRepository userMapperRepository;

    public List<ResPlaceRestaurantDto> findAll() {
        List<ResPlaceRestaurantDto> resPlaceRestaurantDtos = userMapperRepository.findAll();
        System.out.println(resPlaceRestaurantDtos);
        return null;
    }
}
