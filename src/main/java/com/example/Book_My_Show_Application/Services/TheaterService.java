package com.example.Book_My_Show_Application.Services;


import com.example.Book_My_Show_Application.Entities.TheaterEntity;
import com.example.Book_My_Show_Application.Entities.TheaterSeatEntity;
import com.example.Book_My_Show_Application.EntryDtos.TheaterEntryDto;
import com.example.Book_My_Show_Application.Enums.SeatType;
import com.example.Book_My_Show_Application.Repository.TheaterSeatRepository;
import com.example.Book_My_Show_Application.convertors.TheaterConvertors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheaterService {

    @Autowired
    TheaterSeatRepository theaterSeatRepository;


    public String addTheater(TheaterEntryDto theaterEntryDto){

        TheaterEntity theaterEntity = TheaterConvertors.convertDtoToEntity(theaterEntryDto);

        List<TheaterSeatEntity> theaterSeatEntityList = createTheaterSeats(theaterEntryDto,theaterEntity);

        return "";
    }

    private List<TheaterSeatEntity> createTheaterSeats(TheaterEntryDto theaterEntryDto,TheaterEntity theaterEntity){


        int noClassicSeats = theaterEntryDto.getClassicSeatsCount();
        int noPremiumSeats = theaterEntryDto.getPremiumSeatsCount();

        List<TheaterSeatEntity> theaterSeatEntityList = new ArrayList<>();


        //Created the classic Seats
        for(int count = 1;count<=noClassicSeats;count++){

            //We need to make a new TheaterSeatEntity
            TheaterSeatEntity theaterSeatEntity = TheaterSeatEntity.builder()
                    .seatType(SeatType.CLASSIC).seatNo(count+"C")
                    .theaterEntity(theaterEntity).build();

            theaterSeatEntityList.add(theaterSeatEntity);
        }


        //Create the premium Seats
        for(int count=1;count<=noPremiumSeats;count++){

            TheaterSeatEntity theaterSeatEntity = TheaterSeatEntity.builder().
                    seatType(SeatType.PREMIUM).seatNo(count+"P").theaterEntity(theaterEntity).build();

            theaterSeatEntityList.add(theaterSeatEntity);

        }

        theaterSeatRepository.saveAll(theaterSeatEntityList);

        return theaterSeatEntityList;

    }
}
