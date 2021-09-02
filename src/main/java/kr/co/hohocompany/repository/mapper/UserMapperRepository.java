package kr.co.hohocompany.repository.mapper;

import kr.co.hohocompany.dto.ResPlaceRestaurantDto;
import kr.co.hohocompany.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapperRepository {

    @Select(
    "    select\n" +
    "    pr.id,\n" +
    "    pr.name,\n" +
    "    pr.address,\n" +
    "    pr.phone,\n" +
    "    pr.lat,\n" +
    "    pr.lon,\n" +
    "    prf.baby_bed,\n" +
    "    prf.baby_chair,\n" +
    "    prf.baby_menu,\n" +
    "    prf.baby_tableware,\n" +
    "    prf.stroller,\n" +
    "    prf.diaper_change,\n" +
    "    prf.meeting_room,\n" +
    "    prf.nursing_room,\n" +
    "    prf.parking\n" +
    "    from p_restaurants pr\n" +
    "    left join p_restaurant_facilities prf on pr.id = prf.restaurant_id"
    )
    List<ResPlaceRestaurantDto> findAll();
}
