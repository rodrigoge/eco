package br.com.eco.pointservice.mappers;

import br.com.eco.pointservice.enums.PointSortByEnum;
import br.com.eco.pointservice.models.PointSortBy;
import br.com.eco.pointservice.models.PointSortByAddress;
import br.com.eco.pointservice.models.PointSortByCity;
import br.com.eco.pointservice.models.PointSortByEmail;
import br.com.eco.pointservice.models.PointSortByName;
import br.com.eco.pointservice.models.PointSortByUF;

public class PointSortEnumMapper {

    public static PointSortBy toSort(PointSortByEnum pointSortByEnum) {
        return switch (pointSortByEnum) {
            case NAME -> new PointSortByName();
            case EMAIL -> new PointSortByEmail();
            case CITY -> new PointSortByCity();
            case ADDRESS -> new PointSortByAddress();
            case UF -> new PointSortByUF();
        };
    }
}
