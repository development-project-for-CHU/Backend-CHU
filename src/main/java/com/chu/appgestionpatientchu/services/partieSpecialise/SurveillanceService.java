package com.chu.appgestionpatientchu.services.partieSpecialise;


import com.chu.appgestionpatientchu.domain.Surveillance;
import com.chu.appgestionpatientchu.dto.SurveillanceDto;
import com.chu.appgestionpatientchu.mappers.partieSpecialise.MapperSurveillance;
import com.chu.appgestionpatientchu.repository.partieSpecialise.SurveillanceRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SurveillanceService {


    private final SurveillanceRepository surveillanceRepository;


    public List<SurveillanceDto> saveAll(List<SurveillanceDto> surveillance){
        List<Surveillance> surveillanceList = surveillance.stream().map(MapperSurveillance::mapToSurveillance).collect(Collectors.toList());
        List<Surveillance> savedSurveillance= this.surveillanceRepository.saveAll(surveillanceList);
        return savedSurveillance.stream()
                .map(MapperSurveillance::mapToSurveillanceDto)
                .collect(Collectors.toList());
    }


    public List<SurveillanceDto> findAll(Integer size , Integer page){
        List<Surveillance> surveillanceList = this.surveillanceRepository
                .findByIsDeletedFalse( PageRequest.of(page , size))
                .stream().toList();

        return surveillanceList.stream()
                .map(MapperSurveillance::mapToSurveillanceDto)
                .collect(Collectors.toList());
    }



    public List<SurveillanceDto> findSurveillanceByNameOrCreationDate(
            Optional<String> name,
            Optional<LocalDate> creationDate)
    {
        if (name.isPresent() && creationDate.isPresent()) {
            return surveillanceRepository.findByNameAndAddedAtAndIsDeletedFalse(name.get(), creationDate.get() )
                    .stream()
                    .map(MapperSurveillance::mapToSurveillanceDto)
                    .collect(Collectors.toList());
        } else if (name.isPresent()) {
            return surveillanceRepository.findByNameAndIsDeletedFalse(name.get())
                    .stream()
                    .map(MapperSurveillance::mapToSurveillanceDto)
                    .collect(Collectors.toList());
        } else if (creationDate.isPresent()) {
            return surveillanceRepository.findByAddedAtAndIsDeletedFalse(creationDate.get())
                    .stream()
                    .map(MapperSurveillance::mapToSurveillanceDto)
                    .collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }

    }

    public SurveillanceDto updateSurveillance(Long id , SurveillanceDto updateSurveillance) throws EntityNotFoundException {
        return surveillanceRepository.findSurveillanceByIdAndIsDeletedFalse(id)
                .map(surveillance -> {
                    surveillance.setName(updateSurveillance.getName());
                    surveillance.setIsPassedToCommune(updateSurveillance.getIsPassedToCommune());
                    surveillance.setAddedAt(updateSurveillance.getAddedAt());

                    return MapperSurveillance.mapToSurveillanceDto(
                            surveillanceRepository.save(surveillance)
                    );
                })
                .orElseThrow(() -> new EntityNotFoundException("Surveillance not found with id " + id));
    }


    public SurveillanceDto deleteSurveillance(Long id) throws EntityNotFoundException  {
        return surveillanceRepository.findSurveillanceByIdAndIsDeletedFalse(id )
                .map(anamnese  -> {
                    anamnese.setDeleted(true);

                    return MapperSurveillance.mapToSurveillanceDto(
                            surveillanceRepository.save(anamnese)
                    );
                })
                .orElseThrow(() -> new EntityNotFoundException("Surveillance not found with id " + id));
    }
}
